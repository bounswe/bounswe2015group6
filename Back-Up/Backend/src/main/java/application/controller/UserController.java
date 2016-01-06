package application.controller;

import application.core.*;
import application.miscalleneous.ProfileResponse;
import application.miscalleneous.Result;
import application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private FollowController follow;

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private PostController postControl;

    @Autowired
    private TopicFollowRepository topicFollowRepo;

    @Autowired
    private FollowRepository followRepo;

    @Autowired
    private TopicController topicControl;

    @Autowired
    private RecentController recentControl;

    @Autowired
    private TagTopicRelationRepository tagTopicRelationRepo;

    @Autowired
    private FeedRepository feedRepo;

    @Autowired
    private FeedController feedControl;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ArrayList<User> findAll(){
        ArrayList<User> list =  repo.findAll();
        for(User u: list){

            u.setFollowList(followRepo.getFollowerNames(u.getId()));
            u.setResult(new Result(Result.RESULT_OK, "User founded"));
            u.setFollowedTopics(topicFollowRepo.getByFollowerId(u.getId()));
            u.setCreatedTopics(topicRepo.findTopicByOwnerId(u.getId()));
            u.setCreatedPosts(postControl.findPostByOwnerId(u.getId()));
            u.setFollowedUsers(followRepo.getFollowedNames(u.getId()));
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public User findById(@PathVariable("id") int id){
        User user = repo.findById(id);
        if(user == null){
            user = new User();
            user.setResult(new Result(Result.RESULT_FAILED, "User not found"));
            return user;
        }

        /* Get and set followers */
        List<String> followers = followRepo.getFollowerNames(user.getId());
        user.setResult(new Result(Result.RESULT_OK, "User has found"));
        user.setFollowList(followers);
        user.setFollowedTopics(topicFollowRepo.getByFollowerId(user.getId()));
        user.setCreatedTopics(topicRepo.findTopicByOwnerId(user.getId()));
        user.setCreatedPosts(postControl.findPostByOwnerId(user.getId()));
        user.setFollowedUsers(followRepo.getFollowedNames(user.getId()));

        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/username/{username}")
    public User findByUsername(@PathVariable("username") String username){
        User user = repo.findByUsername(username);
        if(user == null){
            user = new User();
            user.setResult(new Result(Result.RESULT_FAILED, "User not found"));
            return user;
        }

        /*Get and set followers */
        List<String> followers = followRepo.getFollowerNames(user.getId());
        user.setResult(new Result(Result.RESULT_OK, "User has found"));
        user.setFollowList(followers);
        user.setFollowedTopics(topicFollowRepo.getByFollowerId(user.getId()));
        user.setCreatedTopics(topicRepo.findTopicByOwnerId(user.getId()));
        user.setCreatedPosts(postControl.findPostByOwnerId(user.getId()));
        user.setFollowedUsers(followRepo.getFollowedNames(user.getId()));

        return user;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup", headers = "Accept=application/json")
    public User save(
          @Valid @RequestBody User user)
    {
        String userName = user.getUsername();
        User temp = repo.findByUsername(userName);

        if(temp != null){
            temp = new User();
            temp.setResult(new Result(Result.RESULT_FAILED, "User has already registered"));
            return temp;
        }

        temp = repo.findByEmail(user.getEmail());

        if(temp != null){
            temp = new User();
            temp.setResult(new Result(Result.RESULT_FAILED, "User has already registered"));
            return temp;
        }

        String saltPassword = BCrypt.gensalt(12);
        String hashPassword = BCrypt.hashpw(user.getPassword(), saltPassword);

        temp = new User();
        temp.setUsername(user.getUsername());
        temp.setPassword(hashPassword);
        temp.setEmail(user.getEmail());
        temp.setFacebookId(user.getFacebookId() + "@facebook");
        temp.setGoogleId(user.getGoogleId() + "@google");
        temp.setTwitterId(user.getTwitterId() + "@twitter");
        temp.setResult(new Result(Result.RESULT_OK, "User has been succesfully registered"));
        repo.save(temp);
        return temp;
    }
    /* Controller returns all available information even though password authentication fails*/
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public User login(
            @RequestParam("username") String username, @RequestParam("password") String password) {

        User user = repo.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setResult(new Result(Result.RESULT_FAILED, "User not found"));
            return user;
        }

        if(!BCrypt.checkpw(password, user.getPassword())){
            user = new User();
            user.setResult(new Result(Result.RESULT_FAILED, "Password did not match"));
            return user;
        }

        user.setFollowList(followRepo.getFollowerNames(user.getId()));
        user.setResult(new Result(Result.RESULT_OK, "Login confirmed"));
        user.setFollowedTopics(topicFollowRepo.getByFollowerId(user.getId()));
        user.setCreatedTopics(topicRepo.findTopicByOwnerId(user.getId()));
        user.setCreatedPosts(postControl.findPostByOwnerId(user.getId()));
        user.setFollowedUsers(followRepo.getFollowedNames(user.getId()));

        return user;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}/followed_topics")
    public ArrayList<Topic> findFollowTopics(@PathVariable("id") int id){

        ArrayList<Integer> follows = topicFollowRepo.getByFollowerId(id);
        ArrayList<Topic> topics = new ArrayList<>();
        for(Integer i: follows){
            topics.add(topicControl.getById(i));
        }

        return topics;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/id/{id}/follow_topic")
    public TopicFollow followTopic(@PathVariable("id") int userId, @RequestParam("topic_id") int topicId){

        TopicFollow tf = topicFollowRepo.findByFollowerIdAndTopicId(userId, topicId);

        if(tf == null){

            tf = new TopicFollow();
            tf.setResult(new Result(Result.RESULT_OK, "Topic followed"));
            tf.setFollowerId(userId);
            tf.setTopicId(topicId);
            topicFollowRepo.save(tf);

        }
        else{

            tf.setResult(new Result(Result.RESULT_FAILED, "Already followed"));

        }

        return tf;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/profile/{id}")
    public ProfileResponse getProfile(@PathVariable("id") int userId){

        User user = repo.findById(userId);
        ProfileResponse pr = new ProfileResponse();

        pr.setUsername(user.getUsername());

        Map<Integer, String> following = new HashMap<>();
        Map<Integer, String> followers = new HashMap<>();


        ArrayList<Integer> followingUsers = followRepo.getFollowed(userId);
        ArrayList<Integer> followedUsers = followRepo.getFollowers(userId);

        for(Integer i: followingUsers){

            User x = repo.findById(i);
            if(x == null) continue;

            following.put(i, x.getUsername());
        }

        for(Integer i: followedUsers){

            User x = repo.findById(i);
            if(x == null) continue;

            followers.put(i, x.getUsername());
        }

        pr.setFollowers(followers);
        pr.setFollowing(following);

        pr.setFeeds(feedControl.getNews(userId));
        pr.setActions(feedControl.getActions(userId));

        return pr;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}/get_recommend")
    public ArrayList<Topic> getRecommendations(@PathVariable("id") int userId){

        ArrayList<Integer> topicFollows = topicFollowRepo.getByFollowerId(userId);

        if(topicFollows.size() == 0){
            return recentControl.getPopularTopics();
        }

        ArrayList<Integer> tagIds = new ArrayList<>();
        ArrayList<Topic> recommended = new ArrayList<>();
        ArrayList<Integer> topicIds = new ArrayList<>();

        for(Integer i: topicFollows){
            ArrayList<TagTopicRelation> ttr = tagTopicRelationRepo.findByTopicId(i);

            for(TagTopicRelation ttr1: ttr){
                int tagId = ttr1.getTagId();
                if(!tagIds.contains(tagId)) tagIds.add(tagId);
            }
        }

        for(Integer i: tagIds){
            ArrayList<TagTopicRelation> ttr = tagTopicRelationRepo.findByTagId(i);

            for(TagTopicRelation ttr1: ttr){
                int topicId = ttr1.getTopicId();
                if(!topicFollows.contains(topicId) && !topicIds.contains(topicId)){
                    topicIds.add(topicId);
                    recommended.add(topicControl.getById(topicId));
                }
            }
        }

        return recommended;

    }


}

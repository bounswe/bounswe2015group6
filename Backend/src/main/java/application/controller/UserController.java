package application.controller;

import application.core.Follow;
import application.core.User;
import application.miscalleneous.Result;
import application.repository.FollowRepository;
import application.repository.TopicFollowRepository;
import application.repository.TopicRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * repo is user repository
     */
    @Autowired
    private UserRepository repo;

    /**
     * controller for follewers
     */
    @Autowired
    private FollowController follow;

    /**
     *repo. of topics
     */
    @Autowired
    private TopicRepository topicRepo;

    /**
     * post control class
     */
    @Autowired
    private PostController postControl;

    /**
     * repo of topic follow
     */
    @Autowired
    private TopicFollowRepository topicFollowRepo;

    /**
     * follow repo
     */
    @Autowired
    private FollowRepository followRepo;

    /**
     * Method to get all users in the system
     * @return list of all users
     */
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

    /**
     *
     * Method to find users by id
     * @param id if of wanted user
     * @return if user is found, returns user with all its features
     * such as Followers, Topics, Posts etc.
     * If user couldn't be found, it returns "User not found" message
     *
     */
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

    /**
     *
     * Method to find user by their username
     * @param username
     * @return if user is found, returns user with all its features
     * such as Followers, Topics, Posts etc.
     * If user couldn't be found, it returns "User not found" message
     *
     */
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


    /**
     *
     *
     * to set new variables of user
     *
     * @param user
     * @return
     */
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

    /**
     *
     * Controller returns all available information even though password authentication fails
     *
     * @param username
     * @param password
     * @return
     */
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
}

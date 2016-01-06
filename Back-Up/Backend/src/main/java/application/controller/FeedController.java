package application.controller;

import application.core.Feed;
import application.core.TopicTopicRelation;
import application.miscalleneous.FeedResponse;
import application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by User on 17.12.2015.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/feed")

public class FeedController {

    @Autowired
    private FeedRepository feedRepo;

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private TopicTopicRelationRepository topicTopicRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostController postControl;

    /*
    * api/feed/feeds
    * @return Returns all logged actions in table
    * */

    @RequestMapping(method = RequestMethod.GET, value = "/feeds")
    public ArrayList<FeedResponse> getAll(){

        ArrayList<Feed> temp = feedRepo.getAll();
        ArrayList<FeedResponse> responses = new ArrayList<>();

        for(Feed feed: temp){
            FeedResponse fr = new FeedResponse();
            fr.setFeed(feed);
            fr.setUsername(userRepo.findById(feed.getUserId()).getUsername());
            fr.setSubjectName(generateSubjectName(feed.getType(), feed.getSubject()));
            responses.add(fr);
        }

        return responses;
    }

    /*
    * /api/feed/id/{id}
    * @param id Id of specific log
    * @return Returns log of given id
    * */
    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public FeedResponse getById(@PathVariable("id") int id){

        Feed feed = feedRepo.findById(id);
        FeedResponse fr = new FeedResponse();
        fr.setFeed(feed);
        fr.setUsername(userRepo.findById(feed.getUserId()).getUsername());
        fr.setSubjectName(generateSubjectName(feed.getType(), feed.getSubject()));

        return fr;
    }

    /*
    * /api/feed/get_news_of?id={id}
    * @param id User id which his followees' actions will be displayed
    * @return Returns all logged actions of followees*/
    @RequestMapping(method = RequestMethod.GET, value = "/get_news_of")
    public ArrayList<FeedResponse> getNews(@RequestParam("id") int id){

        ArrayList<Feed> temp = feedRepo.findFollowedFeed(id);
        ArrayList<FeedResponse> responses = new ArrayList<>();

        for(Feed feed: temp){
            FeedResponse fr = new FeedResponse();

            if(feed.getType() == 2){

                int topicId = postControl.getById(feed.getSubject()).getTopicId();
                feed.setTopicId(topicId);
                feed.setTopicName(topicRepo.findById(topicId).getTitle());
            }

            fr.setFeed(feed);
            fr.setUsername(userRepo.findById(feed.getUserId()).getUsername());
            fr.setSubjectName(generateSubjectName(feed.getType(), feed.getSubject()));
            responses.add(fr);
        }

        return responses;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_actions_of")
    public ArrayList<FeedResponse> getActions(@RequestParam("id") int id){

        ArrayList<Feed> temp = feedRepo.findByUserIdOrderByDateAsc(id);
        ArrayList<FeedResponse> responses = new ArrayList<>();

        for(Feed feed: temp){
            FeedResponse fr = new FeedResponse();

            if(feed.getType() == 2){

                int topicId = postControl.getById(feed.getSubject()).getTopicId();
                feed.setTopicId(topicId);
                feed.setTopicName(topicRepo.findById(topicId).getTitle());
            }

            fr.setFeed(feed);
            fr.setUsername(userRepo.findById(feed.getUserId()).getUsername());
            fr.setSubjectName(generateSubjectName(feed.getType(), feed.getSubject()));
            responses.add(fr);
        }

        return responses;
    }

    /* Helper method */
    private String generateSubjectName(int type, int subject){

        if(type == 1){
            return topicRepo.findById(subject).getTitle();
        }
        else if(type == 2){
            return postRepo.findById(subject).getId() + "";
        }
        else{
            TopicTopicRelation ttr = topicTopicRepo.findById(subject);
            String firstTopic = topicRepo.findById(ttr.getFrom()).getTitle();
            String secondTopic = topicRepo.findById(ttr.getTo()).getTitle();
            return firstTopic + " and " + secondTopic;
        }
    }

}

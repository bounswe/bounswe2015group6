package application.controller;

import application.core.*;
import application.miscalleneous.Result;
import application.repository.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by User on 13.12.2015.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/recent_popular")
public class RecentController {

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private TopicTopicRelationRepository topicTopicRepo;

    @Autowired
    private TagTopicRelationRepository tagTopicRelationRepo;

    @Autowired
    private TagRepository tagRepo;

    @Autowired
    private PostTopicRelationRepository postTopicRepo;

    @Autowired
    private PostController postControl;

    /**
     * method returns topics that are created or edited today
     * @return topics of today
     */
    @RequestMapping(method = RequestMethod.GET, value = "/recent")
    public ArrayList<Topic> getRecentTopics(){

        DateTime today = new DateTime(DateTimeZone.forID("Europe/Istanbul"));
        ArrayList<Topic> temp = topicRepo.findRecentTopics(today);

        for(Topic topic: temp){
            topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        }

        for(Topic topic: temp){

            /* Find edges of a specific topic */
            ArrayList<TopicTopicRelation> edges = topicTopicRepo.findByFrom(topic.getId());

            /* Store their target ids */
            ArrayList<Integer> nodeNumbers  = new ArrayList<Integer>();
            for(TopicTopicRelation t: edges){
                nodeNumbers.add(t.getTo());
            }

            /* Finally set relations */
            topic.setTopicRelations(nodeNumbers);

            /* Get tags of the topic */
            ArrayList<TagTopicRelation> tags = tagTopicRelationRepo.findByTopicId(topic.getId());
            ArrayList<String> tagNames = new ArrayList<>();
            for(TagTopicRelation ttr: tags){
                String tagName = tagRepo.findById(ttr.getTagId()).getTagName();
                tagNames.add(tagName);
            }
            topic.setTags(tagNames);

            /* Find posts of specific topic */
            ArrayList<PostTopicRelation> postRelations = postTopicRepo.findByTopicId(topic.getId());
            ArrayList<Post> posts = new ArrayList<>();

            /* Now topics return posts as JSON */
            for(PostTopicRelation pt: postRelations){
                Post post = postControl.getById(pt.getPostId());
                posts.add(post);
            }

            topic.setPosts(posts);
            topic.setLabel(topic.getTitle());
        }

        return temp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/popular")
    public ArrayList<Topic> getPopularTopics(){

        ArrayList<Topic> temp = topicRepo.findPopularTopics();

        for(Topic topic: temp){
            topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        }

        for(Topic topic: temp){

            /* Find edges of a specific topic */
            ArrayList<TopicTopicRelation> edges = topicTopicRepo.findByFrom(topic.getId());

            /* Store their target ids */
            ArrayList<Integer> nodeNumbers  = new ArrayList<Integer>();
            for(TopicTopicRelation t: edges){
                nodeNumbers.add(t.getTo());
            }

            /* Finally set relations */
            topic.setTopicRelations(nodeNumbers);

            /* Get tags of the topic */
            ArrayList<TagTopicRelation> tags = tagTopicRelationRepo.findByTopicId(topic.getId());
            ArrayList<String> tagNames = new ArrayList<>();
            for(TagTopicRelation ttr: tags){
                String tagName = tagRepo.findById(ttr.getTagId()).getTagName();
                tagNames.add(tagName);
            }
            topic.setTags(tagNames);

            /* Find posts of specific topic */
            ArrayList<PostTopicRelation> postRelations = postTopicRepo.findByTopicId(topic.getId());
            ArrayList<Post> posts = new ArrayList<>();

            /* Now topics return posts as JSON */
            for(PostTopicRelation pt: postRelations){
                Post post = postControl.getById(pt.getPostId());
                posts.add(post);
            }

            topic.setPosts(posts);
            topic.setLabel(topic.getTitle());
        }

        return temp;
    }
}

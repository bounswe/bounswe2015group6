package application.controller;

import application.core.TagTopicRelation;
import application.core.Topic;
import application.miscalleneous.Result;
import application.repository.TagTopicRelationRepository;
import application.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private TagTopicRelationRepository tagTopicRelationRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/topics")
    public ArrayList<Topic> getAll(){
        ArrayList<Topic> temp = topicRepo.findAll();

        for(Topic topic: temp){
            topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        }

        return temp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public Topic getById(@PathVariable("id") int id){

        Topic topic = topicRepo.findById(id);

        if(topic == null){
            topic = new Topic();
            topic.setResult(new Result(Result.RESULT_FAILED, "Topic has not been found"));
            return topic;
        }

        topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        ArrayList<TagTopicRelation> temp = tagTopicRelationRepo.findByTopicId(id);

        /* Change to post JSON representations of tags */
        ArrayList<Integer> tagIds = new ArrayList<Integer>();
        for(TagTopicRelation t: temp){
            tagIds.add(t.getTagId());
        }

        topic.setTags(tagIds);
        /* Set the topic and post relations */
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/title/{title}")
    public Topic getByTitle(@PathVariable("title") String title){

        Topic topic = topicRepo.findByTitle(title);

        if(topic == null){
            topic = new Topic();
            topic.setResult(new Result(Result.RESULT_FAILED, "Topic has not been found"));
            return topic;
        }

        topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        ArrayList<TagTopicRelation> temp = tagTopicRelationRepo.findByTopicId(topic.getId());

        /* Change to post JSON representations of tags*/
        ArrayList<Integer> tagIds = new ArrayList<Integer>();
        for(TagTopicRelation t: temp){
            tagIds.add(t.getTagId());
        }

        topic.setTags(tagIds);
        /* Set the topic and post relations */
        return topic;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create", headers = "Accept=application/json")
    public Topic create(@Valid @RequestBody Topic topic){

        Topic temp = topicRepo.findByTitle(topic.getTitle());

        if(temp != null){
            temp = new Topic();
            temp.setResult(new Result(Result.RESULT_FAILED, "Topic already exists"));
            return temp;
        }

        temp = new Topic();
        temp.setResult(new Result(Result.RESULT_OK, "Topic has been succesfully registered"));
        temp.setTitle(topic.getTitle());
        temp.setOwnerId(topic.getOwnerId());
        topicRepo.save(temp);
        return temp;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public Topic delete(@RequestParam("id") int id){

        Topic temp = topicRepo.findById(id);

        if(temp == null){
            temp = new Topic();
            temp.setResult(new Result(Result.RESULT_OK, "Topic does not exist"));
            return temp;
        }

        temp.setResult(new Result(Result.RESULT_OK, "Topic has been succesfully deleted"));
        topicRepo.delete(id);
        return temp;
    }
}

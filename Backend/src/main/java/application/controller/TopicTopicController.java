package application.controller;

import application.core.Topic;
import application.core.TopicTopicRelation;
import application.repository.TopicTopicRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping(value = "edge")
public class TopicTopicController {

    @Autowired
    private TopicTopicRelationRepository topicTopicRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/edges")
    public ArrayList<TopicTopicRelation> getAll(){
        return topicTopicRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public TopicTopicRelation getOne(@PathVariable("id") int id){
        TopicTopicRelation edge = topicTopicRepo.findById(id);
        return edge;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/source/{source}")
    public ArrayList<TopicTopicRelation> getBySource(@PathVariable("source") int source){
        return topicTopicRepo.findByFrom(source);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/destination/{dest}")
    public ArrayList<TopicTopicRelation> getByDestination(@PathVariable("dest") int destination){
        return topicTopicRepo.findByTo(destination);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public TopicTopicRelation create(@RequestParam("source") int source, @RequestParam("dest") int destination){
        TopicTopicRelation edge = new TopicTopicRelation(source, destination);
        TopicTopicRelation edge2 = new TopicTopicRelation(destination, source);

        TopicTopicRelation temp = topicTopicRepo.findByFromAndTo(source, destination);
        if(temp != null){
            return temp;
        }
        topicTopicRepo.save(edge);
        return edge;
    }

}

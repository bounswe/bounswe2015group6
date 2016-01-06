package application.controller;

import application.core.Feed;
import application.core.Search;
import application.core.TopicTopicRelation;
import application.repository.FeedRepository;
import application.repository.TopicRepository;
import application.repository.TopicTopicRelationRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping(value = "edge")
public class TopicTopicController {

    @Autowired
    private TopicTopicRelationRepository topicTopicRepo;

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private FeedRepository feedRepo;


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
    public TopicTopicRelation create(@RequestParam("source") int source,
                                     @RequestParam("dest")   int destination,
                                     @RequestParam("label")  String label,
                                     @RequestParam("user")   int user){
        TopicTopicRelation edge = new TopicTopicRelation(source, destination);

        TopicTopicRelation temp = topicTopicRepo.findByFromAndTo(source, destination);
        if(temp != null){
            return temp;
        }

        DateTime dateTime = new DateTime(DateTimeZone.forID("Europe/Istanbul"));
        topicRepo.updateEditDate(dateTime, source);
        topicRepo.updateEditDate(dateTime, destination);

        edge.setLabel(label);
        topicTopicRepo.save(edge);

        Search search = new Search();
        String topic1 = "_" + source + "_";
        String topic2 = "_" + destination + "_";
        search.createTopicRelatedTo(topic1, topic2);

        Feed feed = new Feed();
        feed.setUserId(user);
        feed.setDate(dateTime);
        feed.setType(3);
        feed.setMessage("has created relation between");
        feed.setSubject(edge.getId());
        feedRepo.save(feed);
        return edge;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public void deleteEdge(@RequestParam("id") int id){

        /* Delete edge from connections */
        topicTopicRepo.deleteById(id);

        /* Delete also from feed */
        feedRepo.deleteBySubjectAndType(id, 3);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create_new")
    public TopicTopicRelation createNew(@RequestBody @Valid TopicTopicRelation ttr, @RequestParam("user_id")int id){

        TopicTopicRelation edge = topicTopicRepo.findByFromAndTo(ttr.getFrom(), ttr.getTo());
        TopicTopicRelation temp = new TopicTopicRelation(ttr.getFrom(), ttr.getTo());
        int source = ttr.getFrom();
        int destination = ttr.getTo();

        if(edge != null){
            return edge;
        }

        temp.setLabel(ttr.getLabel());
        temp.setTitle(ttr.getTitle());

        topicTopicRepo.save(temp);

        DateTime dateTime = new DateTime(DateTimeZone.forID("Europe/Istanbul"));
        topicRepo.updateEditDate(dateTime, source);
        topicRepo.updateEditDate(dateTime, destination);


        Search search = new Search();
        String topic1 = "_" + source + "_";
        String topic2 = "_" + destination + "_";
        search.createTopicRelatedTo(topic1, topic2);

        Feed feed = new Feed();
        feed.setDate(dateTime);
        feed.setType(3);
        feed.setUserId(id);
        feed.setMessage("has created relation between");
        feed.setSubject(temp.getId());
        feedRepo.save(feed);

        return temp;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_connections")
    public ArrayList<TopicTopicRelation> getEdges(){
        return topicTopicRepo.findAll();
    }

}

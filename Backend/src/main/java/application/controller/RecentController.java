package application.controller;

import application.core.Topic;
import application.repository.TopicRepository;
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

    @RequestMapping(method = RequestMethod.GET, value = "/recent")
    public ArrayList<Topic> getRecentTopics(){

        DateTime today = new DateTime(DateTimeZone.forID("Europe/Istanbul"));
        ArrayList<Topic> recents = topicRepo.findRecentTopics(today);

        return recents;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/popular")
    public ArrayList<Topic> getPopularTopics(){

        ArrayList<Topic> populars = topicRepo.findPopularTopics();
        return populars;
    }
}

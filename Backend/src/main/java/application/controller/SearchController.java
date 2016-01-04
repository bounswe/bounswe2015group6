package application.controller;

import application.core.Search;
import application.core.Topic;
import application.repository.PostRepository;
import application.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private TopicRepository topicRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ArrayList<String> search(@RequestParam("search") String search){
        Search searchIns = new Search();
        ArrayList<String> temp = searchIns.search(search);
        ArrayList<String> topicNames = new ArrayList<String>();
        for(String s: temp){
            String x = s.replaceAll("_", "");
            int topicId = Integer.parseInt(x);
            Topic topic = topicRepo.findById(topicId);

            if(topic != null) {
                String topicName = topic.getTitle();
                topicNames.add(topicName);
            }
        }

        return topicNames;
    }
}

package application.controller;

import application.core.Search;
import application.core.Topic;
import application.core.User;
import application.repository.PostRepository;
import application.repository.TopicRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


    @Autowired
    private UserRepository userRepo;




    @RequestMapping(method = RequestMethod.GET, value = "/*/{searchTerm}")
    public Map<String, ArrayList<String>> searchForTopicAndUserName(@PathVariable("searchTerm") String searchTerm){

        Map<String, ArrayList<String>> results = new HashMap<>();

        ArrayList<User> users = userRepo.findByUserNameContaining(searchTerm);
        ArrayList<Topic> topics = topicRepo.findByTitleContaining(searchTerm);

        ArrayList<String> UserNames = new ArrayList<>();
        for (User u :users){
            UserNames.add(u.getUsername());
        }

        ArrayList<String> TopicTitles = new ArrayList<>();

        for (Topic t: topics) {
            TopicTitles.add(t.getTitle());
        }

        results.put("User", UserNames);
        results.put("Topic", TopicTitles);


        return results;



    }
}

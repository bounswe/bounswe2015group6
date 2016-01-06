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

@CrossOrigin
@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Map<String, Map<Integer, String>> search(@RequestParam("search") String search){

        Search searchIns = new Search();

        search = search.replaceAll("\\s+", "_");
        ArrayList<String> temp = searchIns.search(search);
        ArrayList<String> topicNames = new ArrayList<>();
        ArrayList<String> topicIds = new ArrayList<>();

        Map<Integer, String> topicAndId = new HashMap<>();
        Map<Integer, String> userAndId = new HashMap<>();

        for(String s: temp){
            String x = s.replaceAll("_", "");
            int topicId = Integer.parseInt(x);
            Topic topic = topicRepo.findById(topicId);

            if(topic != null) {
                String topicName = topic.getTitle();
                topicNames.add(topicName);
                topicAndId.put(topicId, topicName);
            }
        }

        Map<String, Map<Integer, String>> results = new HashMap<>();

        ArrayList<User> users = userRepo.findByUsernameContaining(search);
        ArrayList<Topic> topics = topicRepo.findByTitleContaining(search);

        for (User u :users){
            userAndId.put(u.getId(), u.getUsername());
        }

        for (Topic t: topics) {
            if(!topicNames.contains(t.getTitle())) {
                topicAndId.put(t.getId(), t.getTitle());
                topicNames.add(t.getTitle());
            }
        }

        results.put("User", userAndId);
        results.put("Topic", topicAndId);


        return results;

    }
}

package application.controller;

import application.core.Follow;
import application.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/follow")
public class FollowController {

    @Autowired
    private FollowRepository repo;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Follow create(@RequestParam("follower")int followerId, @RequestParam("followed")int followedId){
        Follow follow = new Follow();
        follow.setFollowed(followedId);
        follow.setFollower(followerId);
        repo.save(follow);
        return follow;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/follows")
    public List<Follow> findAll(){
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "followers/{id}")
    public List<Integer> getFollowers(@PathVariable("id") int id){
        return repo.getFollowers(id);
    }
}

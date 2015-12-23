package application.controller;

import application.core.Follow;
import application.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/follow")
public class FollowController {

    @Autowired
    private FollowRepository repo;

    /*
    * api/follow/create*/
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Follow create(@RequestParam("follower")int followerId, @RequestParam("followed")int followedId){
        Follow follow = new Follow();
        follow.setFollowed(followedId);
        follow.setFollower(followerId);
        repo.save(follow);
        return follow;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/follows")
    public ArrayList<Follow> findAll(){
        return repo.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/followers/{id}")
    public ArrayList<Integer> getFollowers(@PathVariable("id") int followedId){
        return repo.getFollowers(followedId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/follows/{id}")
    public ArrayList<Follow> getFollowedPeople(@PathVariable("id") int id){
        return repo .findByFollowerId(id);
    }
}

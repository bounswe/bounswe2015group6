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

    @RequestMapping(method = RequestMethod.GET)
    public List<Follow> findByFollowed(@RequestParam("followed") Integer followedId, @RequestParam("follower") Integer followerId){
        if(followedId != null)
            return repo.findByFollowedId(followedId);
        if(followerId != null)
            return repo.findByFollowerId(followerId);
        return null;

    }
    /*
    @RequestMapping(method = RequestMethod.GET, value = "/follows")
    public List<Follow> findByFollower(@RequestParam("follower") Integer followerId){
        return repo.findByFollowerId(followerId);
    }*/
   // @RequestMapping(method = RequestMethod.GET, value = "/followers/{id}")
   // public List<Integer> getFollowers(@PathVariable("id") int followedId){
   //     return repo.getFollowers(followedId);
   // }
}

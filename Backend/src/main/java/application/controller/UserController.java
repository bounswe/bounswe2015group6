package application.controller;

import application.core.User;
import application.repository.FollowRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private FollowRepository follow;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ArrayList<User> findAll(){
        ArrayList<User> temp =  repo.findAll();
        for(User user: temp){
            user.setFollowList((ArrayList<Integer>)follow.getFollowers(user.getId()));
        }
        return temp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public User findById(@PathVariable("id") int id){
        User user = repo.findById(id);
        if(user == null){
            user = new User();
            user.setId(-1);
            return user;
        }

        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/username/{username}")
    public User findByUsername(@PathVariable("username") String username){
        User user = repo.findByUsername(username);
        if(user == null){
            user = new User();
            user.setId(-1);
            return user;
        }

        return user;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public User save(
            @RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("email") String email)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFacebookId(username + "@facebook");
        user.setGoogleId(username  + "@google");
        user.setTwitterId(username + "@twitter");
        repo.save(user);
        return user;
    }
}

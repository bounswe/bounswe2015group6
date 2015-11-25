package application.controller;

import application.core.User;
import application.miscalleneous.Result;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private FollowController follow;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ArrayList<User> findAll(){
        ArrayList<User> list =  repo.findAll();
        for(User u: list){
            u.setFollowList(follow.getFollowers(u.getId()));
            u.setResult(new Result(Result.RESULT_OK, "User founded"));
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public User findById(@PathVariable("id") int id){
        User user = repo.findById(id);
        if(user == null){
            user = new User();
            user.setResult(new Result(Result.RESULT_FAILED, "User not found"));
            user.setId(-1);
            return user;
        }
        List<Integer> followers = follow.getFollowers(user.getId());
        user.setResult(new Result(Result.RESULT_OK, "User has found"));
        user.setFollowList(followers);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/username/{username}")
    public User findByUsername(@PathVariable("username") String username){
        User user = repo.findByUsername(username);
        if(user == null){
            user = new User();
            user.setResult(new Result(Result.RESULT_FAILED, "User not found"));
            user.setId(-1);
            return user;
        }
        List<Integer> followers = follow.getFollowers(user.getId());
        user.setResult(new Result(Result.RESULT_OK, "User has found"));
        user.setFollowList(followers);
        return user;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup", headers = "Accept=application/json")
    public User save(
          @Valid @RequestBody User user)
    {
        String userName = user.getUsername();
        User temp = repo.findByUsername(userName);

        if(temp != null){
            temp = new User();
            temp.setResult(new Result(Result.RESULT_FAILED, "User has already registered"));
            return temp;
        }

        String saltPassword = BCrypt.gensalt(12);
        String hashPassword = BCrypt.hashpw(user.getPassword(), saltPassword);

        temp = new User();
        temp.setUsername(user.getUsername());
        temp.setPassword(hashPassword);
        temp.setEmail(user.getEmail());
        temp.setFacebookId(user.getFacebookId() + "@facebook");
        temp.setGoogleId(user.getGoogleId() + "@google");
        temp.setTwitterId(user.getTwitterId() + "@twitter");
        temp.setResult(new Result(Result.RESULT_OK, "User has been succesfully registered"));
        repo.save(temp);
        return temp;
        /*User user = repo.findByUsername(username);
        if(user != null){
            user.setResult(new Result(Result.RESULT_FAILED, "User has already registered"));
            return user;
        }

        String saltPassword = BCrypt.gensalt(12);
        String hashPassword = BCrypt.hashpw(password, saltPassword);

        user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword);
        user.setEmail(email);
        user.setFacebookId(username + "@facebook");
        user.setGoogleId(username  + "@google");
        user.setTwitterId(username + "@twitter");
        user.setResult(new Result(Result.RESULT_OK, "User has been succesfully registered"));
        repo.save(user);
        return user;*/
    }
    /* Controller returns all available information even though password authentication fails*/
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public User login(
            @RequestParam("username") String username, @RequestParam("password") String password) {

        User user = repo.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setResult(new Result(Result.RESULT_FAILED, "User not found"));
            return user;
        }

        if(!BCrypt.checkpw(password, user.getPassword())){
          user.setResult(new Result(Result.RESULT_FAILED, "Password did not match"));
        }
        user.setResult(new Result(Result.RESULT_OK, "Login confirmed"));
        return user;

    }
}

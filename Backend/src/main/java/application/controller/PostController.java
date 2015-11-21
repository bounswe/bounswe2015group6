package application.controller;

import application.core.Post;
import application.core.Tag;
import application.core.User;
import application.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.miscalleneous.Result;

import java.util.ArrayList;



@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostRepository postRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    public ArrayList<Post> getAll(){
        ArrayList<Post> list =  postRepo.findAll();
        for(Post p: list){
          p.setResult(new Result(Result.RESULT_OK, "Post found"));
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/id/{id}")
    public Post getById(@PathVariable("id")int id){
        Post post = postRepo.findById(id);
        if(post == null){
            post = new Post();
            post.setResult(new Result(Result.RESULT_FAILED, "Post not found"));
            return post;
        }

        post.setResult(new Result(Result.RESULT_OK, "Post found"));
        return post;
    }
    //TODO: yeniden bak
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Post createPost(@RequestParam("postContent") String postContent, @RequestParam("postTags") ArrayList<Tag> tags, @PathVariable("postOwner") User user){
        Post post = new Post();
        post.setContent(postContent);
        post.setUser(user);
        post.setTags(tags);
        post.setResult(new Result(Result.RESULT_OK,"Succesfully created"));
        return post;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public void delete(@PathVariable ("id") int id){
        postRepo.delete(id);

    }
}

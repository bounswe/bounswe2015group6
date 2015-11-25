package application.controller;

import application.core.Post;
import application.core.PostUser;
import application.core.Tag;
import application.core.User;
import application.repository.PostRepository;
import application.repository.PostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.miscalleneous.Result;

import javax.validation.Valid;
import java.util.ArrayList;



@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private PostUserRepository postUserRepo;

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
    /* Method changed to accept request body */
    @RequestMapping(method = RequestMethod.POST, value = "/create", headers = "Accept=application/json")
    public Post createPost(@RequestBody @Valid Post post){
        Post temp = new Post();
        temp.setContent(post.getContent());
        temp.setOwnerId(post.getOwnerId());
        temp.setTagsOfPost(post.getTagsOfPost());
        temp.setResult(new Result(Result.RESULT_OK,"Succesfully created"));
        postRepo.save(temp);

        /* Add a new row to the Post-User relation*/
        PostUser pu = new PostUser();
        pu.setPostId(temp.getId());
        pu.setOwnerId(temp.getOwnerId());
        postUserRepo.save(pu);

        return temp;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public void delete(@PathVariable ("id") int id){
        Post post = postRepo.findById(id);
        postRepo.delete(id);
        /* Delete the relation also from the post_user table */
        postUserRepo.deleteByOwnerIdAndPostId(post.getOwnerId(), post.getId());
    }
}

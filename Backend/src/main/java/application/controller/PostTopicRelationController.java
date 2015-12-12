package application.controller;

import application.core.Post;
import application.core.PostTopicRelation;
import application.core.TagPostRelation;
import application.miscalleneous.Result;
import application.repository.PostRepository;
import application.repository.PostTopicRelationRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/topic_post")
public class PostTopicRelationController {

    @Autowired
    private PostTopicRelationRepository postTopicRelationRepo;

    @Autowired
    private PostRepository postRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/topic_post/{id}")
    public JSONArray getByTopicId(@PathVariable("id")int id){
        ArrayList<PostTopicRelation> relate = postTopicRelationRepo.findByTopicId(id);
        //Post post = postRepo.findById(id);
        ArrayList<Post> posts = new ArrayList<Post>();

        JSONArray jarray = new JSONArray();
        for (PostTopicRelation rel:relate
             ) {
            Post post = postRepo.findById(rel.getPostId());
            if(post!=null){
                JSONObject obj = new JSONObject();
                post.setResult(new Result(Result.RESULT_OK, "Post found"));
                obj.put("result",post.getResult());
                obj.put("id",post.getId());
                obj.put("ownerId",post.getOwnerId());
                obj.put("content",post.getContent());
                obj.put("tags", post.getTagsOfPost());

                jarray.add(new JSONObject (obj));
            }
        }


        return jarray;
    }
}

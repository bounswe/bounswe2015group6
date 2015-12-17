package application.controller;

import application.core.*;
import application.repository.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.miscalleneous.Result;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private PostUserRepository postUserRepo;

    @Autowired
    private TagRepository tagRepo;

    @Autowired
    private TagPostRelationRepository tagPostRelationRepo;

    @Autowired
    private PostTopicRelationRepository postTopicRelationRepo;

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private FeedRepository feedRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    public ArrayList<Post> getAll(){

        ArrayList<Post> list =  postRepo.findAll();

        for(Post p: list){
          p.setResult(new Result(Result.RESULT_OK, "Post found"));
            ArrayList<String> tags = new ArrayList<>();

            for (TagPostRelation t: tagPostRelationRepo.findByPostId(p.getId())){
                int tagId = t.getTagId();
                Tag tag = tagRepo.findById(tagId);
                tags.add(tag.getTagName());
            }
            p.setTagsOfPost(tags);

            /* Get owner of the post */
            PostUser pu = postUserRepo.findByPostId(p.getId());
            p.setOwnerId(pu.getOwnerId());

            PostTopicRelation ptr = postTopicRelationRepo.findByPostId(p.getId());
            int topicId = ptr.getTopicId();
            p.setTopicId(topicId);
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public Post getById(@PathVariable("id") int id){

        Post post = postRepo.findById(id);
        if(post == null){
            post = new Post();
            post.setResult(new Result(Result.RESULT_FAILED, "Post not found"));
            return post;
        }

        post.setResult(new Result(Result.RESULT_OK, "Post found"));

        /* Get tagIds of the tag-post realation */
        ArrayList<String> tags = new ArrayList<>();
        for (TagPostRelation t: tagPostRelationRepo.findByPostId(post.getId())){
            int tagId = t.getTagId();
            Tag tag = tagRepo.findById(tagId);
            tags.add(tag.getTagName());
        }

        /* Set the tagNames */
        post.setTagsOfPost(tags);

        /* Get owner of the post */
        PostUser pu = postUserRepo.findByPostId(post.getId());
        post.setOwnerId(pu.getOwnerId());

        PostTopicRelation ptr = postTopicRelationRepo.findByPostId(post.getId());
        int topicId = ptr.getTopicId();
        post.setTopicId(topicId);

        return post;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public ArrayList<Post> findByOwnerId(@PathVariable("id") int id){

        ArrayList<Post> allPosts = getAll();
        ArrayList<Post> temp = new ArrayList<>();

        for(Post post: allPosts){
            if(post.getOwnerId() == id)
                temp.add(post);
        }

        return temp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}/id_s")
    public ArrayList<Post> findPostByOwnerId(@PathVariable("id") int id){

        ArrayList<Post> allPosts = getAll();
        ArrayList<Post> temp = new ArrayList<>();

        for(Post post: allPosts){
            if(post.getOwnerId() == id)
                temp.add(post);
        }

        return temp;
    }



    //TODO: yeniden bak
    /* Method changed to accept request body */
    @RequestMapping(method = RequestMethod.POST, value = "/create", headers = "Accept=application/json")
    public Post createPost(@RequestBody @Valid Post post, @RequestParam("topicId") int topicId){

        Post temp = new Post();
        temp.setContent(post.getContent());
        temp.setOwnerId(post.getOwnerId());
        temp.setTagsOfPost(post.getTagsOfPost());
        temp.setResult(new Result(Result.RESULT_OK,"Succesfully created"));
        temp.setTopicId(topicId);
        temp.setDate(new DateTime(DateTimeZone.forID("Europe/Istanbul")));
        temp.setEditDate(new DateTime(DateTimeZone.forID("Europe/Istanbul")));
        postRepo.save(temp);

        topicRepo.updateEditDate(new DateTime(DateTimeZone.forID("Europe/Istanbul")), topicId);

        /* Add a new row to the Post-User relation */
        PostUser pu = new PostUser();
        pu.setPostId(temp.getId());
        pu.setOwnerId(temp.getOwnerId());
        postUserRepo.save(pu);


        /* Add tag-post relations */
        for (String tagName :temp.getTagsOfPost()) {
            TagPostRelation tpg = new TagPostRelation();
            tpg.setPostId(temp.getId());
            int tagID = tagRepo.findByTagName(tagName).getId();
            tpg.setTagId(tagID);
            tagPostRelationRepo.save(tpg);
        }

        /* Add post-topic relation */
        PostTopicRelation postTopic = new PostTopicRelation();
        postTopic.setPostId(temp.getId());
        postTopic.setTopicId(topicId);
        postTopicRelationRepo.save(postTopic);

        Feed feed = new Feed();
        feed.setDate(new DateTime(DateTimeZone.forID("Europe/Istanbul")));
        feed.setMessage("has created post in topic");
        feed.setUserId(temp.getOwnerId());
        feed.setType(2);
        feed.setSubject(temp.getId());
        feedRepo.save(feed);

        return temp;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public void deletePost(@RequestParam ("id") int id){
        Post post = postRepo.findById(id);

        /* Delete the post from the table*/
        postRepo.delete(id);

        /* Delete the relation also from the post_user table */
        postUserRepo.deleteByOwnerIdAndPostId(post.getOwnerId(), post.getId());

        /* Delete also tag-post relations*/
        tagPostRelationRepo.deleteByPostId(post.getId());

        /* Delete also post-topic relation */
        postTopicRelationRepo.deleteByPostId(post.getId());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/id/{id}/up_vote")
    public int upVote(@PathVariable("id") int id){

        Post temp = postRepo.findById(id);
        if(temp == null){
            temp = new Post();
            temp.setResult(new Result(Result.RESULT_OK, "Topic does not exist"));
            return -1;
        }

        int i = postRepo.updateUpVote(id);
        return i;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/id/{id}/down_vote")
    public int downVote(@PathVariable("id") int id){

        Post temp = postRepo.findById(id);
        if(temp == null){
            temp = new Post();
            temp.setResult(new Result(Result.RESULT_OK, "Topic does not exist"));
            return -1;
        }

        int i = postRepo.updateDownVote(id);
        return i;
    }
}

package application.controller;

import application.core.*;
import application.miscalleneous.PostResponse;
import application.miscalleneous.Result;
import application.repository.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.ArrayType;
import javax.validation.Valid;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private TagTopicRelationRepository tagTopicRelationRepo;

    @Autowired
    private TopicTopicRelationRepository topicTopicRepo;

    @Autowired
    private PostTopicRelationRepository postTopicRepo;

    @Autowired
    private TagRepository tagRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private PostController postControl;

    @RequestMapping(method = RequestMethod.GET, value = "/topics")
    public ArrayList<Topic> getAll(){
        ArrayList<Topic> temp = topicRepo.findAll();

        for(Topic topic: temp){
            topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        }

        for(Topic topic: temp){

            /* Find edges of a specific topic */
            ArrayList<TopicTopicRelation> edges = topicTopicRepo.findByFrom(topic.getId());

            /* Store their target ids */
            ArrayList<Integer> nodeNumbers  = new ArrayList<Integer>();
            for(TopicTopicRelation t: edges){
                nodeNumbers.add(t.getTo());
            }

            /* Finally set relations */
            topic.setTopicRelations(nodeNumbers);

            /* Get tags of the topic */
            ArrayList<TagTopicRelation> tags = tagTopicRelationRepo.findByTopicId(topic.getId());
            ArrayList<String> tagNames = new ArrayList<>();
            for(TagTopicRelation ttr: tags){
                String tagName = tagRepo.findById(ttr.getTagId()).getTagName();
                tagNames.add(tagName);
            }
            topic.setTags(tagNames);

            /* Find posts of specific topic */
            ArrayList<PostTopicRelation> postRelations = postTopicRepo.findByTopicId(topic.getId());
            ArrayList<Post> posts = new ArrayList<>();

            /* Now topics return posts as JSON */
            for(PostTopicRelation pt: postRelations){
                Post post = postControl.getById(pt.getPostId());
                posts.add(post);
            }

            topic.setPosts(posts);
            topic.setLabel(topic.getTitle());
        }

        return temp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public Topic getById(@PathVariable("id") int id){

        Topic topic = topicRepo.findById(id);

        if(topic == null){
            topic = new Topic();
            topic.setResult(new Result(Result.RESULT_FAILED, "Topic has not been found"));
            return topic;
        }

        topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        ArrayList<TagTopicRelation> temp = tagTopicRelationRepo.findByTopicId(id);

        /* Change to post JSON representations of tags */
        ArrayList<TagTopicRelation> tags = tagTopicRelationRepo.findByTopicId(topic.getId());
        ArrayList<String> tagNames = new ArrayList<>();
        for(TagTopicRelation ttr: tags){
            String tagName = tagRepo.findById(ttr.getTagId()).getTagName();
            tagNames.add(tagName);
        }
        topic.setTags(tagNames);

        /* Set the topic and post relations */
        ArrayList<TopicTopicRelation> edges = topicTopicRepo.findByFrom(topic.getId());
        ArrayList<Integer> nodeNumbers  = new ArrayList<Integer>();
        for(TopicTopicRelation t: edges){
            nodeNumbers.add(t.getTo());
        }

        topic.setTopicRelations(nodeNumbers);

        /* Find posts of specific topic */
        ArrayList<PostTopicRelation> postRelations = postTopicRepo.findByTopicId(topic.getId());
        ArrayList<Post> posts = new ArrayList<>();

            /* Now topics return posts as JSON */
        for(PostTopicRelation pt: postRelations){
            Post post = postControl.getById(pt.getPostId());
            posts.add(post);
        }

        topic.setPosts(posts);
        topic.setLabel(topic.getTitle());

        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/title/{title:.+}")
    public Topic getByTitle(@PathVariable("title") String title){

        Topic topic = topicRepo.findByTitle(title);

        if(topic == null){
            topic = new Topic();
            topic.setResult(new Result(Result.RESULT_FAILED, "Topic has not been found"));
            return topic;
        }

        topic.setResult(new Result(Result.RESULT_OK, "Topic found"));

        /* Set tag relations */
        ArrayList<TagTopicRelation> tags = tagTopicRelationRepo.findByTopicId(topic.getId());
        ArrayList<String> tagNames = new ArrayList<>();
        for(TagTopicRelation ttr: tags){
            String tagName = tagRepo.findById(ttr.getTagId()).getTagName();
            tagNames.add(tagName);
        }
        topic.setTags(tagNames);

        /* Set the topic and post relations */
        ArrayList<PostTopicRelation> postRelations = postTopicRepo.findByTopicId(topic.getId());
        ArrayList<Post> posts = new ArrayList<>();

            /* Now topics return posts as JSON */
        for(PostTopicRelation pt: postRelations){
            Post post = postControl.getById(pt.getPostId());
            posts.add(post);
        }

        topic.setPosts(posts);

        /* Set topic relations */
        ArrayList<TopicTopicRelation> edges = topicTopicRepo.findByFrom(topic.getId());
        ArrayList<Integer> nodeNumbers  = new ArrayList<Integer>();
        for(TopicTopicRelation t: edges){
            nodeNumbers.add(t.getTo());
        }

        topic.setTopicRelations(nodeNumbers);
        topic.setLabel(topic.getTitle());

        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public ArrayList<Topic> getByOwnerId(@PathVariable("id") int id){

        ArrayList<Topic> temp = topicRepo.findByOwnerId(id);

        for(Topic topic: temp){
            topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        }

        for(Topic topic: temp){

            /* Find edges of a specific topic */
            ArrayList<TopicTopicRelation> edges = topicTopicRepo.findByFrom(topic.getId());

            /* Store their target ids */
            ArrayList<Integer> nodeNumbers  = new ArrayList<Integer>();
            for(TopicTopicRelation t: edges){
                nodeNumbers.add(t.getTo());
            }

            /* Finally set relations */
            topic.setTopicRelations(nodeNumbers);

            /* Get tags of the topic */
            ArrayList<TagTopicRelation> tags = tagTopicRelationRepo.findByTopicId(topic.getId());
            ArrayList<String> tagNames = new ArrayList<>();
            for(TagTopicRelation ttr: tags){
                String tagName = tagRepo.findById(ttr.getTagId()).getTagName();
                tagNames.add(tagName);
            }
            topic.setTags(tagNames);

            /* Find posts of specific topic */
            ArrayList<PostTopicRelation> postRelations = postTopicRepo.findByTopicId(topic.getId());
            ArrayList<Post> posts = new ArrayList<>();

            /* Now topics return posts as JSON */
            for(PostTopicRelation pt: postRelations){
                Post post = postControl.getById(pt.getPostId());
                posts.add(post);
            }

            topic.setPosts(posts);
            topic.setLabel(topic.getTitle());
        }

        return temp;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create", headers = "Accept=application/json")
    public Topic create(@Valid @RequestBody Topic topic){

        Topic temp = topicRepo.findByTitle(topic.getTitle());

        /* Check if topic exists */
        if(temp != null){
            temp = new Topic();
            temp.setResult(new Result(Result.RESULT_FAILED, "Topic already exists"));
            return temp;
        }

        temp = new Topic();
        temp.setResult(new Result(Result.RESULT_OK, "Topic has been succesfully registered"));
        temp.setTitle(topic.getTitle());
        temp.setOwnerId(topic.getOwnerId());
        temp.setCreateDate(new DateTime(DateTimeZone.forID("Europe/Istanbul")));
        temp.setEditDate(new DateTime(DateTimeZone.forID("Europe/Istanbul")));
        topicRepo.save(temp);

        /* Get tags of the topic */
        for(String tagName: topic.getTags()){
            TagTopicRelation ttr = new TagTopicRelation();

            /* Check whether tag exists */
            Tag tag = tagRepo.findByTagName(tagName);

            /* Save the tag if does not exists */
            if(tag == null){
                tag = new Tag();
                tag.setTagName(tagName);
                tagRepo.save(tag);
            }

            /* Create tag-topic relation */
            ttr.setTopicId(temp.getId());
            ttr.setTagId(tag.getId());
            tagTopicRelationRepo.save(ttr);
        }

        temp.setTags(topic.getTags());
        temp.setLabel(topic.getTitle());

        return temp;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public Topic delete(@RequestParam("id") int id){

        Topic temp = topicRepo.findById(id);
        /* Check whether topic exists */
        if(temp == null){
            temp = new Topic();
            temp.setResult(new Result(Result.RESULT_OK, "Topic does not exist"));
            return temp;
        }

        temp.setResult(new Result(Result.RESULT_OK, "Topic has been succesfully deleted"));
        topicRepo.delete(id);

        /* Also delete tag relations */
        tagTopicRelationRepo.deleteByTopicId(id);

        /* Also delete post relations */
        postTopicRepo.deleteByTopicId(id);

        return temp;
    }


    @RequestMapping(method = RequestMethod.GET, value  = "/id/getpost/{id}")
    public PostResponse getPosts(@PathVariable("id") int id){

        Topic temp = topicRepo.findById(id);
        PostResponse pr = new PostResponse();
        /* Check whether topic exists */
        if(temp == null){
            temp = new Topic();
            temp.setResult(new Result(Result.RESULT_OK, "Topic does not exist"));
            return pr;
        }

        temp = getById(temp.getId());
        temp.setResult(new Result(Result.RESULT_OK, "Topic has found"));
        pr.setPosts(temp.getPosts());
        return pr;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/id/{id}/up_vote")
    public int upVote(@PathVariable("id") int id){

        Topic temp = topicRepo.findById(id);
        if(temp == null){
            temp = new Topic();
            temp.setResult(new Result(Result.RESULT_OK, "Topic does not exist"));
            return -1;
        }

        int i = topicRepo.updateUpVote(id);
        return i;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/id/{id}/down_vote")
    public int downVote(@PathVariable("id") int id){

        Topic temp = topicRepo.findById(id);
        if(temp == null){
            temp = new Topic();
            temp.setResult(new Result(Result.RESULT_OK, "Topic does not exist"));
            return -1;
        }

        int i = topicRepo.updateDownVote(id);
        return i;
    }

}
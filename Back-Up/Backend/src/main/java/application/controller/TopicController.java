package application.controller;

import application.core.*;
import application.miscalleneous.EdgeResponse;
import application.miscalleneous.PostResponse;
import application.miscalleneous.Result;
import application.miscalleneous.TopicResponse;
import application.repository.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private FeedRepository feedRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/topics")
    public ArrayList<Topic> getAll(){
        ArrayList<Topic> temp = topicRepo.findAll();

        for(Topic topic: temp){
            topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        }

        for(Topic topic: temp){

            /* Find edges of a specific topic */
            ArrayList<TopicTopicRelation> edges = topicTopicRepo.findByFrom(topic.getId());
            ArrayList<TopicTopicRelation> edges2 = topicTopicRepo.findByTo(topic.getId());

            ArrayList<EdgeResponse> fromRelated = new ArrayList<>();
            ArrayList<EdgeResponse> toRelated = new ArrayList<>();

            ArrayList<Topic> nodeNumbers  = new ArrayList<>();

            for(TopicTopicRelation t: edges){
                System.out.println("Burada sonsuz1");
                EdgeResponse er = new EdgeResponse();
                er.setTopicName(topicRepo.findById(t.getTo()).getTitle());
                er.setLabel(t.getLabel());
                fromRelated.add(er);
            }

            for(TopicTopicRelation t: edges2){
                System.out.println("Burada sonsuz2");
                EdgeResponse er = new EdgeResponse();
                er.setTopicName(topicRepo.findById(t.getFrom()).getTitle());
                er.setLabel(t.getLabel());
                toRelated.add(er);
            }
            System.out.println("Topic 1 bitti");
            topic.setFromRelated(fromRelated);
            topic.setToRelated(toRelated);
            topic.setLabel(topic.getTitle());

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

            System.out.println("Postları yapıcam");
            /* Now topics return posts as JSON */
            for(PostTopicRelation pt: postRelations){
                Post post = postControl.getById(pt.getPostId());
                posts.add(post);
            }
            System.out.println("Postları yaptım");

            topic.setPosts(posts);
            topic.setLabel(topic.getTitle());

            int value = topic.getValue() + (topic.getPosts().size() * 4) / 2 + topic.getUpVote() + topic.getDownVote();
            if(value > 150) value = 150;
            if(value < 10) value = 10;

            topic.setValue(value);

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
        ArrayList<TopicTopicRelation> edges2 = topicTopicRepo.findByTo(topic.getId());

        ArrayList<EdgeResponse> fromRelated = new ArrayList<>();
        ArrayList<EdgeResponse> toRelated = new ArrayList<>();


        for(TopicTopicRelation t: edges){
            EdgeResponse er = new EdgeResponse();
            er.setTopicName(topicRepo.findById(t.getTo()).getTitle());
            er.setLabel(t.getLabel());
            fromRelated.add(er);
        }

        for(TopicTopicRelation t: edges2){
            EdgeResponse er = new EdgeResponse();
            er.setTopicName(topicRepo.findById(t.getFrom()).getTitle());
            er.setLabel(t.getLabel());
            toRelated.add(er);
        }

        topic.setFromRelated(fromRelated);
        topic.setToRelated(toRelated);
        topic.setLabel(topic.getTitle());

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

        int value = topic.getValue() + (topic.getPosts().size() * 4) / 2 + topic.getUpVote() + topic.getDownVote();
        if(value > 150) value = 150;
        if(value < 10) value = 10;

        topic.setValue(value);

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
        ArrayList<TopicTopicRelation> edges2 = topicTopicRepo.findByTo(topic.getId());

        ArrayList<EdgeResponse> fromRelated = new ArrayList<>();
        ArrayList<EdgeResponse> toRelated = new ArrayList<>();



        for(TopicTopicRelation t: edges){;
            EdgeResponse er = new EdgeResponse();
            er.setTopicName(topicRepo.findById(t.getTo()).getTitle());
            er.setLabel(t.getLabel());
            fromRelated.add(er);
        }

        for(TopicTopicRelation t: edges2){
            EdgeResponse er = new EdgeResponse();
            er.setTopicName(topicRepo.findById(t.getFrom()).getTitle());
            er.setLabel(t.getLabel());
            toRelated.add(er);
        }

        topic.setFromRelated(fromRelated);
        topic.setToRelated(toRelated);
        topic.setLabel(topic.getTitle());

        int value = topic.getValue() + (topic.getPosts().size() * 4) / 2 + topic.getUpVote() + topic.getDownVote();
        if(value > 150) value = 150;
        if(value < 10) value = 10;

        topic.setValue(value);

        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public ArrayList<Topic> getByOwnerId(@PathVariable("id") int id){

        ArrayList<Topic> temp = topicRepo.findByOwnerId(id);

        for(Topic topic: temp){
            topic.setResult(new Result(Result.RESULT_OK, "Topic found"));
        }

        for(Topic topic: temp){

            ArrayList<TopicTopicRelation> edges = topicTopicRepo.findByFrom(topic.getId());
            ArrayList<TopicTopicRelation> edges2 = topicTopicRepo.findByTo(topic.getId());

            ArrayList<EdgeResponse> fromRelated = new ArrayList<>();
            ArrayList<EdgeResponse> toRelated = new ArrayList<>();



            for(TopicTopicRelation t: edges){;
                EdgeResponse er = new EdgeResponse();
                er.setTopicName(topicRepo.findById(t.getTo()).getTitle());
                er.setLabel(t.getLabel());
                fromRelated.add(er);
            }

            for(TopicTopicRelation t: edges2){
                EdgeResponse er = new EdgeResponse();
                er.setTopicName(topicRepo.findById(t.getFrom()).getTitle());
                er.setLabel(t.getLabel());
                toRelated.add(er);
            }

            topic.setFromRelated(fromRelated);
            topic.setToRelated(toRelated);
            topic.setLabel(topic.getTitle());

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

            int value = topic.getValue() + (topic.getPosts().size() * 4) / 2 + topic.getUpVote() + topic.getDownVote();
            if(value > 150) value = 150;
            if(value < 10) value = 10;

            topic.setValue(value);
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
        temp.setGroup(topic.getGroup());
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

        Search search = new Search();
        for(String tag: topic.getTags()){
            String xx = "_" + temp.getId() + "_";
            String t = tag.replaceAll("\\s+", "_");
            search.createTopicHasTag(xx, t);
        }

        for(String tag: topic.getTags()){
            for(String tag2: topic.getTags()) {
                if(!tag.equals(tag2)) {
                    String x1 = tag.replaceAll("\\s+", "_");
                    String x2 = tag2.replaceAll("\\s+", "_");
                    search.createUsedWith(x1, x2);
                }
            }
        }

        Feed feed = new Feed();
        feed.setDate(new DateTime(DateTimeZone.forID("Europe/Istanbul")));
        feed.setMessage("has created topic");
        feed.setUserId(temp.getOwnerId());
        feed.setType(1);
        feed.setSubject(topicRepo.findByTitle(temp.getTitle()).getId());
        feedRepo.save(feed);
        return temp;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public Topic delete(@RequestParam("id") int id){

        Topic temp = topicRepo.findById(id);
        /* Check whether topic exists */
        if(temp == null){
            temp = new Topic();
            temp.setResult(new Result(Result.RESULT_OK, "Topic does not exist"));
            return temp;
        }

        temp.setResult(new Result(Result.RESULT_OK, "Topic has been succesfully deleted"));
        topicRepo.deleteById(id);

        /* Also delete tag relations */
        tagTopicRelationRepo.deleteByTopicId(id);

        /* Also delete post relations */
        postTopicRepo.deleteByTopicId(id);

        /* Delete topic relations */
        topicTopicRepo.deleteByFrom(id);
        topicTopicRepo.deleteByTo(id);

        /* Delete also from feeds */
        feedRepo.deleteBySubjectAndType(id, 1);

        return temp;
    }

    /* !! Attention --> URL has changed */
    @RequestMapping(method = RequestMethod.GET, value  = "/id/{id}/get_posts")
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

        ArrayList<Post> posts = temp.getPosts();
        ArrayList<Post> posts2 = new ArrayList<>();

        for(int i = posts.size() - 1; i >= 0; i--){
            posts2.add(posts.get(i));
        }
        pr.setPosts(posts2);
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

    /* Add new tag to the topic */
    @RequestMapping(method = RequestMethod.POST, value = "/add_tag/id/{id}", headers = "Accept=application/json")
    public Topic addNewtag(@RequestBody Tag tag, @PathVariable("id") int id){

        Topic topic = topicRepo.findById(id);

        if(topic == null){
            topic = new Topic();
            topic.setResult(new Result(Result.RESULT_FAILED, "Topic has not been found"));
            return topic;
        }

        Search search = new Search();

        String tagName = tag.getTagName();

        topic = this.getById(id);
        ArrayList<String> tags = topic.getTags();

        if(tags.contains(tag.getTagName())){
            topic.setResult(new Result(Result.RESULT_FAILED, "Tag already added"));
            return topic;
        }

        if(tagRepo.findByTagName(tagName) == null){
            Tag temp = new Tag();
            temp.setTagName(tagName);
            tagRepo.save(temp);
        }

        TagTopicRelation ttr = new TagTopicRelation();
        ttr.setTagId(tagRepo.findByTagName(tagName).getId());
        ttr.setTopicId(id);
        tagTopicRelationRepo.save(ttr);

        /* Donate relations for search engine */
        for(String tagNameX: tags){
            String x1 = tagNameX.replaceAll("\\s+", "_");
            String x2 = tagName.replaceAll("\\s+", "_");
            search.createUsedWith(x1, x2);
            search.createUsedWith(x2, x1);
        }

        /* Create post-tag relation in search engine */
        String xx = "_" + id + "_";
        String tagNameTemp = tagName.replaceAll("\\s+", "_");
        search.createTopicHasTag(xx, tagNameTemp);


        /* Update edit date of the topic */
        DateTime dateTime = new DateTime(DateTimeZone.forID("Europe/Istanbul"));
        topicRepo.updateEditDate(dateTime, id);

        return this.getById(id);
    }
    /* Addendum: check tag changes */
    @RequestMapping(method = RequestMethod.POST, value = "/id/{id}/update")
    public int editTopic(@RequestBody @Valid Topic topic, @PathVariable("id") int id){

        int ownerId = topic.getOwnerId();
        String title = topic.getTitle();
        DateTime date = new DateTime(DateTimeZone.forID("Europe/Istanbul"));

        int result = topicRepo.editTopic(title, ownerId, date, id);

        /* Find tag changes */
        ArrayList<String> oldTags = this.getById(id).getTags();
        ArrayList<String> newTags = topic.getTags();
        ArrayList<String> temp = new ArrayList<String>(oldTags);

        oldTags.removeAll(newTags); /* Tags to be deleted */
        newTags.removeAll(temp);    /* Tags to be added */

        /* Add new tags */
        if(newTags.size() > 0) {
            for (String tagName: newTags) {

                TagTopicRelation ttr = new TagTopicRelation();

            /* Check whether tag exists */
                Tag tag = tagRepo.findByTagName(tagName);

            /* Save the tag if does not exists */
                if (tag == null) {
                    tag = new Tag();
                    tag.setTagName(tagName);
                    tagRepo.save(tag);
                }

            /* Create tag-topic relation */
                ttr.setTopicId(id);
                ttr.setTagId(tag.getId());
                tagTopicRelationRepo.save(ttr);
            }
        }

        /* Delete old tags */
        if(oldTags.size() > 0){
            for(String tagName: oldTags){

                int tagId = tagRepo.findByTagName(tagName).getId(); /* Find tag id */
                tagTopicRelationRepo.deleteByTagId(tagId);          /* Delete tag-topic relation */
            }
        }

        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "get_topics")
    public ArrayList<TopicResponse> getConnections(){

        ArrayList<Topic> topics = this.getAll();

        ArrayList<TopicResponse> response = new ArrayList<>();

        for(Topic topic: topics){

            TopicResponse tr = new TopicResponse();

            ArrayList<PostTopicRelation> postRelations = postTopicRepo.findByTopicId(topic.getId());

            int value = topic.getValue() + (postRelations.size() * 4) / 2 + topic.getUpVote() + topic.getDownVote();
            if(value > 150) value = 150;
            if(value < 10) value = 10;

            topic.setValue(value);

            tr.setId(topic.getId());
            tr.setValue(topic.getValue());
            tr.setTitle(topic.getTitle());
            tr.setLabel(topic.getLabel());
            tr.setGroup(topic.getGroup());

            response.add(tr);
        }

        return response;
    }

}

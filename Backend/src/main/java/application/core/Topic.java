package application.core;

import application.miscalleneous.Result;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue
    private int id;

    /**
     * result of the process: success or fail
     */
    @Transient
    Result result;

    /**
     * Id of the owner of topic
     */
    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "title")
    private String title;

    /**
     * number of positive votes to topic
     */
    @Column(name = "up_vote")
    private int upVote = 0;

    /**
     * number of negative votes to topic
     */
    @Column(name = "down_vote")
    private int downVote = 0;

    @Column(name = "create_date")
    private DateTime createDate;

    @Column(name = "edit_date")
    private DateTime editDate;

    @Transient
    private String label;

    /**
     * list of tags to topic
     */
    @Transient
    private ArrayList<String> tags;

    /**
     * list of post in the topic
     */
    @Transient
    private ArrayList<Post> posts;

    @Transient
    private ArrayList<Integer> topicRelations;

    @Column(name = "_group")
    private String group;

    @Column(name = "value")
    private int value = 20;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Integer> getTopicRelations() {
        return topicRelations;
    }

    public void setTopicRelations(ArrayList<Integer> topicRelations) {
        this.topicRelations = topicRelations;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    /**
     *
     * @return
     */
    public DateTime getEditDate() {
        return editDate;
    }

    /**
     *
     * @param editDate
     */
    public void setEditDate(DateTime editDate) {
        this.editDate = editDate;
    }

    /**
     *
     * @return
     */
    public String getGroup() {
        return group;
    }

    /**
     *
     * @param group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }
}

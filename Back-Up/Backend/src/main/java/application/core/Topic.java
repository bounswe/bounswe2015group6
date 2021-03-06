package application.core;

import application.miscalleneous.EdgeResponse;
import application.miscalleneous.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue
    private int id;

    @Transient
    Result result;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "title")
    private String title;

    @Column(name = "up_vote")
    private int upVote = 0;

    @Column(name = "down_vote")
    private int downVote = 0;

    @Column(name = "create_date")
    private DateTime createDate;

    @Column(name = "edit_date")
    private DateTime editDate;

    @Transient
    private String label;

    @Transient
    private ArrayList<String> tags;

    @Transient
    private ArrayList<Post> posts;

    @Transient
    private ArrayList<EdgeResponse> toRelated;

    @Transient
    private ArrayList<EdgeResponse> fromRelated;

    @Column(name = "_group")
    private String group;

    @Column(name = "value")
    private int value = 10;

    @Transient
    private String username;


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

    public DateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(DateTime editDate) {
        this.editDate = editDate;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<EdgeResponse> getFromRelated() {
        return fromRelated;
    }

    public void setFromRelated(ArrayList<EdgeResponse> fromRelated) {
        this.fromRelated = fromRelated;
    }

    public ArrayList<EdgeResponse> getToRelated() {
        return toRelated;
    }

    public void setToRelated(ArrayList<EdgeResponse> toRelated) {
        this.toRelated = toRelated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

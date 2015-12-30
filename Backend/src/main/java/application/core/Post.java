package application.core;

import application.miscalleneous.Result;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "post")
public class Post {

    /* Result of action */
    @Transient
    Result result;

    /**
     * id of the post
     */
    @Id
    @GeneratedValue
    private int id;

    @Transient
    private int topicId;

    /* Add post owner id*/
    @Transient
    private int ownerId;

    @Column(name = "up_vote")
    private int upVote = 0;

    @Column(name = "down_vote")
    private int downVote = 0;

    /**
     * content of the post
     */
    @Column(name = "content")
    private String content;

    @Column(name = "creation_date")
    private DateTime date;

    @Column(name = "edit_date")
    private DateTime editDate;

    @Transient
    private ArrayList<String> tagsOfPost = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getTagsOfPost() {
        return tagsOfPost;
    }

    public void setTagsOfPost(ArrayList<String> tagsOfPost) {
        this.tagsOfPost = tagsOfPost;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public DateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(DateTime editDate) {
        this.editDate = editDate;
    }
}


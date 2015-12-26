package application.core;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Baris on 23.11.2015.
 */
@Entity
@Table(name="topic")

public class Comment {

    @Id
    @GeneratedValue
    int id;


    /**
     * owner of the comment(registered user)
     */
    @Column(name="owner")
    RegisteredUser owner = new RegisteredUser();

    /**
     * topic that includes the comment
     */
    @Column(name="topic")
    Topic topic = new Topic();

    /**
     * tag list of the comment
     */
    @Transient
    ArrayList<Tag> tags = new ArrayList<>();
    @Transient

    /**
     * number of people who likes
     */
    @Column(name="positiveRate")
    int positiveRate;

    /**
     * number of people who dislikes
     */
    @Column(name="negativeRate")
    int negativeRate;

    /**
     * content of the comment
     */
    @Column(name="content")
    String content;

    ArrayList<Comment> comments = new ArrayList<>();

    @Column(name="date")
    String date;

    //getters and setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Comment> getComment() {
        return comments;
    }

    public void setComment(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RegisteredUser getOwner() {
        return owner;
    }

    public void setOwner(RegisteredUser owner) {
        this.owner = owner;
    }

    public int getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(int positiveRate) {
        this.positiveRate = positiveRate;
    }

    public int getNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(int negativeRate) {
        this.negativeRate = negativeRate;
    }
}


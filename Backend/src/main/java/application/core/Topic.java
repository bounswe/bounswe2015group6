package application.core;

import application.miscalleneous.Result;

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

    @Transient
    private ArrayList<Integer> tags;


    @Transient
    private ArrayList<Integer> posts;


    @Transient
    private ArrayList<Integer> topicRelations;


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

    public ArrayList<Integer> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Integer> tags) {
        this.tags = tags;
    }

    public ArrayList<Integer> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Integer> posts) {
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
}

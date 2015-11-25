package application.core;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "title")
    private String title;

    @Transient
    private ArrayList<Integer> tags;


    @Transient
    private ArrayList<Post> posts;


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
}

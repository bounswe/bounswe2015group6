package application.core;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import java.util.ArrayList;

/**
 * Created by Baris on 18.11.2015.
 */
@Entity
@Table(name="topic")

public class Topic {

    @Id
    @GeneratedValue
    int id;


    @Column(name="title")
    String title;
    @Transient
    ArrayList<Post> content = new ArrayList<>();
    @Transient
    ArrayList<Tag> tag = new ArrayList<>();
    @Transient
    ArrayList<Topic> related = new ArrayList<>();

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Post> getContent() {
        return content;
    }

    public void setContent(ArrayList<Post> content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Tag> getTag() {
        return tag;
    }

    public void setTag(ArrayList<Tag> tag) {
        this.tag = tag;
    }

    public ArrayList<Topic> getRelated() {
        return related;
    }
    public void setRelated(ArrayList<Topic> related) {
        this.related = related;
    }


}

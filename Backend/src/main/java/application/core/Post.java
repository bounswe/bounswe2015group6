package application.core;

/**
 * Created by Oguzhan on 18.11.2015.
 */

import javax.persistence.*;
import application.miscalleneous.Result;
import java.util.ArrayList;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "content")
    private String content;

    @Transient
    private ArrayList<Tag> tags;

    @Transient
    private User user;

    @Transient
    Result result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent(){return content;}

    public void setContent(String content) {this.content = content;}

    public ArrayList<Tag> getTags() {return tags;}

    public void setTags(ArrayList<Tag> tags){this.tags = tags;}

    public User getUser(){return user;}

    public void setUser(User user){this.user = user;}

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


}

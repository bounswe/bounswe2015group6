package application.core;

import application.miscalleneous.Result;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "posts")
public class Post {

    /* Result of action */
    @Transient
    Result result;

    @Id
    @GeneratedValue
    int id;

    /* Add post owner id*/
    @Transient
    int ownerId;

    @Column(name = "content")
    String content;

    @Transient
    ArrayList<Integer> tagsOfPost = new ArrayList<Integer>();

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

    public ArrayList<Integer> getTagsOfPost() {
        return tagsOfPost;
    }

    public void setTagsOfPost(ArrayList<Integer> tagsOfPost) {
        this.tagsOfPost = tagsOfPost;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

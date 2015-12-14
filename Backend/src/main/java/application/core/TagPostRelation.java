package application.core;

import javax.persistence.*;

@Entity
@Table(name = "tag_post_relation")
public class TagPostRelation {
    @Id
    @GeneratedValue
    int id;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "tag_id")
    private int tagId;

    public TagPostRelation(){
        this.postId = -1;
        this.tagId = -1;
    }

    public TagPostRelation(int postId, int tagId){
        this.postId = postId;
        this.tagId = tagId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {this.postId = postId;    }
}

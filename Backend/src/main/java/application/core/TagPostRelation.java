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

    /**
     *
     * empty relation constructor
     */
    public TagPostRelation(){
        this.postId = -1;
        this.tagId = -1;
    }

    /**
     *
     * @param postId
     * @param tagId
     */
    public TagPostRelation(int postId, int tagId){
        this.postId = postId;
        this.tagId = tagId;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getTagId() {
        return tagId;
    }

    /**
     *
     * @param tagId
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    /**
     *
     * @return
     */
    public int getPostId() {
        return postId;
    }

    /**
     *
     * @param postId
     */
    public void setPostId(int postId) {this.postId = postId;    }
}

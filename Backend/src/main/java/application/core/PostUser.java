package application.core;

import javax.persistence.*;

/**
 * Created by User on 22.11.2015.
 */

@Entity
@Table(name = "post_user")
public class PostUser {

    @Id
    @GeneratedValue
    int id;

    @Column(name = "owner_id")
    int ownerId;

    @Column(name = "post_id")
    int postId;

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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}

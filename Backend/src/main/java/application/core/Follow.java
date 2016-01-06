package application.core;

import javax.persistence.*;

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue
    private int id;

    @Column(name  = "followed_id")
    private int followedId;

    @Column(name = "follower_id")
    private int followerId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowed() {
        return followedId;
    }

    public void setFollowed(int followed) {
        this.followedId = followed;
    }

    public int getFollower() {
        return followerId;
    }

    public void setFollower(int follower) {
        this.followerId = follower;
    }
}

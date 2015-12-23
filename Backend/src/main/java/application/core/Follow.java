package application.core;

import javax.persistence.*;

@Entity
@Table(name = "follow")
public class Follow {

    /**
     * id of "follow" relationship
     */
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

    /**
     *
     * @return id of user who is followed
     */
    public int getFollowed() {
        return followedId;
    }

    /**
     *
     * @param followed set id of user who is followed
     */
    public void setFollowed(int followed) {
        this.followedId = followed;
    }

    /**
     *
     * @return id of user who follows
     */
    public int getFollower() {
        return followerId;
    }

    /**
     *
     * @param follower id of user who will be added as follower
     */
    public void setFollower(int follower) {
        this.followerId = follower;
    }
}

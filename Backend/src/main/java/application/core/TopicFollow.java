package application.core;

import javax.persistence.*;

@Entity
@Table(name = "topic_follow")
public class TopicFollow {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "follower_id")
    private int followerId;

    @Column(name = "topic_id")
    private int topicId;

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}

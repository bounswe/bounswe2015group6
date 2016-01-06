package application.core;

import application.miscalleneous.Result;

import javax.persistence.*;

@Entity
@Table(name = "topic_follow")
public class TopicFollow {

    @Id
    @GeneratedValue
    private int id;

    @Transient
    private Result result;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

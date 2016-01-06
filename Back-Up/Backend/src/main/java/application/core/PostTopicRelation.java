package application.core;

import javax.persistence.*;

@Entity
@Table(name = "post_topic_relation")
public class PostTopicRelation {

    @Id
    @GeneratedValue
    int id;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "topic_id")
    private int topicId;

    public PostTopicRelation(){
        this.postId = -1;
        this.topicId = -1;
    }

    public PostTopicRelation(int postId, int topicId){
        this.postId = postId;
        this.topicId = topicId;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {this.postId = postId;    }


}

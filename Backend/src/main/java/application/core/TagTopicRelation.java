package application.core;

import javax.persistence.*;

@Entity
@Table(name = "tag_relation")
public class TagTopicRelation {

    @Id
    @GeneratedValue
    int id;

    @Column(name = "topic_id")
    private int topicId;

    @Column(name = "tag_id")
    private int tagId;

    public TagTopicRelation(){
        this.topicId = -1;
        this.tagId = -1;
    }

    public TagTopicRelation(int topicId, int tagId){
        this.topicId = topicId;
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

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}

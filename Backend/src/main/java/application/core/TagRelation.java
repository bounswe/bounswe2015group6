package application.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tag_relation")
public class TagRelation {

    @Column(name = "topic_id")
    private int topicId;

    @Column(name = "tag_id")
    private int tagId;

    public TagRelation(){
        this.topicId = -1;
        this.tagId = -1;
    }

    public TagRelation(int topicId, int tagId){
        this.topicId = topicId;
        this.tagId = tagId;
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

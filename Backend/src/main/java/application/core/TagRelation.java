package application.core;

import javax.persistence.*;



/**
 *
 * This class is a draft and
 * will not be used.
 *
 *
 */
@Entity
@Table(name = "tag_relation")
public class TagRelation {
    /**
     * id of the relation
     */
    @Id
    @GeneratedValue
    int id;

    /**
     *  id of the topic
     */
    @Column(name = "topic_id")
    private int topicId;

    /**
     * id of the tag
     */
    @Column(name = "tag_id")
    private int tagId;

    /**
     * empty constructor
     */
    public TagRelation(){
        this.topicId = -1;
        this.tagId = -1;
    }

    /**
     *
     * @param topicId
     * @param tagId
     */
    public TagRelation(int topicId, int tagId){
        this.topicId = topicId;
        this.tagId = tagId;
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
    public int getTopicId() {
        return topicId;
    }

    /**
     *
     * @param topicId
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}

package application.core;

import application.miscalleneous.Result;

import javax.persistence.*;

/**
 *
 * Tag class keeps each individual tag and relate to posts and topics with
 * TagPostRelation and TagTopicRelation classes. Relations between tags are
 * realized by TagRelation class.
 *
 */
@Entity
@Table(name = "tag")
public class Tag{

    @Transient
    Result result;

    /**
     * id of the tag
     */
    @Id
    @GeneratedValue
    int id;

    @Column(name ="tag_name")
    String tagName;

    /**
     * empty constructor
     */
    public Tag(){
        this.tagName = "";
    }

    /**
     *
     * @param tagName
     */
    public Tag(String tagName){
        this.tagName = tagName;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

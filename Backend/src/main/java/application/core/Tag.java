package application.core;

import application.miscalleneous.Result;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag{

    @Transient
    Result result;

    @Id
    @GeneratedValue
    int id;

    @Column(name ="tag_name")
    String tagName;

    public Tag(){
        this.tagName = "";
    }

    public Tag(String tagName){
        this.tagName = tagName;
    }

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

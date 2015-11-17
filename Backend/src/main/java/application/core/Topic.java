package application.core;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "title")
    private String title;

    @Transient
    private ArrayList<Integer> tags;


    /*
    @Transient
    private ArrayList<Post> posts;
    */

    /*
    @Transient
    private ArrayList<Integer> topicRelations;
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

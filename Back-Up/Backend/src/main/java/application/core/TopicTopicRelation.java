package application.core;

import application.miscalleneous.Result;

import javax.persistence.*;

@Entity
@Table(name = "topic_topic_relation")
public class TopicTopicRelation {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "from_id")
    private int from;

    @Column(name = "to_id")
    private int to;

    @Column(name = "label")
    private String label;

    @Column(name = "title")
    private String title;

    @Transient
    private String arrows = "to";

    public TopicTopicRelation(){
        this.from= -1;
        this.to= -1;
    }

    public TopicTopicRelation(int from, int to){
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {this.to = to;}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArrows() {
        return arrows;
    }

    public void setArrows(String arrows) {
        this.arrows = arrows;
    }
}

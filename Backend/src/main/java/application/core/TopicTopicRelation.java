package application.core;

import application.miscalleneous.Result;

import javax.persistence.*;

@Entity
@Table(name = "topic_topic_relation")
public class TopicTopicRelation {

    @Id
    @GeneratedValue
    private int id;

    /**
     * id of the topic that is subject of relationship
     */
    @Column(name = "from_id")
    private int from;

    /**
     * if of the topic that is object of the relationship
     */
    @Column(name = "to_id")
    private int to;

    @Column(name = "label")
    private String label;

    @Column(name = "title")
    private String title;

    @Transient
    private String arrows = "to";

    /**
     * empty relation constructor
     */
    public TopicTopicRelation(){
        this.from= -1;
        this.to= -1;
    }

    public TopicTopicRelation(int from, int to){
        this.from = from;
        this.to = to;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getFrom() {
        return from;
    }

    /**
     *
     * @param from
     */
    public void setFrom(int from) {
        this.from = from;
    }

    /**
     *
     * @return
     */
    public int getTo() {
        return to;
    }

    /**
     *
     * @param to
     */
    public void setTo(int to) {this.to = to;}

    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getArrows() {
        return arrows;
    }

    /**
     *
     * @param arrows
     */
    public void setArrows(String arrows) {
        this.arrows = arrows;
    }
}

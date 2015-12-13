package application.core;

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
}

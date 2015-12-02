package application.core;

import javax.persistence.*;

@Entity
@Table(name = "topic_topic_relation")
public class TopicTopicRelation {
    @Id
    @GeneratedValue
    int id;

    @Column(name = "source_id")
    private int sourceId;

    @Column(name = "target_id")
    private int targetId;

    public TopicTopicRelation(){
        this.sourceId= -1;
        this.targetId= -1;
    }

    public TopicTopicRelation(int sourceId, int targetId){
        this.sourceId = sourceId;
        this.targetId = targetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId= sourceId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {this.targetId= targetId;    }
}

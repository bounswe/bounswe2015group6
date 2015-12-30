package application.core;

import javax.persistence.*;



/**
 * Created by Baris on 29.12.2015.
 *
 *
 * This class relates tags to tags.
 *
 *
 */
@Entity
@Table(name = "tag_tag_relation")
public class TagTagRelation {
    /**
     * id of the relation
     */
    @Id
    @GeneratedValue
    int id;

    /**
     *  id of the tag1
     */
    @Column(name = "tag1_id")
    private int tag1Id;

    /**
     * id of tag2
     */
    @Column(name = "tag2_id")
    private int tag2Id;

    /**
     * this variable is for further use,
     * it keeps how strong is a relationship between two tags,
     * it can take a value between 0 and 100
     */
    @Column(name = "relation_strenth")
    private int relation_strenth;


    /**
     *
     * empty constuctor
     */
    public TagTagRelation(){
        this.tag1Id = -1;
        this.tag2Id = -1;
    }

    /**
     * constructor 2
     * @param tag1Id
     * @param tag2Id
     */
    public TagTagRelation(int tag1Id, int tag2Id){
        this.tag1Id = tag1Id;
        this.tag2Id = tag2Id;
    }


    /**
     *
     * @return
     */
    public int getTag1Id() {
        return tag1Id;
    }

    /**
     *
     * @param tag1Id
     */
    public void setTag1Id(int tag1Id) {
        this.tag1Id = tag1Id;
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
    public int getId() {
        return id;
    }

    /**
     *
     * @param tag2Id
     */
    public void setTag2Id(int tag2Id) {
        this.tag2Id = tag2Id;
    }

    /**
     *
     * @return
     */
    public int getTag2Id() {
        return tag2Id;
    }

    /**
     *
     * @param relation_strenth
     */
    public void setRelation_strenth(int relation_strenth) {
        this.relation_strenth = relation_strenth;
    }

    /**
     *
     * @return
     */
    public int getRelation_strenth() {
        return relation_strenth;
    }
}

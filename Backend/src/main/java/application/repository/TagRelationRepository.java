package application.repository;

import application.core.TagRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


/**
 * Repository for tag relations
 */
public interface TagRelationRepository extends CrudRepository<TagRelation, Integer>{

    /**
     * returns all tag relations
     * @return
     */
    ArrayList<TagRelation> findAll();

    /**
     * finds tags of any topic by topic id
     * @param topicId
     * @return
     */
    ArrayList<TagRelation> findByTopicId(int topicId);

    /**
     * finds the tag by tag id
     * @param TagId
     * @return
     */
    ArrayList<TagRelation> findByTagId(int TagId);

}

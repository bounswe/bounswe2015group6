package application.repository;

import application.core.TagTopicRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 *
 */
public interface TagTopicRelationRepository extends CrudRepository<TagTopicRelation, Integer>{

    /**
     * returns all tag topic relations
     * @return
     */
    ArrayList<TagTopicRelation> findAll();

    /**
     * finds all tag relations to a particular topic
     * @param topicId
     * @return
     */
    ArrayList<TagTopicRelation> findByTopicId(int topicId);

    /**
     * finds all tag topic relations by topic id
     * @param TagId
     * @return
     */
    ArrayList<TagTopicRelation> findByTagId(int TagId);

    /**
     * deletes all tag-topic relations of a tag by its id
     * @param tagId
     */
    void deleteByTagId(int tagId);

    /**
     * deletes all tag-topic relations by id of topic
     * @param topicId
     */
    void deleteByTopicId(int topicId);
}

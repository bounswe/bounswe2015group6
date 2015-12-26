package application.repository;

import application.core.TopicTopicRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * class is a repository for relationship between topics
 */
public interface TopicTopicRelationRepository extends CrudRepository<TopicTopicRelation, Integer> {
    ArrayList<TopicTopicRelation> findAll();
    ArrayList<TopicTopicRelation> findByFrom(int from);
    ArrayList<TopicTopicRelation> findByTo(int to);
    TopicTopicRelation findById(int id);
    TopicTopicRelation findByFromAndTo(int from, int to);

    /**
     *
     * @param from
     * @param to
     */
    void deleteByFromAndTo(int from, int to);

    /**
     *
     * @param Id
     */
    void deleteById(int Id);

    /**
     *
     * @param from
     */
    void deleteByFrom(int from);

    /**
     *
     * @param to
     */
    void deleteByTo(int to);
}

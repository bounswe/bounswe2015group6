package application.repository;

import application.core.TopicTopicRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface TopicTopicRelationRepository extends CrudRepository<TopicTopicRelation, Integer> {
    ArrayList<TopicTopicRelation> findAll();
    ArrayList<TopicTopicRelation> findByFrom(int from);
    ArrayList<TopicTopicRelation> findByTo(int to);
    TopicTopicRelation findById(int id);
    TopicTopicRelation findByFromAndTo(int from, int to);
    void deleteByFromAndTo(int from, int to);
    void deleteById(int Id);
    void deleteByFrom(int from);
    void deleteByTo(int to);
}

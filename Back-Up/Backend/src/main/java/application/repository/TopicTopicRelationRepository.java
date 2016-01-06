package application.repository;

import application.core.TopicTopicRelation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


public interface TopicTopicRelationRepository extends CrudRepository<TopicTopicRelation, Integer> {
    ArrayList<TopicTopicRelation> findAll();
    ArrayList<TopicTopicRelation> findByFrom(int from);
    ArrayList<TopicTopicRelation> findByTo(int to);
    TopicTopicRelation findById(int id);
    TopicTopicRelation findByFromAndTo(int from, int to);

    @Modifying
    @Transactional
    Integer deleteByFromAndTo(int from, int to);

    @Modifying
    @Transactional
    Integer deleteById(int Id);

    @Modifying
    @Transactional
    Integer deleteByFrom(int from);

    @Modifying
    @Transactional
    Integer deleteByTo(int to);
}

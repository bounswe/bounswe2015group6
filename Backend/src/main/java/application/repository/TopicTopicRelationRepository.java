package application.repository;

import application.core.TopicTopicRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface TopicTopicRelationRepository extends CrudRepository<TopicTopicRelation, Integer> {
    ArrayList<TopicTopicRelation> findAll();
    ArrayList<TopicTopicRelation> findBySourceId(int SourceId);
    ArrayList<TopicTopicRelation> findByTargetId(int TargetId);
    void deleteBySourceIdAndTargetId(int SourceId, int TargetId);
    void deleteById(int Id);
}

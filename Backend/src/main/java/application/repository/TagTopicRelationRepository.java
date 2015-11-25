package application.repository;

import application.core.TagTopicRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TagTopicRelationRepository extends CrudRepository<TagTopicRelation, Integer>{
    ArrayList<TagTopicRelation> findAll();
    ArrayList<TagTopicRelation> findByTopicId(int topicId);
    ArrayList<TagTopicRelation> findByTagId(int TagId);
}

package application.repository;

import application.core.TagRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TagRelationRepository extends CrudRepository<TagRelation, Integer>{
    ArrayList<TagRelation> findAll();
    ArrayList<TagRelation> findByTopicId(int topicId);
    ArrayList<TagRelation> findByTagId(int TagId);
}

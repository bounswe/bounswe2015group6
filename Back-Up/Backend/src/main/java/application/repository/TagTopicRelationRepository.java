package application.repository;

import application.core.TagTopicRelation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface TagTopicRelationRepository extends CrudRepository<TagTopicRelation, Integer>{
    ArrayList<TagTopicRelation> findAll();
    ArrayList<TagTopicRelation> findByTopicId(int topicId);
    ArrayList<TagTopicRelation> findByTagId(int TagId);

    @Modifying
    @Transactional
    Integer deleteByTagId(int tagId);

    @Modifying
    @Transactional
    Integer deleteByTopicId(int topicId);
}

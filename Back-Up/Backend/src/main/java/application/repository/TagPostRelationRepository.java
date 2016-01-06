package application.repository;

import application.core.TagPostRelation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface TagPostRelationRepository extends CrudRepository<TagPostRelation, Integer>{
    ArrayList<TagPostRelation> findAll();
    ArrayList<TagPostRelation> findByPostId(int postId);
    ArrayList<TagPostRelation> findByTagId(int tagId);

    @Modifying
    @Transactional
    Integer deleteByTagId(int tagId);

    @Modifying
    @Transactional
    Integer deleteByPostId(int postId);
}

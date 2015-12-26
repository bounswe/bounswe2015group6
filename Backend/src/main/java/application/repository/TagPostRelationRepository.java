package application.repository;

import application.core.TagPostRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Class for relations between tags and posts
 */
public interface TagPostRelationRepository extends CrudRepository<TagPostRelation, Integer>{
    ArrayList<TagPostRelation> findAll();
    ArrayList<TagPostRelation> findByPostId(int postId);
    ArrayList<TagPostRelation> findByTagId(int tagId);

    /**
     * delete relation by tag id
     * @param tagId
     */
    void deleteByTagId(int tagId);

    /**
     * delete relation by postid
     * @param postId
     */
    void deleteByPostId(int postId);
}

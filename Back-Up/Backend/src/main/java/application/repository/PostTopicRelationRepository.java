package application.repository;

import application.core.PostTopicRelation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface PostTopicRelationRepository extends CrudRepository<PostTopicRelation, Integer>{
    ArrayList<PostTopicRelation> findAll();
    PostTopicRelation findByPostId(int postId);
    ArrayList<PostTopicRelation> findByTopicId(int TopicId);

    @Modifying
    @Transactional
    Integer deleteByPostId(int postId);

    @Modifying
    @Transactional
    Integer deleteByTopicId(int topicId);
}

package application.repository;

import application.core.PostTopicRelation;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface PostTopicRelationRepository extends CrudRepository<PostTopicRelation, Integer>{
    ArrayList<PostTopicRelation> findAll();
    PostTopicRelation findByPostId(int postId);
    ArrayList<PostTopicRelation> findByTopicId(int TopicId);
    void deleteByPostId(int postId);
    void deleteByTopicId(int topicId);
}

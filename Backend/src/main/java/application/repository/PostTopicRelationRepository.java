package application.repository;

import application.core.PostTopicRelation;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

/**
 * Repository to relate posts to topics
 */
public interface PostTopicRelationRepository extends CrudRepository<PostTopicRelation, Integer>{
    ArrayList<PostTopicRelation> findAll();
    PostTopicRelation findByPostId(int postId);
    ArrayList<PostTopicRelation> findByTopicId(int TopicId);

    /**
     * deleter relationship by post id
     * @param postId
     */
    void deleteByPostId(int postId);

    /**
     * delete relationship by topic id
     * @param topicId
     */
    void deleteByTopicId(int topicId);
}

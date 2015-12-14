package application.repository;

import application.core.TopicFollow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * Created by User on 13.12.2015.
 */
public interface TopicFollowRepository extends CrudRepository<TopicFollow, Integer> {

    @Query("SELECT tf.topicId FROM TopicFollow tf WHERE tf.followerId = :followerId")
    ArrayList<Integer> getByTopicId(@Param("followerId") int followerId);

    @Query("SELECT tf.followerId FROM TopicFollow tf WHERE tf.topicId = :topicId")
    ArrayList<Integer> getByFollowerId(@Param("topicId") int topicId);
}

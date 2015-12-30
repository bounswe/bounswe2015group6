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

    /**
     *
     * @param followerId
     * @return
     */
    @Query("SELECT tf.topicId FROM TopicFollow tf WHERE tf.followerId = :followerId")
    ArrayList<Integer> getByFollowerId(@Param("followerId") int followerId);

    /**
     *
     * @param topicId
     * @return
     */
    @Query("SELECT tf.followerId FROM TopicFollow tf WHERE tf.topicId = :topicId")
    ArrayList<Integer> getByTopicId(@Param("topicId") int topicId);
}

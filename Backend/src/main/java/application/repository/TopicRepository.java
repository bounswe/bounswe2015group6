package application.repository;

import application.core.Topic;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by User on 30.11.2015.
 */
public interface TopicRepository extends CrudRepository<Topic, Integer> {

    ArrayList<Topic> findAll();
    Topic findById(int id);
    Topic findByTitle(String title);
    ArrayList<Topic> findByOwnerId(int ownerId);

    @Query("SELECT t FROM Topic t WHERE t.editDate <= :editDate ORDER BY t.editDate DESC")
    ArrayList<Topic> findRecentTopics(@Param("editDate")DateTime editDate);

    @Query("SELECT t FROM Topic t ORDER BY t.upVote+t.downVote DESC")
    ArrayList<Topic> findPopularTopics();

    @Query("SELECT t FROM Topic t WHERE t.ownerId = :ownerId")
    ArrayList<Topic> findTopicByOwnerId(@Param("ownerId") int ownerId);

    @Modifying
    @Transactional
    @Query("UPDATE Topic t SET t.upVote = t.upVote + 1 WHERE t.id = :id")
    int updateUpVote(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Topic t SET t.downVote = t.downVote - 1 WHERE t.id = :id")
    int updateDownVote(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Topic t SET t.editDate = :editDate WHERE t.id = :id")
    DateTime updateEditDate(@Param("editDate") DateTime editDate, @Param("id") int id);
}

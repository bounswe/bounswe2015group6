package application.repository;

import application.core.Feed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * Created by User on 17.12.2015.
 */
public interface FeedRepository extends CrudRepository <Feed, Integer>{

    @Query("SELECT f FROM Feed f ORDER BY f.date")
    ArrayList<Feed> getAll();

    Feed findById(int id);

    /**
     *
     * @param userId
     * @return
     */
    ArrayList<Feed> findByUserId(int userId);

    /**
     *
     * @param id
     * @return
     */
    @Query("SELECT f FROM Feed f, Follow fo WHERE fo.followerId = :id AND f.userId = fo.followedId ORDER BY f.date")
    ArrayList<Feed> findFollowedFeed(@Param("id") int id);

}

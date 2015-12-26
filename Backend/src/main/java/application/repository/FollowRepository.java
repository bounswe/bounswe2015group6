package application.repository;

import application.core.Follow;
import application.core.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * repostory for follow classes
 */
public interface FollowRepository extends CrudRepository<Follow, Integer>{
    ArrayList<Follow> findAll();
    ArrayList<Follow> findByFollowedId(Integer id);
    ArrayList<Follow> findByFollowerId(Integer id);

    /**
     *
     * @param followerId
     * @return
     */
    @Query("SELECT f.followedId FROM Follow f WHERE f.followerId = :followerId")
    ArrayList<Integer> getFollowed(@Param("followerId") Integer followerId);

    /**
     *
     * @param followedId
     * @return
     */
    @Query("SELECT f.followerId FROM Follow f WHERE f.followedId = :followedId ")
    ArrayList<Integer> getFollowers(@Param("followedId") Integer followedId);

    /**
     *
     * @param followedId
     * @return
     */
    @Query("SELECT u.username FROM Follow f, User u WHERE f.followedId = :followedId AND u.id = f.followerId")
    ArrayList<String> getFollowerNames(@Param("followedId") int followedId);

    /**
     *
     * @param followedId
     * @return
     */
    @Query("SELECT u.username FROM Follow f, User u WHERE f.followerId = :followerId AND u.id = f.followedId")
    ArrayList<String> getFollowedNames(@Param("followerId") int followedId);

}

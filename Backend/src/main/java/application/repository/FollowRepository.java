package application.repository;

import application.core.Follow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends CrudRepository<Follow, Integer>{
    List<Follow> findAll();
    List<Follow> findByFollowedId(Integer id);
    List<Follow> findByFollowerId(Integer id);

   // @Query("SELECT follower_id FROM follow WHERE followed_id= :followed_id ")
   // List<Integer> getFollowers(@Param("followed_id") Integer followedId);

}

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

    @Query("SELECT f.followerId FROM Follow f WHERE f.followedId= :followed_id")
    List<Integer> getFollowers(@Param("followed_id") int followedId);

}

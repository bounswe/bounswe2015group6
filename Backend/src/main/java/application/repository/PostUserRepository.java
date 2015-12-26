package application.repository;

import application.core.PostUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


/**
 * Repository which relates posts to users
 */
public interface PostUserRepository extends CrudRepository<PostUser, Integer> {


    ArrayList<PostUser> findAll();
    PostUser findById(int id);
    ArrayList<PostUser> findByOwnerId(int ownerId);
    PostUser findByPostId(int postId);


    /**
     *
     * @param ownerId
     * @param postId
     */
    void deleteByOwnerIdAndPostId(int ownerId, int postId);
}

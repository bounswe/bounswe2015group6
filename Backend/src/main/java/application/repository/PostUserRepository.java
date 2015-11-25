package application.repository;

import application.core.PostUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by User on 22.11.2015.
 */
public interface PostUserRepository extends CrudRepository<PostUser, Integer> {

    ArrayList<PostUser> findAll();
    PostUser findById(int id);
    ArrayList<PostUser> findByOwnerId(int ownerId);
    ArrayList<PostUser> findByPostId(int postId);
    void deleteByOwnerIdAndPostId(int ownerId, int postId);
}

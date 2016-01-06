package application.repository;

import application.core.PostUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface PostUserRepository extends CrudRepository<PostUser, Integer> {

    ArrayList<PostUser> findAll();
    PostUser findById(int id);
    ArrayList<PostUser> findByOwnerId(int ownerId);
    PostUser findByPostId(int postId);

    @Modifying
    @Transactional
    Integer deleteByOwnerIdAndPostId(int ownerId, int postId);
}

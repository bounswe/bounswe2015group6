package application.repository;

import application.core.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by Oguzhan on 18.11.2015.
 */
public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findById(int id);
    ArrayList<Post> findAll();
}

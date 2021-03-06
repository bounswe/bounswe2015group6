package application.repository;

import application.core.Post;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by Oguzhan on 18.11.2015.
 */
public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findById(int id);
    ArrayList<Post> findAll();
    ArrayList<Post> findByContentContaining(String content);

    @Override
    void delete(Post post);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.upVote = p.upVote + 1 WHERE p.id = :id")
    int updateUpVote(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.downVote = p.downVote - 1 WHERE p.id = :id")
    int updateDownVote(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.editDate = :editDate WHERE p.id = :id")
    int updateEditDate(@Param("editDate") DateTime editDate, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.content = :content, p.editDate = :editDate WHERE p.id = :id")
    int updatePost(@Param("id") int id,
                   @Param("content") String content,
                   @Param("editDate") DateTime dateTime);
}

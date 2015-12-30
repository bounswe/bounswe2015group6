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
 * @author: Evren Civelek
 * it is for update functionality of the class
 * and also for voting system. 
 */
public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findById(int id);
    ArrayList<Post> findAll();
<<<<<<< HEAD
 // here, update function updates the upVote
 // and sends it to database. 
=======

    /**
     *
     * @param id
     * @return
     */
>>>>>>> origin/master
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.upVote = p.upVote + 1 WHERE p.id = :id")
    int updateUpVote(@Param("id") int id);

<<<<<<< HEAD

 // here, update function updates the downVote
 // and sends it to database. 

=======
    /**
     *
     * @param id
     * @return
     */
>>>>>>> origin/master
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.downVote = p.downVote - 1 WHERE p.id = :id")
    int updateDownVote(@Param("id") int id);

<<<<<<< HEAD

//here, update function updates or edits the date
// and sends it to database

=======
    /**
     *
     * @param editDate
     * @param id
     * @return
     */
>>>>>>> origin/master
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.editDate = :editDate WHERE p.id = :id")
    int updateEditDate(@Param("editDate") DateTime editDate, @Param("id") int id);

    /**
     *
     * @param id
     * @param content
     * @param dateTime
     * @return
     */
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.content = :content AND p.ownerId = :ownerId AND p.editDate = :editDate WHERE p.id = :id")
    int updatePost(@Param("id") int id,
                   @Param("content") String content,
                   @Param("ownerId") int ownerId,
                   @Param("editDate") DateTime dateTime);
}

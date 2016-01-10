package application.repository;

import application.core.PostVote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * Created by User on 09.01.2016.
 */
public interface PostVoteRepository extends CrudRepository<PostVote, Integer>{

    PostVote findByWhoVotedAndWhatVotedAndVoteType(int whoVoted, int whatVoted, int voteType);

    @Query("SELECT pv.whoVoted FROM PostVote pv WHERE pv.whatVoted = :postId")
    ArrayList<Integer> getVotersByPostId(@Param("postId") int postId);

    @Query("SELECT pv.whatVoted FROM PostVote pv WHERE pv.whoVoted = userId")
    ArrayList<Integer> getPostsByVoterId(@Param("userId") int userId);
}

package application.repository;

import application.core.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * Created by User on 09.01.2016.
 */
public interface VoteRepository extends CrudRepository<Vote, Integer> {

    Vote findByWhoVotedAndWhatVotedAndVoteType(int whoVoted, int whatVoted, int voteType);

    @Query("SELECT v.whoVoted FROM Vote v WHERE v.whatVoted = :topicId")
    ArrayList<Integer> getVoterByTopicId(@Param("topicId") int topicId);

    @Query("SELECT v.whatVoted FROM Vote v WHERE v.whoVoted = :userId")
    ArrayList<Integer> getTopicVotedBy(@Param("userId") int userId);
}

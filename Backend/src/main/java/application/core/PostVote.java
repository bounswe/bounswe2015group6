package application.core;

import application.miscalleneous.Result;

import javax.persistence.*;

/**
 * Created by User on 09.01.2016.
 */

@Entity
@Table(name  = "post_vote")
public class PostVote {

    @Id
    @GeneratedValue
    private int id;

    @Transient
    Result result;

    @Column(name = "user_id")
    private int whoVoted;

    @Column(name = "post_id")
    private int whatVoted;

    @Column(name = "vote_type")
    private int voteType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWhoVoted() {
        return whoVoted;
    }

    public void setWhoVoted(int whoVoted) {
        this.whoVoted = whoVoted;
    }

    public int getWhatVoted() {
        return whatVoted;
    }

    public void setWhatVoted(int whatVoted) {
        this.whatVoted = whatVoted;
    }

    public int getVoteType() {
        return voteType;
    }

    public void setVoteType(int voteType) {
        this.voteType = voteType;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

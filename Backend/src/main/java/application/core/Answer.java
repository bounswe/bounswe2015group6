package application.core;

import org.joda.time.DateTime;


import javax.persistence.*;

/**
 * Created by Baris on 29.12.2015.
 *
 *
 * class for answers to posts.
 *
 */
@Entity
@Table(name = "Answer")
public class Answer {

    @Id
    @GeneratedValue
    private int id;

    /**
     * keeps the user who answers
     */
    @Column(name = "user_id")
    private int userId;

    @Column(name = "type")
    private int type;

    /**
     * text in the answer
     */
    @Column(name = "message")
    private String message;

    /**
     * Id of the post that is answered
     */
    @Column(name = "post_id")
    private int postId;

    @Column(name = "subject")
    private int subject;

    @Column(name = "date")
    private DateTime date;

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     */
    public void setSubject(int subject) {
        this.subject = subject;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public DateTime getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(DateTime date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     *
     * @param postId
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     *
     * @return
     */
    public int getPostId() {
        return postId;
    }
}



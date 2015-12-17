package application.core;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by User on 17.12.2015.
 */
@Entity
@Table(name = "feed")
public class Feed {

    @Id
    @GeneratedValue
    private int id;

    /* Action kim tarafından yapıldı */
    @Column(name = "user_id")
    private int userId;

    /*
    * 1 --> new topic
    * 2 --> new post
    * 3 --> new relation
    * */

    @Column(name = "type")
    private int type;

    @Column(name = "message")
    private String message;

    @Column(name = "subject")
    private int subject;

    @Column(name = "date")
    private DateTime date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

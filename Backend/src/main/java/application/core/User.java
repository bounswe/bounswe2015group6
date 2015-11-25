package application.core;

import application.miscalleneous.Result;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "user")
public class User {

    @Transient
    Result result;

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 6, max = 18)
    private String username;

    @NotNull
    private String password;

    @NotEmpty
    private String email;

    @Column(name = "isBanned")
    private int isBanned = 0;

    @Column(name = "rating")
    private int rating = 0;

    @Column(name = "facebook_id")
    private String facebookId;

    @Column(name = "twitter_id")
    private String twitterId;

    @Column(name = "google_id")
    private String googleId;

    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*getter - setter: username*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*getter - setter: password*/
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*getter - setter: email*/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*getter - setter: facebookId*/
    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    /*Getter - setter : twitterId*/
    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    /*Getter - setter : googleId*/

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    /*Getter - setter: followList*/
    @ElementCollection
    @CollectionTable(name = "follow", joinColumns = @JoinColumn(name = "id", referencedColumnName = "followed_id"))
    @Column(name = "follower_id")
    public List<Integer> getFollowList() {
        return followList;
    }

    public void setFollowList(List<Integer> followList){
        this.followList = followList;
    }

    @Transient
    private List<Integer> followList;

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}

package application.core;

import application.miscalleneous.Result;
import org.hibernate.type.ArrayType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "user")
public class User {

    /**
     * It shows if the process is resulted by Success or Failure
     */
    @Transient
    Result result;

    @Id
    @GeneratedValue
    private int id;

    /**
     * username: it is minimum 6, maximum 18 characters
     */
    @NotNull
    @Size(min = 6, max = 18)
    private String username;

    @NotNull
    private String password;

    @NotEmpty
    private String email;

    /**
     * it is 1, if the user is banned
     */
    @Column(name = "isBanned")
    private int isBanned = 0;

    @Column(name = "rating")
    private int rating = 0;

    @Column(name = "facebook_id")
    private String facebookId = "";

    @Column(name = "twitter_id")
    private String twitterId = "";

    @Column(name = "google_id")
    private String googleId = "";

    @Transient
    private ArrayList<Topic> createdTopics;

    @Transient
    private ArrayList<Integer> followedTopics;

    @Transient
    private ArrayList<Post> createdPosts;

    @Transient
    private ArrayList<String> followedUsers;

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

    public List<String> getFollowList() {
        return followList;
    }

    public void setFollowList(List<String> followList){
        this.followList = followList;
    }

    @Transient
    private List<String> followList;

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public ArrayList<Post> getCreatedPosts() {
        return createdPosts;
    }

    public void setCreatedPosts(ArrayList<Post> createdPosts) {
        this.createdPosts = createdPosts;
    }

    public ArrayList<Topic> getCreatedTopics() {
        return createdTopics;
    }

    /**
     *
     * @param createdTopics
     */
    public void setCreatedTopics(ArrayList<Topic> createdTopics) {
        this.createdTopics = createdTopics;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getFollowedTopics() {
        return followedTopics;
    }

    /**
     *
     * @param followedTopics
     */
    public void setFollowedTopics(ArrayList<Integer> followedTopics) {
        this.followedTopics = followedTopics;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getFollowedUsers() {
        return followedUsers;
    }

    /**
     *
     * @param followedUsers
     */
    public void setFollowedUsers(ArrayList<String> followedUsers) {
        this.followedUsers = followedUsers;
    }
}

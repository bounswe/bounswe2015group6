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

    /**
     *
     * keeps interests of the user
     */
    @Transient
    private ArrayList<String> interests;

    /**
     *keeps topics created by user
     */
    @Transient
    private ArrayList<Topic> createdTopics;
    /**
     *keeps topics followed by user
     */
    @Transient
    private ArrayList<Integer> followedTopics;
    /**
     *keeps posts created by user
     */
    @Transient
    private ArrayList<Post> createdPosts;

    /**
     * followed users
     */
    @Transient
    private ArrayList<String> followedUsers;

    /**
     * followers of the user
     */
    @Transient
    private ArrayList<String> followers;

    /**
     *keeps location of user
     */
    @Transient
    private String location;

    /**
     *keeps school of user
     */
    @Transient
    private String school;

    /**
     *keeps profession of user
     */
    @Transient
    private String job;

    /**
     *keeps birthday of user
     */
    @Transient
    private Date birthday;

    /**
     *
     * @return
     */
    public int getIsBanned() {
        return isBanned;
    }

    /**
     *
     * @param isBanned
     */
    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    /**
     *
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

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

    /**
     *
     * @param password
     */
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

    /**
     *
     * @param facebookId
     */
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    /*Getter - setter : twitterId*/
    public String getTwitterId() {
        return twitterId;
    }

    /**
     *
     * @param twitterId
     */
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    /*Getter - setter : googleId*/

    /**
     *
     * @return
     */
    public String getGoogleId() {
        return googleId;
    }

    /**
     *
     * @param googleId
     */
    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    /**
     *
     * @return
     */
    public List<String> getFollowList() {
        return followList;
    }

    /**
     *
     * @param followList
     */
    public void setFollowList(List<String> followList){
        this.followList = followList;
    }

    /**
     *
     */
    @Transient
    private List<String> followList;

    /**
     *
     * @param result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     *
     * @return
     */
    public Result getResult() {
        return result;
    }

    /**
     *
     * @return
     */
    public ArrayList<Post> getCreatedPosts() {
        return createdPosts;
    }

    /**
     *
     * @param createdPosts
     */
    public void setCreatedPosts(ArrayList<Post> createdPosts) {
        this.createdPosts = createdPosts;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param followers
     */
    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getFollowers() {
        return followers;
    }

    /**
     *
     * @param interests
     */
    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getInterests() {
        return interests;
    }

    public String getLocation(){return location;}

    public void setLocation(String location){this.location=location;}

    public Date getBirthday(){return birthday;}

    public void setBirthday(Date birthday){this.birthday = birthday;}

    public String getSchool(){return school;}

    public void setSchool(String school){this.school = school;}

    public String getJob(){return job;}

    public void setJob(String job){this.job = job;}
}

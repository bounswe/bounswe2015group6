package application.miscalleneous;

import application.core.Feed;
import application.core.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 05.01.2016.
 */
public class ProfileResponse {

    String username;
    ArrayList<FeedResponse> feeds = new ArrayList<>();
    ArrayList<FeedResponse> actions = new ArrayList<>();
    Map<Integer, String> following = new HashMap<>();
    Map<Integer, String> followers = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<FeedResponse> getFeeds() {
        return feeds;
    }


    public void setFeeds(ArrayList<FeedResponse> feeds) {
        this.feeds = feeds;
    }

    public Map<Integer, String> getFollowers() {
        return followers;
    }

    public void setFollowers(Map<Integer, String> followers) {
        this.followers = followers;
    }

    public Map<Integer, String> getFollowing() {
        return following;
    }

    public void setFollowing(Map<Integer, String> following) {
        this.following = following;
    }

    public ArrayList<FeedResponse> getActions() {
        return actions;
    }

    public void setActions(ArrayList<FeedResponse> actions) {
        this.actions = actions;
    }
}

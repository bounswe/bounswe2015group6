package application.miscalleneous;

import application.core.Feed;

/**
 * Created by User on 17.12.2015.
 */
public class FeedResponse {

    Feed feed;
    String username;
    String subjectName;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}

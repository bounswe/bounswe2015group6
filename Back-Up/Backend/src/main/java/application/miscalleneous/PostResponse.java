package application.miscalleneous;

import application.core.Post;

import java.util.ArrayList;

/**
 * Created by User on 12.12.2015.
 */
public class PostResponse {
    ArrayList<Post> posts = new ArrayList<>();

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}

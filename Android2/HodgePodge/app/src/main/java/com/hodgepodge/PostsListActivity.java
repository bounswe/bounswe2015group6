package com.hodgepodge;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PostsListActivity extends AppCompatActivity {
    ListView postsListView;
    Context context = PostsListActivity.this;
    ArrayList<PostListData> postsList = new ArrayList<PostListData>();

    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String> content = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<Integer> rating = new ArrayList<>();
    ArrayList<String> username = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);

        Bundle extras = getIntent().getExtras();
        int topicId = extras.getInt("id");
        String titleStr = extras.getString("title");
        setTitle(titleStr);

        ServiceClient.get("topic/id/getpost/" + topicId, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray posts = null;
                try {
                    posts = response.getJSONArray("posts");
                    try {
                        for (int i = 0; i < posts.length(); i++) {
                            id.add(1);
                            JSONObject post = (JSONObject) posts.get(i);
                            String contentString = (i + 1) + ". ";
                            contentString = contentString + (String) post.get("content");
                            content.add(contentString);
                            date.add(((String) post.get("date")).substring(0, 10));
                            rating.add((Integer) post.get("upVote"));
                            username.add("BerkDilek");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                postsListView = (ListView) findViewById(R.id.list);

                getDataForList();
                postsListView.setAdapter(new PostsListAdapter(context, postsList));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline


                // Do something with the response
                //System.out.println(timeline);

//                try {
//                    JSONArray arr = timeline.getJSONArray(0);
//                    JSONObject obj = arr.getJSONObject(0);
//                    JSONArray narr = obj.getJSONArray("posts");
//                    System.out.println(narr.get(2));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Log.i("onFinish", "OKOKOK");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                throwable.printStackTrace(System.out);
            }


        });




    }

    public void upVoteButtonClickedListener (View view) {
        System.out.println("upVoteOnClick");
    }


    private void getDataForList() {
        for (int i = 0; i<id.size(); i++) {
            PostListData postListData = new PostListData();
            postListData.setId(id.get(i));
            postListData.setContent(content.get(i));
            postListData.setDate(date.get(i));
            postListData.setRating(rating.get(i));
            postListData.setUsername(username.get(i));

            postsList.add(postListData);
        }
    }


}

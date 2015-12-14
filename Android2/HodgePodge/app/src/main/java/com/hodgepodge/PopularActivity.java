package com.hodgepodge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PopularActivity extends AppCompatActivity {
    ListView topicsListView;
    Context context = PopularActivity.this;
    ArrayList<PostListData> topicsList = new ArrayList<PostListData>();

    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<Integer> rating = new ArrayList<>();
    ArrayList<String> username = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        setTitle("Popular Topics");

        ServiceClient.get("recent_popular/popular", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                System.out.println(timeline);
                try {
                    for (int i = 0; i < timeline.length(); i++) {

                        JSONObject topic = (JSONObject) timeline.get(i);
                        id.add(i);
                        title.add((String) topic.get("title"));
                        System.out.println(title);
                        date.add(((String) topic.get("createDate")).substring(0, 10));
                        rating.add((Integer) topic.get("upVote") - (Integer) topic.get("downVote"));
                        username.add("BerkDilek");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                topicsListView = (ListView) findViewById(R.id.popularTopicListView);

                getDataForList();
                topicsListView.setAdapter(new PostsListAdapter(context, topicsList));
                topicsListView.setClickable(true);

                topicsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PostListData item = (PostListData)topicsListView.getItemAtPosition(position);
                        Intent intent = new Intent(context, PostsListActivity.class);

                        intent.putExtra("id" , item.getId());
                        startActivity(intent);


                    }
                });
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

    private void getDataForList() {
        for (int i = 0; i<id.size(); i++) {
            PostListData postListData = new PostListData();
            postListData.setId(id.get(i));
            postListData.setContent(title.get(i));
            postListData.setDate(date.get(i));
            postListData.setRating(rating.get(i));
            postListData.setUsername(username.get(i));

            topicsList.add(postListData);
        }
    }
}

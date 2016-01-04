package com.hodgepodge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class PostAddActivity extends AppCompatActivity {
    public int topicId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_add);
    }

    public void addPost(View view) throws JSONException {
        final EditText topicEditText = (EditText) findViewById(R.id.topicEditText);
        final EditText contentEditText = (EditText) findViewById(R.id.contentEditText);
        final EditText tagsEditText = (EditText) findViewById(R.id.tagsEditView);

        ServiceClient.get("topic/title/" + topicEditText.getText().toString(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {
                    topicId = (int) response.get("id");
                    System.out.println(topicId);
                    SharedPreferences user = getSharedPreferences("UserInfo", 0);

                    JSONObject jsonParams = new JSONObject();
                    jsonParams.put("ownerId", user.getString("ID", ""));
                    jsonParams.put("content", contentEditText.getText().toString());
                    jsonParams.put("tagsOfPost", tagsEditText.getText());

                    StringEntity entity = null;
                    try {
                        entity = new StringEntity(jsonParams.toString());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    ServiceClient.post(getApplicationContext() , "post/create?topicId=" + topicId, entity, "application/json", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            System.out.println("Create Post Sucess");
                            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            System.out.println(error.getMessage());
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                throwable.printStackTrace(System.out);
            }
        });



    }
}





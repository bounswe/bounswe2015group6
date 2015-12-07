package com.hodgepodge;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;


import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class CreateRelationActivity extends AppCompatActivity {
    String URL = "edge/create?source=";
    int source;
    int dest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_relation);
    }

    public void createRelation(View view) throws JSONException {
        final EditText topic1Text = (EditText) findViewById(R.id.editTxtTopic1);
        final EditText topic2Text = (EditText) findViewById(R.id.editTxtTopic2);
        final Context context = this;
        ServiceClient.get("topic/title/" + topic1Text.getText(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    source = response.getInt("id");
                   // URL = URL + Integer.toString(response.getInt("id")) + "&dest=";

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
        ServiceClient.get("topic/title/" + topic2Text.getText(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    /*URL = URL + Integer.toString(response.getInt("id"));
                    SharedPreferences settings = getSharedPreferences("URLInfo", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("URL", URL);
                    editor.commit();
                    System.out.println("In get " + URL);*/
                    dest = response.getInt("id");
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
        /*SharedPreferences url = getSharedPreferences("URLInfo", 0);
        System.out.println("out get " + URL);*/
        System.out.println(source + " " + dest);
        System.out.println();
        StringEntity entity = null;
        ServiceClient.post(this,URL + source + "&dest=" + dest , entity, "application/json",
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        System.out.println("Create Relation Sucess");
                        Intent intent = new Intent(context, HomePageActivity.class);
                        startActivity(intent);
                        //Send user to homepage
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.println(error.getMessage());
                        //if (error.getMessage().equals("Usernameexist")) {
                        //  errorTextView.setText("Passwords do not match. Please try again.");
                        //  usernameField.setText("");
                        //}
                    }
                });
    }
}
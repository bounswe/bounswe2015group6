package com.hodgepodge;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class CreateRelationActivity extends AppCompatActivity {
    String URL = "edge/create_new";
    private int source;
    private int dest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_relation);
    }

    public void createRelation(View view) throws JSONException {
        final EditText topic1Text = (EditText) findViewById(R.id.editTxtTopic1);
        final EditText topic2Text = (EditText) findViewById(R.id.editTxtTopic2);
        final EditText labelText = (EditText) findViewById(R.id.editTxtLabel);
        final TextView errorText =  (TextView) findViewById(R.id.errorTextViewRelation);
        final Context context = this;

        ServiceClient.get("topic/title/" + topic1Text.getText(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getInt("id") != 0){
                        source = response.getInt("id");
                        // URL = URL + Integer.toString(response.getInt("id")) + "&dest=";
                        ServiceClient.get("topic/title/" + topic2Text.getText(), null, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    if(response.getInt("id") != 0){
                                        dest = response.getInt("id");
                                        JSONObject jsonParams = new JSONObject();
                                        jsonParams.put("from", source);
                                        jsonParams.put("to", dest);
                                        jsonParams.put("label", labelText.getText().toString());
                                        jsonParams.put("title", labelText.getText().toString());
                                        StringEntity entity = null;
                                        try {
                                            entity = new StringEntity(jsonParams.toString());
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        ServiceClient.post(getApplicationContext(),  URL , entity, "application/json",
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
                                    }else{
                                        errorText.setVisibility(View.VISIBLE);
                                        errorText.setText("Destination Topic don't exist");
                                    }


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
                    }else{
                        errorText.setVisibility(View.VISIBLE);
                        errorText.setText("Source Topic don't exist");

                    }


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
package com.hodgepodge;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CreateRelationActivity extends AppCompatActivity {
    String URL = "edge/create?source=";
    private int source;
    private int dest;
    private SharedPreferences user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_relation);
    }

    public void createRelation(View view) throws JSONException {
        final EditText topic1Text = (EditText) findViewById(R.id.editTxtTopic1);
        final EditText topic2Text = (EditText) findViewById(R.id.editTxtTopic2);
        final EditText label = (EditText) findViewById(R.id.editTxtLabel);
        final TextView errorText =  (TextView) findViewById(R.id.errorTextViewRelation);
        final Context context = this;
        user = getSharedPreferences("UserInfo", 0);


        ServiceClient.get("topic/title/" + topic1Text.getText(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getInt("id") != 0){
                        source = response.getInt("id");
                        ServiceClient.get("topic/title/" + topic2Text.getText(), null, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    if(response.getInt("id") != 0){
                                        dest = response.getInt("id");

                                        ServiceClient.post(getApplicationContext(), URL + source + "&dest=" + dest+"&label="+label.getText()+"&user="+user.getString("ID", ""), null, "application/json",
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
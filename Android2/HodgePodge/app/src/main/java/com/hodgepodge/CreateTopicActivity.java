package com.hodgepodge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class CreateTopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_topic);
    }

    public void createTopic(View view) throws JSONException {
        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
        final EditText tagsEditText = (EditText) findViewById(R.id.tagsEditText);
        final TextView errorCreateTopicTextView = (TextView) findViewById(R.id.errorCreateTopicTextView);
        final Context context = this;
        SharedPreferences user = getSharedPreferences("UserInfo", 0);

        String convertedTitle = titleEditText.getText().toString().replaceAll("\\s+", " ");
        titleEditText.setText(convertedTitle);
        if ( titleEditText.getText().toString().equals(" ") || titleEditText.getText().toString().isEmpty() ) {
            tagsEditText.setText("");
            errorCreateTopicTextView.setVisibility(View.VISIBLE);
            errorCreateTopicTextView.setText("Please, Enter a Title");

        } else {
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("ownerId", user.getString("ID", ""));
            jsonParams.put("title", titleEditText.getText().toString());
            // Taking tags which are  seperated by space
            String delims = "[ ]+";
            String[] tags = tagsEditText.getText().toString().split(delims);
            JSONArray tagsJson = new JSONArray(Arrays.asList(tags));
            jsonParams.put("tags", tagsJson);

            StringEntity entity = null;
            try {
                entity = new StringEntity(jsonParams.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            ServiceClient.post(this, "topic/create", entity, "application/json",
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            System.out.println("Create Topic Sucess");
                            errorCreateTopicTextView.setText("The topic was created");
                            Intent intent = new Intent(context, HomePageActivity.class);
                            startActivity(intent);
                            //Send user to homepage
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            System.out.println(error.getMessage());
                            Log.d("TOPIC CREATE ERROR", error.getMessage().toString());

                            //if (error.getMessage().equals("Usernameexist")) {
                            //  errorTextView.setText("Passwords do not match. Please try again.");
                            //  usernameField.setText("");
                            //}
                        }
                    });

        }
    }
}


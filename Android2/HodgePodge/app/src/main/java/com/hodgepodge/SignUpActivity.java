package com.hodgepodge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    public void attemptSignUp (View view) throws JSONException {
        final EditText usernameField = (EditText) findViewById(R.id.username_field);
        final EditText emailField = (EditText) findViewById(R.id.email_field);
        final EditText passwordField = (EditText) findViewById(R.id.password_field);
        final EditText passwordRepeatField = (EditText) findViewById(R.id.password_repeat_field);

        AsyncHttpClient client = new AsyncHttpClient();
//        client.addHeader("Content-Type", "application/json");
//        client.addHeader("Accept", "application/json");
//        client.setURLEncodingEnabled(false);

//        RequestParams requestParams = new RequestParams();
//        requestParams.put("username", usernameField.getText().toString());
//        requestParams.put("email", emailField.getText().toString());
//        requestParams.put("password", passwordField.getText().toString());
//        requestParams.put("facebookId", "");
//        requestParams.put("twitterId", "");
//        requestParams.put("googleId", "");
//        requestParams.setForceMultipartEntityContentType(true);



        JSONObject jsonParams = new JSONObject();
        jsonParams.put("username", "berkdilekberk");
        jsonParams.put("password", "berk124123");
        jsonParams.put("email", "berk@berk.com");
        jsonParams.put("facebookId", "berk");
        jsonParams.put("twitterId", "12s");
        jsonParams.put("googleId", "bebee");
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        client.post(this, "http://ec2-52-27-36-39.us-west-2.compute.amazonaws.com:8080/api/user/signup", entity, "application/json",
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        int c = 1;
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        int c = 1;
                    }
                });

        //        client.post("user/signup", requestParams, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                System.out.println("RegisterSuccess");
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                System.out.println(error.getMessage());
//
//            }
//        });

    }
}

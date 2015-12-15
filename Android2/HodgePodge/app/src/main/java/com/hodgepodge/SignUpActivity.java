package com.hodgepodge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        if (!passwordField.getText().toString().equals(passwordRepeatField.getText().toString())){
            TextView errorTextView = (TextView) findViewById(R.id.errorSingUpTextView);
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText("Passwords do not match. Please try again.");
            passwordField.setText("");
            passwordRepeatField.setText("");
        } else {
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("username", usernameField.getText().toString());
            jsonParams.put("password", passwordField.getText().toString());
            jsonParams.put("email", emailField.getText().toString());
            jsonParams.put("facebookId", "");
            jsonParams.put("twitterId", "");
            jsonParams.put("googleId", "");
            StringEntity entity = null;
            try {
                entity = new StringEntity(jsonParams.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            ServiceClient.post(this, "user/signup", entity, "application/json",
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            System.out.println("Register Sucess");
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
}

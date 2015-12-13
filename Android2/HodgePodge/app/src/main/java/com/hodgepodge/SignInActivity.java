package com.hodgepodge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SignInActivity extends AppCompatActivity {
    private static final String APP_SHARED_PREFS = "asdasd_preferences";
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    private boolean isUserLoggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void attemptSignIn(View view) throws JSONException{
        final EditText emailField = (EditText) findViewById(R.id.editText);
        final EditText passwordField = (EditText) findViewById(R.id.editText2);
        final TextView errorTextView = (TextView) findViewById(R.id.errorTextView);
        final Context context = this;

        ServiceClient.get("user/login?username="+emailField.getText()+"&password="+passwordField.getText(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {
                    JSONObject resultArray = (JSONObject) response.get("result");
                    if (resultArray.get("result").equals("OK")) {
                        errorTextView.setVisibility(View.VISIBLE);
                        errorTextView.setText("Login Success");
                        /*
                            author: Emre Oral
                            In that part we put username and id of logged in user to settings
                            it is a SharedPreferences.
                        */
                        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("Username",response.getString("username"));
                        editor.putString("ID", Integer.toString(response.getInt("id")));
                        editor.commit();

                        Intent intent = new Intent(context,HomePageActivity.class);
                        startActivity(intent);
                        // end of Emre Oral's code.
                        //LOGIN SUCCESS => direct to homepage
                    } else {
                        emailField.setText("");
                        passwordField.setText("");
                        errorTextView.setVisibility(View.VISIBLE);
                        errorTextView.setText("Wrong password or username");
                        //LOGIN FAILURE => try again
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline


                // Do something with the response
                System.out.println(timeline);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                throwable.printStackTrace(System.out);
            }
        });
    }
}

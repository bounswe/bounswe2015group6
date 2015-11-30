package com.hodgepodge;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {
    /*
        author: Emre Oral, this is a simple HomePageActivity to show usage of SharedPreferences
        in that activity I show the username and id of logged in user in a TextView.
        NOTE: to understand what "USERNAME" and "ID" are please check my code in success part of
        SignInActivity.java.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TextView txtWel = (TextView) findViewById(R.id.txtWelcome);
        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
        txtWel.setText("WELCOME UserName: " + settings.getString("Username", "").toString() +
        " ID: " + settings.getString("ID", "").toString());

    }
}

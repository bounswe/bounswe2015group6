package com.hodgepodge.hodgepodge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by apple on 02/11/15.
 */
public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setElevation(0);
    }
}

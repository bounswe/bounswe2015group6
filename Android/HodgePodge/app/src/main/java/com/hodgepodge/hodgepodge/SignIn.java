package com.hodgepodge.hodgepodge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by apple on 02/11/15.
 */
public class SignIn extends AppCompatActivity {
    TextView resultText;
    EditText email;
    EditText password;
    String EmailString, PasswordString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setElevation(0);

        email = (EditText)findViewById(R.id.emailText);
        password = (EditText)findViewById(R.id.passwordText);
    }


}




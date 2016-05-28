package com.ateam.mafiamania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Android login screen Activity
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername;
    private Button buttonLogin;

    private static final String LOGIN_URL = "http://mafiamania.coolpage.biz/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.username);
        buttonLogin = (Button) findViewById(R.id.login_button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        TextView signupLink = (TextView) findViewById(R.id.sign_up_link);
        assert signupLink != null;
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void onClick(View v) {
        /* This logic has been take care above using
        above Lambda Expressions and Abstract classes
            if(v == buttonLogin){
                loginUser();
            }
        */
    }

    private void loginUser() {
        StringBuffer response = new StringBuffer();
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        SendGetRequest sendGetRequest = new SendGetRequest(this, LOGIN_URL, username);
        sendGetRequest.createGetRequestAndSend();
    }
 }
package com.ateam.mafiamania;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Android login screen Activity
 */
    public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername;
    private Button buttonSignup;


    private static final String REGISTER_URL = "http://mafiamania.coolpage.biz/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextUsername = (EditText) findViewById(R.id.username);
        buttonSignup = (Button) findViewById(R.id.signup_button);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    @Override
    public void onClick(View v) {
        /* This logic has been take care above using
        above Lambda Expressions and Abstract classes
            if(v == buttonSignup){
                registerUser();
            }
        */
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        SendGetRequest sendGetRequest = new SendGetRequest(this ,REGISTER_URL, username);
        sendGetRequest.createGetRequestAndSend();
    }
}
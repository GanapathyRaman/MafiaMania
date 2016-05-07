package com.ateam.mafiamania;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class Temp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_temp);

        TextView tempText = (TextView) findViewById(R.id.tempT);
        SendGetRequest sendReq = new SendGetRequest();
        tempText.append("Hi ");
        tempText.append(sendReq.getUsername().toUpperCase() + " :)\n\n");
        tempText.append("Thanks for registering with us. Currently the app is in Beta version. " +
                "Support for the Online mode is under development and will be released soon.");
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
}
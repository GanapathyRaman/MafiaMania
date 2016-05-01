package com.ateam.mafiamania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlayModeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonOffline;
    private Button buttonOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_game_mode);

        buttonOffline = (Button) findViewById(R.id.buttonOffline);

        buttonOnline = (Button) findViewById(R.id.buttonOnline);
        buttonOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayModeActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
    }

    @Override
    public void onClick(View v) {
        /* This logic has been take care above using
        above Lambda Expressions and Abstract classes
        */
    }
}

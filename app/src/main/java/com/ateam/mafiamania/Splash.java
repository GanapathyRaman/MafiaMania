package com.ateam.mafiamania;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

    private int splash_screen_show_milliseconds = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openLoginActivity = new Intent(Splash.this, LoginActivity.class);
                startActivity(openLoginActivity);
                finish();

            }
        }, splash_screen_show_milliseconds);
    }
}
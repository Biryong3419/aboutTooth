package com.example.kimwoochul.abouttooth;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new SplashHandler(), 3000);
    }
    private class SplashHandler implements Runnable
    {
        @Override
        public void run()
        {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }
}

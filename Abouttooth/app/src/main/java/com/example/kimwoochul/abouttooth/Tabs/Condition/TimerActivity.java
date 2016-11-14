package com.example.kimwoochul.abouttooth.Tabs.Condition;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kimwoochul.abouttooth.R;

/**
 * Created by Jh on 2016-11-14.
 */

public class TimerActivity extends AppCompatActivity {

    Button buttonStart;
    ProgressBar progressBar;
    TextView textCounter;

    MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        buttonStart = (Button)findViewById(R.id.start);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        textCounter = (TextView)findViewById(R.id.counter);

        buttonStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                progressBar.setProgress(100);
                myCountDownTimer =  new MyCountDownTimer(10000, 500);
                myCountDownTimer.start();
            }});

    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            textCounter.setText(String.valueOf(millisUntilFinished));
            int progress = (int) (millisUntilFinished/100);
            progressBar.setProgress(progress);
        }

        @Override
        public void onFinish() {
            textCounter.setText("Finished");
            progressBar.setProgress(0);
        }

    }

}
package com.example.kimwoochul.abouttooth.Tabs.Condition;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kimwoochul.abouttooth.R;

/**
 * Created by Jh on 2016-11-14.
 */


public class TimerActivity extends Activity implements View.OnClickListener {

    int i = -1;
    ProgressBar mProgressBar, mProgressBar1;
Button btn_cancel;
    private Button buttonStartTime, buttonStopTime;
    private EditText edtTimerValue;
    private TextView textViewShowTime;
    private CountDownTimer countDownTimer;
    private long totalTimeCountInMilliseconds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);


        buttonStartTime = (Button) findViewById(R.id.button_timerview_start);
        buttonStopTime = (Button) findViewById(R.id.button_timerview_stop);
btn_cancel=(Button) findViewById(R.id.btn_timer_cancel);
        textViewShowTime = (TextView)
                findViewById(R.id.textView_timerview_time);
        edtTimerValue = (EditText) findViewById(R.id.textview_timerview_back);

        buttonStartTime.setOnClickListener(this);
        buttonStopTime.setOnClickListener(this);

      //  mProgressBar = (ProgressBar) findViewById(R.id.progressbar_timerview);
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1_timerview);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_timerview_start) {

            setTimer();

            buttonStartTime.setVisibility(View.INVISIBLE);
            buttonStopTime.setVisibility(View.VISIBLE);
            //mProgressBar.setVisibility(View.INVISIBLE);

            startTimer();
            //mProgressBar1.setVisibility(View.VISIBLE);


        } else if (v.getId() == R.id.button_timerview_stop) {
            countDownTimer.cancel();
            countDownTimer.onFinish();
            //mProgressBar1.setVisibility(View.GONE);
            //mProgressBar.setVisibility(View.VISIBLE);
            edtTimerValue.setVisibility(View.VISIBLE);
            buttonStartTime.setVisibility(View.VISIBLE);
            buttonStopTime.setVisibility(View.INVISIBLE);
        }
    }
    private void setTimer(){
        int time = 0;
        if (!edtTimerValue.getText().toString().equals("")) {
            time = Integer.parseInt(edtTimerValue.getText().toString());
        } else
            Toast.makeText(TimerActivity.this, "Please Enter Minutes...",
                    Toast.LENGTH_LONG).show();
        totalTimeCountInMilliseconds =  time * 1000;
        mProgressBar1.setMax( time * 1000);
    }
    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 1) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                mProgressBar1.setProgress((int) (leftTimeInMilliseconds));

                textViewShowTime.setText(String.format("%02d:%02d:%02d", seconds / 3600,
                        (seconds % 3600) / 60, (seconds % 60)));
            }
            @Override
            public void onFinish() {
                textViewShowTime.setText("00:00");
                textViewShowTime.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.VISIBLE);
                //mProgressBar.setVisibility(View.VISIBLE);
              //  mProgressBar1.setVisibility(View.GONE);

            }
        }.start();
    }
}
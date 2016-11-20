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

public class TimerActivity extends AppCompatActivity {

    Button buttonStart;
    ProgressBar progressBar;
    EditText textCounter;
    private static boolean timerFlag;
    MyCountDownTimer myCountDownTimer;
int minus=0;

    String hh;
//edittext 값을 변경한다.
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timerFlag=true;
        Toast.makeText(TimerActivity.this,"여기까지된다1",Toast.LENGTH_LONG).show();
        //   String text=pref.getString("token","");
hh = loadvalue();
        buttonStart = (Button)findViewById(R.id.start);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        textCounter = (EditText) findViewById(R.id.counter);
        textCounter.setText(hh);



        buttonStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(timerFlag == true) {
                    progressBar.setProgress(100);
                    minus=0;
                    myCountDownTimer =  new MyCountDownTimer(Integer.valueOf(textCounter.getText().toString())*1000,500);
                    buttonStart.setText("stop");
                    myCountDownTimer.start();
                   timerFlag=false;
                }
                else{
                    myCountDownTimer.cancel();
                    buttonStart.setText("start");
timerFlag=true;
                    progressBar.setProgress(0);
                }
            }});

    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            textCounter.setText(String.valueOf(millisUntilFinished/1000));
            //int progress = (int) (100);
            int progress = (int) ((millisUntilFinished/1000)+ (100-millisUntilFinished/1000)-minus);
            minus++;
            progressBar.setProgress(progress);
        }

        @Override
        public void onFinish() {
            timerFlag=true;
           saveValue();
            buttonStart.setText("start");
            textCounter.setText("Finished");
            progressBar.setProgress(0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            textCounter.setText(loadvalue());

        }

    }
 private void saveValue(){
     SharedPreferences pref=getSharedPreferences("timervalue",Activity.MODE_PRIVATE);
     SharedPreferences.Editor editor = pref.edit();
     editor.putString("value",textCounter.getText().toString());
     editor.commit();
 }
    private String loadvalue(){
        SharedPreferences pref=getSharedPreferences("timervalue",Activity.MODE_PRIVATE);
        String value=pref.getString("value","15");
return value;
    }


}
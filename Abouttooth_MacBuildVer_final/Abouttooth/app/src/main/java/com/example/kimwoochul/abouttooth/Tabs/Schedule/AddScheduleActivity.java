package com.example.kimwoochul.abouttooth.Tabs.Schedule;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kimwoochul.abouttooth.R;
import com.example.kimwoochul.abouttooth.Tools.MyApplication;

import org.w3c.dom.Text;

import java.util.Calendar;

public class AddScheduleActivity extends AppCompatActivity {


    Button btn_cancle;
    Button btn_ok;
    Intent intent;
    Calendar calendar;
    TextView tv_day;
    TextView tv_time;
    TimePickerDialog dialog;
    EditText et_memo;

    int year;
    int month;
    int dayOfMonth;
    String day;

    int hour;
    int minute;
    String time;

    String memo;

    ScheduleFragment scheduleFragment;
    ScheduleManager scheduleManager;

    final static int TIMEPICKER_CLOCK_MODE = 0;
    final static int TIMEPICKER_SPINNER_MODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        tv_day = (TextView)findViewById(R.id.tv_day);
        tv_time = (TextView)findViewById(R.id.tv_time);

        calendar = Calendar.getInstance();

        intent = getIntent();
        try
        {
            day = intent.getExtras().getString("day");
        }
        catch (NullPointerException e)
        {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            day = year + "." + month + "." + dayOfMonth;
        }

        tv_day.setText(day);

        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        time = hour + ":" + minute;

        tv_time.setText(time);

        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new TimePickerDialog(AddScheduleActivity.this,TIMEPICKER_SPINNER_MODE , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int _minute) {
                        hour = hourOfDay;
                        minute = _minute;
                        time = hour + ":" + minute;
                        tv_time.setText(time);
                    }
                }, hour, minute, true);
                dialog.show();
            }
        });

        et_memo = (EditText)findViewById(R.id.et_memo);

        btn_cancle = (Button)findViewById(R.id.btn_cancle);
        btn_ok = (Button)findViewById(R.id.btn_ok);

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memo = et_memo.getText().toString();

                scheduleManager = ScheduleManager.getInstance(MyApplication.getsContext());
                scheduleManager.InitScheduleTable();

                scheduleFragment = ScheduleFragment.getInstance();
                scheduleManager.addScheduleTable(new Data_Schedule(day, memo, time));

                scheduleFragment.setSchedule(scheduleManager.getScheduleData());
                scheduleFragment.setScheduleAdapter(MyApplication.getsContext(), scheduleFragment.getSchedule());
                scheduleFragment.getLv_schedule().setAdapter(scheduleFragment.getScheduleAdapter());

                scheduleFragment.getScheduleAdapter().notifyDataSetChanged();

                finish();
            }
        });
    }
}

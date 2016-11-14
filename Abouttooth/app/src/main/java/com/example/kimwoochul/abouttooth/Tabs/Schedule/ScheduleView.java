package com.example.kimwoochul.abouttooth.Tabs.Schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.kimwoochul.abouttooth.R;
import com.example.kimwoochul.abouttooth.Tools.MyApplication;

/**
 * Created by woocheol on 2016. 11. 6..
 */

public class ScheduleView extends FrameLayout {

    public ScheduleView(Context context){
        super(context);
        context = MyApplication.getsContext();
        init();
    }

    TextView lv_day;
    TextView lv_memo;
    TextView lv_time;

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.list_schedule, this);
        lv_day = (TextView)findViewById(R.id.lV_day);
        lv_memo = (TextView)findViewById(R.id.lv_memo);
        lv_time = (TextView)findViewById(R.id.lv_time);
    }

    public void setData(Data_Schedule data){
        lv_day.setText(data.getDay());
        lv_memo.setText(data.getMemo());
        lv_time.setText(data.getTime());
    }
}

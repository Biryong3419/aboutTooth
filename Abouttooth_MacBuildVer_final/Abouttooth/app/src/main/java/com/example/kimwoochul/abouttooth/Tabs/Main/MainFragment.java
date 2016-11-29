package com.example.kimwoochul.abouttooth.Tabs.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.kimwoochul.abouttooth.R;
import com.example.kimwoochul.abouttooth.Tabs.Condition.MyflaActivity;
import com.example.kimwoochul.abouttooth.Tabs.Condition.TimerActivity;
import com.example.kimwoochul.abouttooth.Tabs.Schedule.Data_Schedule;
import com.example.kimwoochul.abouttooth.Tabs.Schedule.ScheduleAdapter;
import com.example.kimwoochul.abouttooth.Tabs.Schedule.ScheduleFragment;
import com.example.kimwoochul.abouttooth.Tabs.Schedule.ScheduleManager;
import com.example.kimwoochul.abouttooth.Tools.MyApplication;

import java.util.ArrayList;

public class MainFragment extends Fragment implements View.OnTouchListener {
    ViewFlipper flipper;
    float xAtUp;
    float xAtDown;
    ImageView main_c;
    ImageView main_teeth_condition;
    ArrayList<Data_Schedule> schedule;
    private static ListView lv_main;
    private static ScheduleAdapter scheduleAdapter;
    private static ScheduleFragment instance;
    private static ScheduleManager scheduleManager;



    public static ScheduleFragment getInstance(){
        if(instance == null){
            instance = new ScheduleFragment();
        }
        return instance;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_main,null);
        lv_main=(ListView)v.findViewById(R.id.lv_main);
        main_c=(ImageView)v.findViewById(R.id.main_c);
        main_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), TimerActivity.class);
                startActivity(i);
            }
        });
        main_teeth_condition=(ImageView)v.findViewById(R.id.main_teeth_condition);
        main_teeth_condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyflaActivity.class);
                startActivity(i);
            }
        });
  scheduleListViewInit();
        flipper = (ViewFlipper) v.findViewById(R.id.viewFlipper);
        flipper.setOnTouchListener(this);
        return v;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // 터치 이벤트가 일어난 뷰가 ViewFlipper가 아니면 return
        if (view != flipper)
            return false;


        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            xAtDown = motionEvent.getX(); // 터치 시작지점 x좌표 저장
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            xAtUp = motionEvent.getX(); // 터치 끝난지점 x좌표 저장

            if (xAtUp < xAtDown) {
                // 왼쪽 방향 에니메이션 지정
                flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
                        R.anim.push_left_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                        R.anim.push_left_out));

                // 다음 view 보여줌
                flipper.showNext();
            } else if (xAtUp > xAtDown) {
                // 오른쪽 방향 에니메이션 지정
                flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
                        R.anim.push_right_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(MainFragment.this.getActivity(),
                        R.anim.push_right_out));
                // 전 view 보여줌
                flipper.showPrevious();
            }
        }
        return true;
    }
    public ArrayList<Data_Schedule> getSchedule(){
        return this.schedule;
    }
    public  void setSchedule(ArrayList<Data_Schedule> data){
        schedule = new ArrayList<Data_Schedule>();
        this.schedule.addAll(data);
    }
    public void deleteItem(ArrayList<Data_Schedule> data){
        schedule.clear();
        schedule.addAll(data);
    }
    public void setScheduleAdapter(Context context, ArrayList<Data_Schedule> data){
        scheduleAdapter = new ScheduleAdapter(context, data);
    }
    public ScheduleAdapter getScheduleAdapter() {
        return scheduleAdapter;
    }
    public ListView getLv_schedule(){
        return this.lv_main;
    }
    public void scheduleListViewInit(){
        scheduleManager = ScheduleManager.getInstance(MyApplication.getsContext());
        scheduleManager.InitScheduleTable();

        //TODO
        setSchedule(scheduleManager.getScheduleData());
        setScheduleAdapter(MyApplication.getsContext(), getSchedule());
        getLv_schedule().setAdapter(getScheduleAdapter());

        scheduleAdapter.notifyDataSetChanged();
    }
}

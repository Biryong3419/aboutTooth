package com.example.kimwoochul.abouttooth.Tabs.Schedule;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;

import com.example.kimwoochul.abouttooth.R;
import com.example.kimwoochul.abouttooth.Tools.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment
{
    Intent intent;
    long date;

    ArrayList<Data_Schedule> schedule;
    private static ListView lv_schedule;
    private static  ScheduleAdapter scheduleAdapter;

    private static ScheduleFragment instance;
    private static ScheduleManager scheduleManager;

    public ScheduleFragment(){
        super();
    }

    public static ScheduleFragment getInstance(){
        if(instance == null){
            instance = new ScheduleFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        instance = getInstance();

        lv_schedule = (ListView)v.findViewById(R.id.lv_schedule);

        scheduleManager = ScheduleManager.getInstance(MyApplication.getsContext());
        scheduleManager.InitScheduleTable();

        //TODO
        setSchedule(scheduleManager.getScheduleData());
        setScheduleAdapter(MyApplication.getsContext(), getSchedule());
        getLv_schedule().setAdapter(getScheduleAdapter());

        scheduleAdapter.notifyDataSetChanged();

        final CalendarView calendar = (CalendarView)v.findViewById(R.id.calendar);
        intent = new Intent(getActivity(), AddScheduleActivity.class);

        date = calendar.getDate();
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                intent.putExtra("day", year + "." + (month + 1) + "." + dayOfMonth);

                Toast.makeText(getActivity(), year + "." + (month + 1) + "." + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(intent);
            }
        });

        return v;
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
        return this.lv_schedule;
    }
}

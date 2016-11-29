package com.example.kimwoochul.abouttooth.Tabs.Schedule;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by woocheol on 2016. 11. 6..
 */

public class ScheduleAdapter extends BaseAdapter {
    ArrayList<Data_Schedule> schedule;
    private Context context;

    public ScheduleAdapter(Context context, ArrayList<Data_Schedule> list) {
        super();
        this.context = context;
        schedule = new ArrayList<Data_Schedule>();
        schedule.addAll(list);
    }

    @Override
    public int getCount() {
        return schedule.size();
    }

    @Override
    public Object getItem(int position) {
        return schedule.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScheduleView itemView;

        if(convertView == null){
            itemView = new ScheduleView(context);
        }
        else{
            itemView = (ScheduleView)convertView;
        }
        itemView.setData(schedule.get(position));

        return itemView;
    }
}

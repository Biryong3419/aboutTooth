package com.example.kimwoochul.abouttooth.Tabs.Schedule;

import android.content.Context;

import com.example.kimwoochul.abouttooth.Tools.DbOpenHelper;
import com.example.kimwoochul.abouttooth.Tools.MyApplication;

import java.util.ArrayList;

/**
 * Created by woocheol on 2016. 11. 6..
 */

public class ScheduleManager {
    private DbOpenHelper mDbOpenHelper;
    private Context mContext;

    private ArrayList<Data_Schedule> scheduleData;

    int lastIdx = 0;
    private static ScheduleManager instance;

    public static ScheduleManager getInstance(Context context){
        if(instance == null){
            instance = new ScheduleManager(context);
        }
        return instance;
    }
    public ScheduleManager(Context context){
        this.mContext = context;
        mDbOpenHelper = mDbOpenHelper.getInstance();
    }

    public void InitScheduleTable(){
        scheduleData = new ArrayList<Data_Schedule>();
    }
    public ArrayList<Data_Schedule> getScheduleData(){
        scheduleData.addAll(ScheduleManager.getInstance(MyApplication.getsContext()).loadScheduleData());

        return scheduleData;
    }
    public ArrayList<Data_Schedule> deleteItem(int position){
        DbOpenHelper.getInstance().deleteItem(position);
        scheduleData.clear();
        scheduleData.addAll(ScheduleManager.getInstance(MyApplication.getsContext()).loadScheduleData());

        return scheduleData;
    }
    public void addScheduleTable(Data_Schedule data){
        mDbOpenHelper.insertDb(data);
    }
    public ArrayList<Data_Schedule> loadScheduleData(){
        return mDbOpenHelper.loadDb();
    }
}

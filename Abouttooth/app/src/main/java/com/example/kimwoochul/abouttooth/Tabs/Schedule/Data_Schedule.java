package com.example.kimwoochul.abouttooth.Tabs.Schedule;

/**
 * Created by woocheol on 2016. 11. 6..
 */

public class Data_Schedule {
    private String day;
    private String memo;
    private String time;

    public Data_Schedule(String day, String memo, String time){
        this.day = day;
        this.memo = memo;
        this.time = time;
    }

    public String getDay() {
        return day;
    }
    public String getMemo() {
        return memo;
    }
    public String getTime() {
        return time;
    }
}

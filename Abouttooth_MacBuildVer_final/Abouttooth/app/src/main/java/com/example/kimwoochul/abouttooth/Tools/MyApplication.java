package com.example.kimwoochul.abouttooth.Tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by woocheol on 2016. 11. 5..
 */

public class MyApplication extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getsContext(){
        return sContext;
    }
}

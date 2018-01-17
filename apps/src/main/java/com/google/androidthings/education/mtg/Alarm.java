package com.google.androidthings.education.mtg;

import android.util.Log;

/**
 * Created by estanie on 2018-01-16.
 */

public class Alarm implements Runnable {
    private static final String TAG = "Alarm";
    private MyDevice myDevice;

    public Alarm(MyDevice myDevice){
        this.myDevice = myDevice;
    }

    public void run(){
        long threadId = Thread.currentThread().getId();

        Log.e(TAG,"Alarm is running");

        myDevice.alarm_bell(true);
        try {
            // @TODO: 현재 1분이지만 10분으로 다시 변경해야함.
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e){
            Log.e(TAG,e.toString());
        }
        myDevice.alarm_bell(false);

    }
}
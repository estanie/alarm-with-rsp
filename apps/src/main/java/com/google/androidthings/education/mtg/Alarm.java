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
            Thread.sleep(1000 * 600);
        } catch (InterruptedException e){
            Log.e(TAG,e.toString());
        }
        myDevice.alarm_bell(false);

    }
}
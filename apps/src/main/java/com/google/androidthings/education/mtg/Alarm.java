package com.google.androidthings.education.mtg;

import android.util.Log;

/**
 * Created by estanie on 2018-01-16.
 */

public class Alarm extends Thread{
    private static final String TAG = "Alarm";
    MyDevice myDevice;
    public Alarm(MyDevice myDevice){
        this.myDevice = myDevice;
    }
    public void run(){
        int second = 0;
        Log.e(TAG,"Alarm is running");
        while (second<=1000*60) {
            second++;
            try{
                myDevice.alarm_bell(true);
                Log.d(TAG,"***id: "+this.getId());
            }catch(Exception e){
                Log.e(TAG,"Error: "+e);
            }
        }
        myDevice.alarm_bell(false);
    }
}

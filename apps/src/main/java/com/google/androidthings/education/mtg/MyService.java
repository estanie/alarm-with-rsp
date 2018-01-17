package com.google.androidthings.education.mtg;

import android.app.Service;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TimePicker;

import com.google.androidthings.education.mtg.Display;
import com.google.androidthings.education.mtg.Led;
import com.google.androidthings.education.mtg.MusicPlayer;

import java.util.Timer;

/**
 * Created by estanie on 2018-01-16.
 */

public class MyService extends Service {
    private static final String TAG = "MY_SERVICE";
    private Thread rspJob;
    private Display display;
    private Led led;
    private MusicPlayer music;
    MyDevice myDevice;
    RspGame game = null;

    boolean serviceStatus = true;

    public IBinder mIBinder = new MyBinder();

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind()");
        return mIBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("LOG", "onUnbind()");
        return super.onUnbind(intent);
    }

    public void onCreate() {
        Log.e(TAG, "onCreate()");
        display = new Display();
        led = new Led();
        music = new MusicPlayer();
        myDevice = new MyDevice(display, music, led);
        myDevice.open();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        rspJob = new Thread() {
            @Override
            public void run() {
                myDevice.pause(1);
                try {
                    alarm_with_rsp();
                } catch (Exception e) {
                    Log.e(TAG, "Alarm-with-rsp Error: ");
                    e.printStackTrace();
                }
            }
        };
        rspJob.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void alarm_with_rsp() {

        Display display = myDevice.getDisplay();
        ScheduledJob job = new ScheduledJob(display);
        Timer jobScheduler = new Timer();
        jobScheduler.scheduleAtFixedRate(job, 1000, 5000);
        Alarm alarm = new Alarm(myDevice);
        Thread alarmThread = null;

        while (true) {
            if (alarmThread == null || alarmThread.getState() == Thread.State.TERMINATED) {
                myDevice.off();
                alarmThread = new Thread(alarm);
            }
            if (game != null && !game.isGamePlaying()) {//게임 횟수가 다 되었으면.
                alarmThread.interrupt();
                myDevice.off();
                Log.d(TAG, "Alarm is interrupted");
            }

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+09:00"));
            cal.getTime();

            int getHour = cal.get(Calendar.HOUR) + cal.get(Calendar.AM_PM) * 12;
            int getMinute = cal.get(Calendar.MINUTE);
            if (getHour == (MainActivity.setTime() / 100) && getMinute == MainActivity.setTime() % 100) {

                if (!alarmThread.isAlive() && !alarmThread.isInterrupted()) {
                    game = new RspGame();
                    alarmThread.start();
                    Intent intent = new Intent(this, StartActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);

                }
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service is Destroyed");
        myDevice.close();
    }
}

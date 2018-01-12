package com.google.androidthings.education.mtg;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import android.util.Log;
import static com.google.androidthings.education.mtg.Led.ALL;
import static com.google.androidthings.education.mtg.Led.BLUE;
import static com.google.androidthings.education.mtg.Led.CYAN;
import static com.google.androidthings.education.mtg.Led.GREEN;
import static com.google.androidthings.education.mtg.Led.ORANGE;
import static com.google.androidthings.education.mtg.Led.RED;
import static com.google.androidthings.education.mtg.Led.VIOLET;
import static com.google.androidthings.education.mtg.Led.WHITE;
import static com.google.androidthings.education.mtg.Led.YELLOW;
import static com.google.androidthings.education.mtg.MusicPlayer.Note.C;
import static com.google.androidthings.education.mtg.MusicPlayer.Note.D;
import static com.google.androidthings.education.mtg.MusicPlayer.Note.E;
import static com.google.androidthings.education.mtg.MusicPlayer.Note.F;
import static com.google.androidthings.education.mtg.MusicPlayer.Note.G;

/**
 * My Device!
 */


public class MyDevice {
    private static final String TAG = MyDevice.class.getSimpleName();

    private Led light;
    private Display display;
    private MusicPlayer music;

    public MyDevice(Display display, MusicPlayer music, Led light) {
        this.display = display;
        this.music = music;
        this.light = light;
    }

    public static void pause(double pauseTimeSec) {
        try {
            Thread.sleep((long)(pauseTimeSec * 1000.0));
        } catch (InterruptedException e) {
            Log.e(TAG, "Failed to sleep", e);
        }
    }

    /** 여기서부터 시작 */

    public void alarm_with_RSP() {
        ScheduledJob job = new ScheduledJob(display);
        Timer jobScheduler = new Timer();
        jobScheduler.scheduleAtFixedRate(job, 1000, 5000);
        try{
            Thread.sleep(20000);
        } catch(InterruptedException ex){
        }
        jobScheduler.cancel();
    }
}

class ScheduledJob extends TimerTask{
    private Display display;
    ScheduledJob(Display device_display) {
        display = device_display;
    }

    public void run()
    {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+09:00"));
        cal.getTime();
        String hour = Integer.toString(cal.get(Calendar.HOUR) + cal.get(Calendar.AM_PM)*12);
        String minute = Integer.toString(cal.get(Calendar.MINUTE));

        if(hour.length() < 2) {
            hour = '0' + hour;
        }
        if(minute.length() < 2){
            minute = '0' + minute;
        }
        display.show(hour+minute);
    }
}
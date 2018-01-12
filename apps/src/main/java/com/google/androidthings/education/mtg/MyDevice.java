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

    public void alarm_with_rsp() {

        ScheduledJob job = new ScheduledJob(display, music);
        Timer jobScheduler = new Timer();
        jobScheduler.scheduleAtFixedRate(job, 1000, 5000);

        while(true) {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+09:00"));
            cal.getTime();
            int getHour = cal.get(Calendar.HOUR) + cal.get(Calendar.AM_PM)*12;
            int getMinute = cal.get(Calendar.MINUTE);
            if(getHour == setTime() / 100 && getMinute == setTime() % 100) {
                // alarm();
            }
            try {
                Thread.sleep(20000);
            } catch(InterruptedException e){
            }

        }
    }

    int setTime() // 스크린 어케 구현하지 ㅠㅠ
    {
        int setHour = 0;
        int setMinute = 0;
        int setTime = setHour * 100 + setMinute;
        return setTime;
    }

    void alarm_bell(boolean start) { // 1넣으면 켜지고 0넣으면 꺼지고
        if(start) music.play(C);
        else music.stop();
    }
}

class ScheduledJob extends TimerTask{
    private Display display;
    private MusicPlayer music;

    ScheduledJob(Display display, MusicPlayer music) {
        this.display = display;
        this.music = music;
    }

    public void run()
    {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+09:00"));
        cal.getTime();
        int getHour = cal.get(Calendar.HOUR) + cal.get(Calendar.AM_PM)*12;
        int getMinute = cal.get(Calendar.MINUTE);

        String sHour = Integer.toString(getHour);
        String sMinute = Integer.toString(getMinute);

        if(sHour.length() < 2) {
            sHour = '0' + sHour;
        }
        if(sMinute.length() < 2){
            sMinute = '0' + sMinute;
        }
        display.show(sHour+sMinute);
    }
}
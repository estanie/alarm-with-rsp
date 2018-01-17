package com.google.androidthings.education.mtg;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;

import java.io.IOException;
import java.io.Serializable;
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
import java.util.Random;

/**
 * My Device!
 */


public class MyDevice implements Serializable {
    private static final String TAG = MyDevice.class.getSimpleName();
    private Led light;
    private Display display;
    private MusicPlayer music;
    private MusicPlayer.Note nabiya[] = { G, E, E, E, F, D, D, D, C, D, E, F, G, G, G, G,
            G, E, E, E, F, D, D, D, C, E, G, G, E, E, E, E};
    public Led getLight() {
        return light;
    }

    public MusicPlayer getMusic() {
        return music;
    }
    public Display getDisplay(){
        return display;
    }

    public MyDevice(Display display, MusicPlayer music, Led light) {
        this.display = display;
        this.music = music;
        this.light = light;
    }

    public void open(){
        light.open();
        music.open();
        display.open();
    }

    public void close(){
        light.close();
        music.close();
        display.close();
    }
    public void off(){
        light.off(ALL);
        music.stop();
    }

    public static void pause(double pauseTimeSec) {
        try {
            Thread.sleep((long)(pauseTimeSec * 1000.0));
        } catch (InterruptedException e) {
            Log.e(TAG, "Failed to sleep", e);
        }
    }
    /** 여기서부터 시작 */

    public void ledOn(int times){
        for (int i = 0;i<times;i++)
            light.on(i);
    }

    boolean alarm_bell(boolean start) { // 1넣으면 켜지고 0넣으면 꺼지고
        if(start) {
            music.play(E);
            //music.playAll(0.5, nabiya);
        }
        else music.stop();
        return true;
    }
}


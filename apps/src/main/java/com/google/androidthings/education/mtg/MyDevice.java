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

    int user_key(){ // user키 입력 및 컴퓨터에게 이기는 패로 바꿈
        return 1;
    }

    boolean check(int computer){ // user키와 컴퓨터키의 매칭을 확인
        int k = user_key();
        if(k == computer)return true;
        return false;
    }

    void rand_computer() { // 난수를 생성하고 종료조건을 확인한다.
        int win_number = 0;
        Random rand = new Random();
        //first
        while(win_number < 10){
            int com = rand.nextInt(3)+1;
            // alram(win_number); // 스크린에 컴퓨터가 이긴횟수를 표시한다.
            if(check(com))win_number += 1;
        }
        //second
        /*
        for(int i = 1 ; i <= 10 ; i++)
            int com = rand.nextInt(3)+1;
            // alram(win_number);
            if(check(com))check_number += 1;
        }
        */
        //third
        /*
        int count = 0; // 연속해서 2번 지는 지 확인
        while(win_number < 10){
            int com = rand.nextInt(3)+1;
            // alram(win_number); // 스크린에 컴퓨터가 이긴횟수를 표시한다.
            if(check(com)){
                win_number += 1;
                count = 0;
             }
             else if(count==2){
                win_number += 1;
                count = 0;
             }
             else count++;
        }
         */
    }
}


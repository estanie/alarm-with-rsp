package com.google.androidthings.education.mtg;

import java.util.Random;

/**
 * Created by estanie on 2018-01-18.
 */

public class RspGame {
    
    int win_number = 0;
    int play_number = 0;
    private static final int PLAY_GAME_TIMES = 10;
    private static final int WIN_TIMES = 5;

    public int getWin_number() {
        return win_number;
    }
    int user_key(){ // user키 입력 및 컴퓨터에게 이기는 패로 바꿈
        return 1;
    }

    boolean check(int computer){ // user키와 컴퓨터키의 매칭을 확인
        int k = user_key();
        if(k == computer)return true;
        return false;
    }

    boolean isGamePlaying() {
        return play_number < PLAY_GAME_TIMES && win_number < WIN_TIMES;
    }
    void rand_computer() { // 난수를 생성하고 종료조건을 확인한다.
        Random rand = new Random();
        //first
        while(isGamePlaying()) {
            int com = rand.nextInt(3)+1;
            // alarm(win_number); // 스크린에 컴퓨터가 이긴횟수를 표시한다.
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

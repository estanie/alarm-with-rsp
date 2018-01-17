/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.androidthings.education.mtg;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.util.Log;
import android.icu.util.Calendar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Timer;

import static com.google.androidthings.education.mtg.MusicPlayer.Note;
import static com.google.androidthings.education.mtg.Led.ALL;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    TextView tv;
    TimePicker tp;
    Button btn;

    static int setHour = -1;
    static int setMinute = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        tp = (TimePicker) findViewById(R.id.tp);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);

       Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
    }

    public static int setTime() {
        return setHour * 100 + setMinute;
    }

    @Override
    public void onClick(View v) {
        setHour = tp.getHour();
        setMinute = tp.getMinute();
        tv.setText(setHour + " 시" + setMinute + " 분에 알람이 울립니다.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}

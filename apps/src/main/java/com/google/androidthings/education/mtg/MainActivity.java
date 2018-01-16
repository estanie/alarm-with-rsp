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
import android.os.Bundle;
import android.util.Log;
import android.icu.util.Calendar;
import android.widget.TextView;
import android.widget.TimePicker;


import static com.google.androidthings.education.mtg.MusicPlayer.Note;
import static com.google.androidthings.education.mtg.Led.ALL;

public class MainActivity extends Activity implements TimePicker.OnTimeChangedListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    TextView tv;
    TimePicker tp;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);//시간 설정하는 레이아웃이에요! res/layout에 보면 있어용

        c = Calendar.getInstance();
        tv = (TextView) findViewById(R.id.tv);
        tp = (TimePicker) findViewById(R.id.tp);
        tp.setOnTimeChangedListener(this);

        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute){
        tv.setText("현재 설정된 시간 \n시:분 |" + hourOfDay + ":"  + minute);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}

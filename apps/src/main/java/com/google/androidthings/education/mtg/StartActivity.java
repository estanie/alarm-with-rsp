package com.google.androidthings.education.mtg;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by estanie on 2018-01-15.
 */

public class StartActivity extends Activity {
    private static final String TAG = StartActivity.class.getSimpleName();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_start);
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG,"Button Clicked");
                intent = new Intent(StartActivity.this,WaitingActivity.class);
                startActivity(intent);
            }
        });


    }
}

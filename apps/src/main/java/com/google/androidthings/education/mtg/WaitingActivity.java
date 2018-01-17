package com.google.androidthings.education.mtg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by estanie on 2018-01-15.
 */

public class WaitingActivity extends Activity {
    private static final String TAG = WaitingActivity.class.getSimpleName();
    int times;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_waiting);
        Button waitingButton = (Button) findViewById(R.id.waiting_button);
        // @TODO: 버튼으로 입력 받기 및 완료되면 버튼 제거하기.
        waitingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent;
                Log.d(TAG, "Button Clicked");
                intent = new Intent(WaitingActivity.this, ResultActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}

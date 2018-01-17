package com.google.androidthings.education.mtg;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.androidthings.education.mtg.MyService.MyBinder;

/**
 * Created by estanie on 2018-01-15.
 */

public class ResultActivity extends Activity {
    private static final String TAG = ResultActivity.class.getSimpleName();
    MyService ms = null;
    boolean isService = false;

    TextView winner;
    TextView winTimes;
    Button nextStep;
    ServiceConnection conn = new ServiceConnection(){
        public void onServiceConnected(ComponentName name, IBinder service){
            MyBinder mb = (MyBinder) service;
            ms = mb.getService();
            Log.e(TAG, "Service is binding");
            isService = true;
            afterBinding();
        }
        public void onServiceDisconnected(ComponentName name){
            isService = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_result);

        nextStep = (Button)findViewById(R.id.next_step);
        winner = (TextView)findViewById(R.id.winner);
        winTimes = (TextView)findViewById(R.id.win_times);

        Intent serviceIntent = new Intent(ResultActivity.this, MyService.class);
        bindService(serviceIntent,conn, Context.BIND_AUTO_CREATE);

        nextStep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent;
                // @TODO: 서비스 바인딩해서 이긴 횟수
                    if (ms.game.isGamePlaying()) {
                        intent = new Intent(ResultActivity.this, WaitingActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        intent = new Intent(ResultActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }
        });
    }

    private void afterBinding() {
        ms.game.rand_computer();
            if (ms.game.isWin()) {
                winner.setText("You Win!");
            } else {
                winner.setText("Com Win!");
            }
            winTimes.setText("현재까지"+ ms.game.getWin_number()+ "회 이겼습니다.");

    }
}

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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.androidthings.education.mtg.MyService.MyBinder;

/**
 * Created by estanie on 2018-01-15.
 */

public class ResultActivity extends Activity {
    private static final String TAG = ResultActivity.class.getSimpleName();
    MyService ms = null;
    boolean isService = false;
    int userResult;
    int comResult;
    ImageView userResultImage;
    ImageView comResultImage;
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
        userResultImage = (ImageView)findViewById(R.id.my_result);
        comResultImage = (ImageView)findViewById(R.id.com_result);

        Intent serviceIntent = new Intent(ResultActivity.this, MyService.class);
        bindService(serviceIntent,conn, Context.BIND_AUTO_CREATE);

        nextStep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent;
                unbindService(conn);
                    if (ms.game.isGamePlaying()) {
                        intent = new Intent(ResultActivity.this, WaitingActivity.class);
                    } else {
                        intent = new Intent(ResultActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                }
        });
    }

    private void afterBinding() {
        ms.game.rand_computer();
        userResult = ms.game.getUserKey();
        comResult = ms.game.getComKey();
        setImage(userResult,userResultImage);
            if (ms.game.isUserWin()) {
                winner.setText("WIN!");
            } else {
                winner.setText("LOSE!");
            }
            setImage(comResult,comResultImage);
            ms.myDevice.ledOn(ms.game.getWin_number());
            winTimes.setText("현재까지 "+ ms.game.getWin_number()+ "회 이겼습니다.");

    }
    private void setImage(int result,ImageView view){

        switch (result){
            case 1:
                view.setImageResource(R.drawable.scissor);
                break;
            case 2:
                view.setImageResource(R.drawable.rock);
                break;
            case 3:
                view.setImageResource(R.drawable.paper);
                break;
        }
    }

}

package com.google.androidthings.education.mtg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by estanie on 2018-01-15.
 */

public class ResultActivity extends Activity {
    private static final String TAG = ResultActivity.class.getSimpleName();
    private static final int RSP_TIMES = 5;

    int times;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_result);
        Button nextStep = (Button)findViewById(R.id.next_step);

        TextView winner = (TextView)findViewById(R.id.winner);
        boolean getResult = true; //  결과 매칭해서 받아오는 부분 필요

        if (getResult){
            winner.setText("You Win!");
        } else {
            winner.setText("Com Win!");
        }
        times = 5;//이긴 횟수 받는 부분!
        nextStep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent;
                if (times<RSP_TIMES) {
                    intent = new Intent(ResultActivity.this, WaitingActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                else{
                    intent = new Intent(ResultActivity.this, MainActivity.class);
                    startActivity(intent);

                }
                finish();
            }
        });
    }
}

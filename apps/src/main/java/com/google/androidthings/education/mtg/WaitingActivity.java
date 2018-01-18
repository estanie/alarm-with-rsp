package com.google.androidthings.education.mtg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
        ImageView rock = (ImageView) findViewById(R.id.rock);
        ImageView scissor = (ImageView) findViewById(R.id.scissor);
        ImageView paper = (ImageView) findViewById(R.id.paper);
        // @TODO: 버튼으로 입력 받기 및 완료되면 버튼 제거하기.

        rock.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Intent intent = new Intent(WaitingActivity.this, ResultActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("userResult",2);
                startActivity(intent);

            }
        });
        scissor.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Intent intent = new Intent(WaitingActivity.this, ResultActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("userResult",1);
                startActivity(intent);
            }
        });
        paper.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Intent intent = new Intent(WaitingActivity.this, ResultActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("userResult",3);
                startActivity(intent);
            }
        });
    }

}

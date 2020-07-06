package com.biswanath.handlerandlooper.handlerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.biswanath.handlerandlooper.R;

public class ThreadExpActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ThreadExpActivity";

    private TextView btnStart, btnStop,txtCount;
    private int count = 0;
    private boolean mStopLoop= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_exp);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        txtCount = findViewById(R.id.txtCount);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mStopLoop = true;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mStopLoop){
                            try{
                                Thread.sleep(1000);
                                count++;
                            }catch (InterruptedException e){
                                Log.i(TAG,e.getMessage());
                            }
                            Log.i(TAG,"Thread id in while loop: "+Thread.currentThread().getId()+", Count : "+count);

                            // TODO: 1
                            //uncomment the below line to examine what happen
                            //txtCount.setText(""+count);
                        }
                    }
                }).start();
                break;
            case R.id.btnStop:
                mStopLoop = false;
                break;
        }
    }
}

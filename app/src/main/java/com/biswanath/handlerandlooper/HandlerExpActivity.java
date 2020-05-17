package com.biswanath.handlerandlooper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HandlerExpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HandlerExpActivity";

    private TextView btnStart, btnStop,txtCount;
    private int count = 0;
    private boolean mStopLoop= false;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_exp);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        txtCount = findViewById(R.id.txtCount);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        //handler which post data in looper
        //here looper is main looper
        mHandler = new Handler(getMainLooper());
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

                            // TODO: 2
                            //Using Handler to post or send data from this background thread to main thread
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    txtCount.setText(""+count);
                                }
                            });
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

package com.biswanath.handlerandlooper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class CustomLooperExpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CustomLooperExpActivity";

    private TextView btnStart, btnStop,txtCount;
    private int count = 0;
    private boolean mStopLoop= false;
    private LooperThread looperThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_exp);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        txtCount = findViewById(R.id.txtCount);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        Log.d(TAG, "Main Thread id of thread " +Thread.currentThread().getId());


        // TODO: 3
        //instantiate custom looper and start
        looperThread = new LooperThread();
        looperThread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mStopLoop = true;
                // TODO: 4
                //Try this first
                //executeOnCustomLooper();
                // TODO: 5
                executeCustomLooperWithHandler();
                break;
            case R.id.btnStop:
                mStopLoop = false;
                break;
        }
    }
    public void executeCustomLooperWithHandler(){
        looperThread.handler.post(new Runnable() {
            @Override
            public void run() {
                while (mStopLoop){
                    try{
                        Log.d(TAG, "Thread id of thread that send message" +Thread.currentThread().getId());
                        Thread.sleep(1000);
                        count++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtCount.setText(""+count);
                            }
                        });

                    }catch (InterruptedException e){
                        Log.i(TAG,e.getMessage());
                    }
                    Log.i(TAG,"Thread id in while loop: "+Thread.currentThread().getId()+", Count : "+count);
                }
            }
        });
    };

    public void executeOnCustomLooper(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mStopLoop){
                    try{
                        Log.d(TAG, "Thread id of thread that send message" +Thread.currentThread().getId());
                        Thread.sleep(1000);
                        count++;
                        Message message = new Message();
                        message.obj = ""+count;
                        looperThread.handler.sendMessage(message);

                    }catch (InterruptedException e){
                        Log.i(TAG,e.getMessage());
                    }
                    Log.i(TAG,"Thread id in while loop: "+Thread.currentThread().getId()+", Count : "+count);
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO: it should be done or it otherwise it drain battery
        if(looperThread!=null && looperThread.isAlive()){
            looperThread.handler.getLooper().quit();
        }
    }
}

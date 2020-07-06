package com.biswanath.handlerandlooper.handlerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.biswanath.handlerandlooper.R;

public class CustomHandlerActivity extends AppCompatActivity  implements View.OnClickListener {
    private static final String TAG = "CustomHandlerActivity";

    private TextView btnStart, btnStop,txtCount;
    private int count = 0;
    private boolean mStopLoop= false;
    private CustomHandlerThread customHandlerThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_handler);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        txtCount = findViewById(R.id.txtCount);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        Log.d(TAG, "Main Thread id of thread " +Thread.currentThread().getId());


        // TODO: 6
        //instantiate custom CustomHandlerThread and start
        customHandlerThread = new CustomHandlerThread("customHandlerThread");
        customHandlerThread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mStopLoop = true;
                // TODO: 7
                executeCustomHandler();
                break;
            case R.id.btnStop:
                mStopLoop = false;
                break;
        }
    }
    public void executeCustomHandler(){
        customHandlerThread.handler.post(new Runnable() {
            @Override
            public void run() {
                while (mStopLoop){
                    try{
                        Log.d(TAG, "Thread id of thread that send message" +Thread.currentThread().getId());
                        Thread.sleep(1000);
                        count++;
                        customHandlerThread.handler.sendMessage(getMessageWithCount(""+count));

                    }catch (InterruptedException e){
                        Log.i(TAG,e.getMessage());
                    }
                    Log.i(TAG,"Thread id in while loop: "+Thread.currentThread().getId()+", Count : "+count);
                }
            }
        });
    };

    private Message getMessageWithCount(String count){
        Message  msg = new Message();
        msg.obj = ""+count;
        return msg;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO: it should be done or it otherwise it drain battery
        if(customHandlerThread!=null ){
            customHandlerThread.getLooper().quit();
        }
    }
}

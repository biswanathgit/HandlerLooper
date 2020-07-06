package com.biswanath.handlerandlooper.handlerexample;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

public class CustomHandlerThread extends HandlerThread {
    public static final String TAG = CustomHandlerThread.class.getSimpleName();

    public Handler handler;
    public CustomHandlerThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "CustomHandlerThread: Thread is :"+ Thread.currentThread().getName() + "   :  message :"+msg.obj);
            }
        };
    }
}

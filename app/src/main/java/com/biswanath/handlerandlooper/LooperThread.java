package com.biswanath.handlerandlooper;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread {
    public static final String  TAG = LooperThread.class.getSimpleName();
    Handler handler;

    @Override
    public void run() {
        Looper.prepare();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "CustomLooperThread: Thread is :"+ Thread.currentThread().getName() + "   :  message :"+msg.obj);

            }
        };

        Looper.loop();
    }
}

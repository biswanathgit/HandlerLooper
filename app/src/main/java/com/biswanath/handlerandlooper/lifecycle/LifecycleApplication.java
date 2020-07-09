package com.biswanath.handlerandlooper.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class LifecycleApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "Lifecycle";

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG, " onCreated: " + activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, " onStarted: " + activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, " onResumed: " + activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG, " onPaused: " + activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG, " onStopped: " + activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.d(TAG, " SaveInstanceState: " + activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG, " onDestroyed: " + activity);
    }
}

package com.biswanath.handlerandlooper.lifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.biswanath.handlerandlooper.R;

public class CActivity extends AppCompatActivity {
    private static final String TAG = "CActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        Log.d(TAG, "onCreate: " + this.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart " + this.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume " + this.toString());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart " + this.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause " + this.toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState " + this.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState " + this.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop " + this.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy " + this.toString());
    }


}

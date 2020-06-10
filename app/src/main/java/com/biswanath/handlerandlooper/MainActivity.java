package com.biswanath.handlerandlooper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private TextView txtThreadExp, txtHandlerExp, txtCustomLooper,txtCustomHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtThreadExp = findViewById(R.id.txtThreadExp);
        txtHandlerExp = findViewById(R.id.txtHandlerExp);
        txtCustomLooper = findViewById(R.id.txtCustomLooper);
        txtCustomHandler = findViewById(R.id.txtCustomHandler);


        txtThreadExp.setOnClickListener(this);
        txtHandlerExp.setOnClickListener(this);
        txtCustomLooper.setOnClickListener(this);
        txtCustomHandler.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtThreadExp:
                startThreadExpActivity();
                break;
            case R.id.txtHandlerExp:
                startHandlerExpExpActivity();
                break;

            case R.id.txtCustomLooper:
                startCustomLooperExpExpActivity();
                break;
            case R.id.txtCustomHandler:
                startCustomLooperExpExpActivity();
                break;
        }
    }

    private void startThreadExpActivity() {
        Intent i = new Intent(MainActivity.this, ThreadExpActivity.class);
        startActivity(i);
    }

    private void startHandlerExpExpActivity() {
        Intent i = new Intent(MainActivity.this, HandlerExpActivity.class);
        startActivity(i);
    }

    private void startCustomLooperExpExpActivity() {
        Intent i = new Intent(MainActivity.this, CustomLooperExpActivity.class);
        startActivity(i);
    }

    private void startCustomHandlerExpExpActivity() {
        Intent i = new Intent(MainActivity.this, CustomHandlerThread.class);
        startActivity(i);
    }
}

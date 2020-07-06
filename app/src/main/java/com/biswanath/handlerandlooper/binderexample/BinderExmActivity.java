package com.biswanath.handlerandlooper.binderexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.biswanath.handlerandlooper.IMyAidlInterface;
import com.biswanath.handlerandlooper.R;
import com.biswanath.handlerandlooper.handlerexample.MainActivity;

public class BinderExmActivity extends AppCompatActivity {
    private static final String TAG = "BinderExmActivity";
    IMyAidlInterface exampleService;
    Button btn1, btn2;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_exm);

        btn1= findViewById(R.id.btn1);
        btn2= findViewById(R.id.btn2);
        txt= findViewById(R.id.txtShow);

        btn1.setOnClickListener(v -> {
            try {
                Log.d(TAG, "onCreate: btn1  SerialNumber"+ exampleService.getSerialNumber());
                txt.setText(exampleService.getSerialNumber());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        btn2.setOnClickListener(v -> {
            try {
                Log.d(TAG, "onCreate: btn2  VersionCodes"+ exampleService.getVersionCodes());
                txt.setText(exampleService.getVersionCodes());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });


        initService();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder boundService) {
            exampleService = IMyAidlInterface.Stub.asInterface(boundService);
            Log.d(TAG, "onServiceConnected: ");
            Toast.makeText(BinderExmActivity.this, "AIDL service connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            exampleService = null;
            Toast.makeText(BinderExmActivity.this, "AIDL service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };

    private void initService() {
        Intent i = new Intent(this,ExampleService.class);
        boolean resultIntent = bindService(i,mConnection, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "initService: isCreated " + resultIntent);

        if(resultIntent){
            btn1.setEnabled(true);
            btn2.setEnabled(true);
        }
    }

    private void releaseService() {
        unbindService(mConnection);
        mConnection = null;
        Log.d(TAG, "releaseService() trigger");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }
}

package com.biswanath.handlerandlooper.binderexample;

import android.os.Build;
import android.os.RemoteException;

import com.biswanath.handlerandlooper.BuildConfig;
import com.biswanath.handlerandlooper.IMyAidlInterface;

public class IMyAidlInterfaceImpl extends IMyAidlInterface.Stub {

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public String getSerialNumber() throws RemoteException {
        return Build.BOARD;
    }

    @Override
    public String getVersionCodes() throws RemoteException {
        return Build.DEVICE;
    }
}

package com.yf.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver-1";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: 11111");
       if(intent!=null){
           String action = intent.getAction();
           Log.d(TAG, "onReceive: 播"+action);
       }
    }
}

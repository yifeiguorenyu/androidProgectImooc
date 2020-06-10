package com.yf.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.yf.service.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private static final String TAG = "MainActivity-1";
    ServiceConnection conn =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            MyService.MyBind bind = (MyService.MyBind) iBinder;
//            int process = bind.getProcess();
//            Log.d(TAG, "onServiceConnected: =="+process);

            IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            try {
                iMyAidlInterface.showProgress();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private boolean isBind =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void start(View view) {
        intent = new Intent(this,MyService.class);
        startService(intent);
    }

    public void stop(View view) {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }
    public void bind(View view) {
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,conn, BIND_AUTO_CREATE);
        isBind = true;
    }

    public void unbind(View view) {

        if(conn!=null&&isBind){
            unbindService(conn);
isBind=false;
        }
    }
}

package com.yf.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = "Main5Activity-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Log.d(TAG, "onCreate: "+getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: busprovider==="+BusProvider.getBus());
        BusProvider.getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getBus().unregister(this);
    }

    public void tosetVal(View view) {
        MyApplication mApp = (MyApplication) getApplication();
        mApp.setAge(15);
    }

    public void togetVal(View view) {
        MyApplication mApp = (MyApplication) getApplication();
        String name = mApp.getName();
        Log.d(TAG, "togetVal: name====="+name);
    }
}

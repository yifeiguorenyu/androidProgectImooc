package com.yf.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.otto.Bus;

public class Main4Activity extends AppCompatActivity {
    private static final String TAG = "Main4Activity-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Log.d(TAG, "onCreate: "+getApplication());
    }

    public void tofive(View view) {
        Intent intent = new Intent(Main4Activity.this,Main5Activity.class);
        startActivity(intent);
    }

    public void startServi(View view) {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    public void tosetVal(View view) {
        MyApplication myApp = (MyApplication) getApplication();
        myApp.setName("yifei");

    }

    public void togetVal(View view) {
        MyApplication myApp = (MyApplication) getApplication();
        String name = myApp.getName();
        Log.d(TAG, "togetVal: name=="+name);
        int age = myApp.getAge();
        Log.d(TAG, "togetVal: age======"+age);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication myApp = (MyApplication) getApplication();
        Bus bus = myApp.getBus();
        Log.d(TAG, "onResume: ==="+bus);
        bus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication myApp = (MyApplication) getApplication();
        Bus bus = myApp.getBus();
        bus.unregister(this);
    }
}

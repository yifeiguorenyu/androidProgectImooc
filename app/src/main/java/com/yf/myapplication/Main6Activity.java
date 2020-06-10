package com.yf.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main6Activity extends AppCompatActivity {
    private static final String TAG = "Main6Activity-1";
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        Log.d(TAG, "onCreate: format==="+format);
        textView = findViewById(R.id.idtime);
        textView.setText(format);
        Log.d(TAG, "acitvity====="+this);
        if(savedInstanceState!=null){
            Log.d(TAG, "onCreate: bundle"+savedInstanceState);
            String createtime = savedInstanceState.getString("createtime");
            textView.setText(createtime);
        }
    }

    public void commit(View view) {
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: "+outState);
        outState.putString("createtime",textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ==="+savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ===="+this);
    }
}

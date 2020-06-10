package com.yf.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    private TextView idtextcoast;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        idtextcoast = findViewById(R.id.idtextcoast);
        editText = findViewById(R.id.ideditext);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.yf.ssss");

        registerReceiver(receiver,intentFilter);
    }

    public void sendBro(View view) {
        Intent intent = new Intent("com.yf.ssss");
        intent.putExtra("aaa",editText.getText().toString());
        sendBroadcast(intent);
    }






    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                String aaa = intent.getStringExtra("aaa");


                idtextcoast.setText(aaa);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(receiver!=null){
            unregisterReceiver(receiver);
        }
    }
}

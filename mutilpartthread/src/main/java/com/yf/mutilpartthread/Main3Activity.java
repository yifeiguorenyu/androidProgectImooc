package com.yf.mutilpartthread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    private static final String TAG = "Main3Activity-1";
 private TextView idtv;
 
 
   private Handler mMainHandler;
   private Handler mChildHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        idtv = findViewById(R.id.idtv);
        new ChildThread().start();
        mMainHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                String fromChildStrig = (String) msg.obj;
                idtv.setText(fromChildStrig);
            }
        };
    }

    public void startBtn3(View view) {
        Message msg= new Message();
        msg.what =9;
        msg.obj="hellow";
        mChildHandler.sendMessage(msg);
    }
    
    private class ChildThread extends Thread{
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            mChildHandler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    Log.d(TAG, "handleMessage: child message=="+msg.what+"==="+msg.obj);
                    Message newMsg = Message.obtain();
                    newMsg.obj = msg.obj;
                    mMainHandler.sendMessage(newMsg);
                }
            };
            
            
            
            
            Looper.loop();
        }
    }
}

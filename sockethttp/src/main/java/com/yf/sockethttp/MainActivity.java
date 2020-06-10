package com.yf.sockethttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editext;
    private UdpClient1 client;
    private TextView tvList;
    private static final String TAG = "MainActivity-1";
    String text = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editext = findViewById(R.id.editext);
        tvList = findViewById(R.id.tvList);
        client = new UdpClient1();
    }

    public void btn1(View view) {
        String s = editext.getText().toString();
        text +="Client"+ s + "\n";
        tvList.setText(text);
        if (!TextUtils.isEmpty(s)) {
            client.sendMsg(s, new UdpClient1.OnMsgReturnedListener() {
                @Override
                public void onMsgReturned(String msg) {
                    Log.d(TAG, "onMsgReturned: msg=="+msg);
                    appendMsgContent(msg);
                    tvList.setText(text);
                }

                @Override
                public void onError(Exception ex) {

                }
            });
        }
    }

    private void appendMsgContent(String msg){
        text +="Server:"+ msg + "\n";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.onDestory();
    }
}

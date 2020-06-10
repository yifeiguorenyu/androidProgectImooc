package com.yf.sockethttp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yf.sockethttp.tcp1.TcpClientBiz;

public class TcpActivity extends AppCompatActivity implements TcpClientBiz.OnMsgComingListener {
    private EditText editext;
    private TcpClientBiz tcpClient;
    private TextView tvList;
    private static final String TAG = "MainActivity-1";
    String text = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editext = findViewById(R.id.editext);
        tvList = findViewById(R.id.tvList);
        tcpClient = new TcpClientBiz();
        tcpClient.setListener(this);
    }

    public void btn1(View view) {
        String s = editext.getText().toString();
//        text +="Client"+ s + "\n";
        tvList.setText(text);
        if (!TextUtils.isEmpty(s)) {
            tcpClient.sendMsg(s);
        }
    }

    private void appendMsgContent(String msg){
        text +="Server:"+ msg + "\n";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tcpClient.onDestory();
    }

    @Override
    public void onMsgComing(String msg) {
        Log.d(TAG, "onMsgComing: msg=="+msg);
        text+=msg+"\n";
        tvList.setText(text);

    }

    @Override
    public void onError(Exception ex) {
        Log.d(TAG, "onError: "+ex.getMessage());
    }
}

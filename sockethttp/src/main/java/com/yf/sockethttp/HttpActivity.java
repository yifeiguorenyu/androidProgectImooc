package com.yf.sockethttp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yf.sockethttp.http.HttpUtil;

public class HttpActivity extends AppCompatActivity {
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

    }

    public void btn1(View view) {
        String s = editext.getText().toString();
        text +="Client"+ s + "\n";
        tvList.setText(text);
        if (!TextUtils.isEmpty(s)) {
            HttpUtil.doGet("http://www.qqhr.gov.cn/zhuanti/G_search.jsp", new HttpUtil.HttpListener() {
                @Override
                public void onSuccess(String content) {
                    tvList.setText(content);
                }

                @Override
                public void onFail(Exception ex) {
                    Log.d(TAG, "onFail: "+ex.getMessage());
                }
            });
        }
    }


}

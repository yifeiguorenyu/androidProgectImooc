package com.yf.perfectdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity-1";
    public static final int WHAT = 1001;
    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.text)
    TextView textView;
    private int count =8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Message message = Message.obtain();
        message.what= WHAT;
        mHandler.sendMessageDelayed(message,1000);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                mHandler.removeMessages(WHAT);
                MainActivity.this.finish();
            }
        });
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {

            if(msg.what==WHAT){
                count--;
                if(count>0){
                    textView.setText(count+"秒点击跳过");
                    Message message = Message.obtain();
                    message.what= WHAT;
                    mHandler.sendMessageDelayed(message,1000);
                }else {
                    Log.d(TAG, "handleMessage: 1234567788");
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        }
    };


}

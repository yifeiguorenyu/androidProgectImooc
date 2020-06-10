package com.yf.mutilpartthread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main4Activity extends AppCompatActivity {
    private String url = "http://www.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg?setmkt=zh-CN";
    private String filename = "tem_image";
    private static final String TAG = "Main4Activity-1";
    private ImageView imageView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        imageView = findViewById(R.id.imageview);
        mMainHandler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        progressBar.setProgress((Integer) msg.obj);
                        break;
                    case 1:
                        imageView.setImageBitmap((Bitmap) msg.obj);
                        break;
                }

            }
        };
        progressBar= findViewById(R.id.id_pb);
    }

   Handler mMainHandler ;
    public void downloadfile(View view) {
        new ImageLoader().start();
    }

    class ImageLoader extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Log.d(TAG, "run: 11234567");
                URL url1 = null;
                HttpURLConnection conn = null;
                InputStream inputStream = null;
                OutputStream outputStream = null;
                url1 = new URL(url);
                conn = (HttpURLConnection) url1.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setConnectTimeout(20 * 1000);
                inputStream = conn.getInputStream();
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                byte[] data = new byte[1024];
                int seg = 0;
                long total = conn.getContentLength();
                long current = 0;
                while ((seg = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, seg);
                    current += seg;
                    int progress = (int) ((float) current / total * 100);
                    Message mepro=Message.obtain();
                    mepro.what=0;
                    mepro.obj= progress;
                    mMainHandler.sendMessage(mepro);
                    Log.d(TAG, "run: progress==" + progress);
                    SystemClock.sleep(10);
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                if(outputStream!=null){
                    outputStream.close();
                }
                Bitmap bitmap =
                        BitmapFactory.decodeFile(getFileStreamPath(filename).getAbsolutePath());
                Message message= Message.obtain();
                message.what=1;
                message.obj=bitmap;
                mMainHandler.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

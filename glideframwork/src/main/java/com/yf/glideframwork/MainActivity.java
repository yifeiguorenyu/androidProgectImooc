package com.yf.glideframwork;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity-1";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imageView = findViewById(R.id.image);
        Log.d(TAG, "onCreate: imageview==" + imageView);
        RequestBuilder<Drawable> requestBuilder= Glide.with(this).load("ttp://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg");
        Glide.with(this)
                .load("http://img.mukewang.com/55666c0a0001d6b506000338-240-135.jpg")
                .thumbnail(requestBuilder)
                .into(imageView);
    }
}

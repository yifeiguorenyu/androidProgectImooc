package com.yf.perfectdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yf.perfectdemo.view.BookPageBezierHelper;
import com.yf.perfectdemo.view.BookPageView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookActivity extends AppCompatActivity {
    public static final String FILE_PATH = "filePath";
    @BindView(R.id.book_page_view)
    BookPageView bookPageView;
    @BindView(R.id.progress)
    TextView textView;
    private BookPageBezierHelper bookPageBezierHelper;
    @BindView(R.id.setting_view)
    RelativeLayout settingView;


    private static final String TAG = "BookActivity-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);

        //get size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        bookPageBezierHelper = new BookPageBezierHelper(width, height);
        bookPageView.setBookPageBezierHelper(bookPageBezierHelper);

        Bitmap currentPage = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Bitmap nextPage = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        bookPageView.setOnUserNeedSettingListener(new BookPageView.OnUserNeedSettingListener() {
            @Override
            public void onUserNeedSetting() {
                if(settingView.getVisibility()==View.VISIBLE){
                    settingView.setVisibility(View.GONE);
                }else {
                    settingView.setVisibility(View.VISIBLE);

                }
            }
        });
        bookPageView.setBitmaps(currentPage,nextPage);
        Intent intent = getIntent();
        if(intent!=null){
            String filePath = intent.getStringExtra(FILE_PATH);
            if(!TextUtils.isEmpty(filePath)){
                try {
                    bookPageBezierHelper.setBackground(this,R.drawable.book_bg);
                    bookPageBezierHelper.setOnProgressChangedListener(new BookPageBezierHelper.OnProgressChangedListener() {
                        @Override
                        public void setProgress(int currentLength, int totalLength) {
                            textView.setText(currentLength*100/totalLength+"%");
                        }
                    });
                    bookPageBezierHelper.openBook(filePath);

                    bookPageBezierHelper.draw(new Canvas(currentPage));
                } catch (IOException e) {
                    Log.d(TAG, "error=="+e.getMessage());
                    e.printStackTrace();
                }
            }else {

            }
        }

    }

    public static void start(Context context,String filePath){
        Intent intent = new Intent(context,BookActivity.class);
        intent.putExtra(FILE_PATH,filePath);
        context.startActivity(intent);
    }
}

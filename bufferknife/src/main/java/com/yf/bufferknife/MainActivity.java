package com.yf.bufferknife;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity-1";
    @BindView(R.id.textview)
    TextView textView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.listview)
     ListView listv;

    private List<String> mData = new ArrayList<>(Arrays.asList("simplye use","reclyView use"));

    @OnClick(R.id.btn1) void btn1(){
        Log.d(TAG, "btn1: 12333");
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView.setText("abc123");
        imageView.setImageResource(R.drawable.ic_launcher_background);


        listv.setAdapter(new CategoryAdapter(this,mData));

    }
    @OnItemClick(R.id.listview)
    public void itemClicked(int position){
        Toast.makeText(this,mData.get(position),Toast.LENGTH_LONG).show();
    }


}

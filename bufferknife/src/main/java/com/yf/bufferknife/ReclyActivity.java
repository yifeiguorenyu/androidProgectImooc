package com.yf.bufferknife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ReclyActivity extends AppCompatActivity {
    @BindView(R.id.rclyView)
    RecyclerView mReclyView;

    private List<String> mData = new ArrayList<>(Arrays.asList("simplye use","reclyView use"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recly);
        ButterKnife.bind(this);
       mReclyView.setAdapter(new RvAdapter(this,mData));
       mReclyView.setLayoutManager(new LinearLayoutManager(this));
    }
//    @OnItemClick(R.id.rclyView)
//    public void itemClick(int position){
//        Toast.makeText(this,mData.get(position),Toast.LENGTH_LONG).show();
//    }

}

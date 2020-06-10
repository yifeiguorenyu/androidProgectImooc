package com.yf.testhandler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyView)
    RecyclerView recyclerView;
    MainRecyViewAdapter mainRecyViewAdapter;
    private static final String TAG = "MainActivity-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainRecyViewAdapter=new MainRecyViewAdapter(this,recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(true);//数据方向展示
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainRecyViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainRecyViewAdapter.setLisnerer(new MainRecyViewAdapter.OnItemClickLisnerer() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: ==="+position);
            }
        });

    }

    public void addData(View view) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String s ="第=="+i+"条数据";
            data.add(s);
        }
        mainRecyViewAdapter.setDataSource(data);
    }


    public void changeLayout(View view) {
        if(recyclerView.getLayoutManager().getClass()==LinearLayoutManager.class){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
            recyclerView.setLayoutManager(gridLayoutManager);
        }else if(recyclerView.getLayoutManager().getClass()==GridLayoutManager.class){
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void addOneData(View view) {
        mainRecyViewAdapter.addData(1);
    }

    public void deleteOneData(View view) {
        mainRecyViewAdapter.removeData(1);
    }
}

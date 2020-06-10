package com.yf.testhandler;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnItemClick;

import static android.graphics.Color.RED;

public class MainRecyViewAdapter extends RecyclerView.Adapter<MainRecyViewAdapter.MyViewHolder> {

    private Context context;
    private static final String TAG = "MainRecyViewAdap-1";
    private RecyclerView mRecyclerView;
    private int addDataPostion=-1;

    public MainRecyViewAdapter(Context context,RecyclerView recyclerView) {
        this.context = context;
        dataSource = new ArrayList<>();
        mRecyclerView = recyclerView;

    }

    private List<String> dataSource;

    public void setDataSource(List<String> dataSource) {
        this.dataSource= dataSource;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.linear_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.iv.setImageResource(getIcon(position));
        holder.tv.setText(this.dataSource.get(position));
        if(mRecyclerView.getLayoutManager().getClass()== StaggeredGridLayoutManager.class){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getRanRandOmHeight());
            holder.tv.setLayoutParams(params);
        }else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.tv.setLayoutParams(params);
        }
        //改变itemView的背景颜色
        if(addDataPostion==position){
            holder.mItemView.setBackgroundColor(RED);
        }else {
            holder.mItemView.setBackgroundColor(Color.parseColor("#A4d3ce"));
        }
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lisnerer!=null){
                    lisnerer.onItemClick(position);
                }
            }
        });
    }

    //添加一条数据到指定位置
    public void addData(int position){
        addDataPostion = position;
        dataSource.add(position,"插入的数据");
        notifyItemInserted(position);
        notifyItemRangeChanged(position,dataSource.size()-position);


    }
    //删除一条数据
    public void removeData(int position){
        addDataPostion=-1;
     dataSource.remove(position);
     notifyItemRemoved(position);
        notifyItemRangeChanged(position,dataSource.size()-position);


    }

    @Override
    public int getItemCount() {
        return this.dataSource.size();
    }

    private int getIcon(int position){
        switch (position%5){
            case 0:
                return R.drawable.aa;
            case 1:
                return R.drawable.bb;

            case 2:
                return R.drawable.cc;

            case 3:
                return R.drawable.dd;

            case 4:
                return R.drawable.ee;
                default:
                    return  0;

        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        View mItemView;
        ImageView iv;
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv= itemView.findViewById(R.id.iv);
            tv= itemView.findViewById(R.id.tv);
            mItemView = itemView;
        }
    }

    private int getRanRandOmHeight(){
        return (int) (Math.random()*1000);
    }

    private OnItemClickLisnerer lisnerer;

    public void setLisnerer(OnItemClickLisnerer lisnerer) {
        this.lisnerer = lisnerer;
    }

    //
    public interface OnItemClickLisnerer{
        void onItemClick(int position);
    }
}

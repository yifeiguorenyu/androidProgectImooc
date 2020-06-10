package com.yf.bufferknife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<String> mdatas;

    public RvAdapter(Context context, List<String> datas) {
        inflater = LayoutInflater.from(context);
        mdatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemTv.setText(mdatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_tv)
        TextView itemTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

//    static class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.item_tv)
//        TextView mTextView;
//        public ViewHolder(@NonNull View itemView) {
//
//            super(itemView);
//            ButterKnife.bind(this,itemView);
//        }
//    }
}

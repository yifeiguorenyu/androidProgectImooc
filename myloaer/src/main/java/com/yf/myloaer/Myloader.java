package com.yf.myloaer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Myloader extends BaseAdapter {
    private Context mcontext;
    private List<String> mdatas;
    private LayoutInflater inflater;

    public Myloader(Context context, List<String> datas) {
        mcontext = context;
        mdatas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public String getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textV.setText(getItem(position));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.textV)
        TextView textV;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

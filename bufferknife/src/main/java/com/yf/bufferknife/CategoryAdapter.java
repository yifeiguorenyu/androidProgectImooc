package com.yf.bufferknife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;

    public CategoryAdapter(Context context, List<String> objectes) {
        super(context, -1, objectes);
        inflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_category, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemTv.setText(getItem(position));
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_tv)
        TextView itemTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

//    static class ViewHolder{
//        @BindView(R.id.item_tv)
//        TextView mTextview;
//
//        public ViewHolder(View view){
//            ButterKnife.bind(this,view);
//        }
//
//    }
}

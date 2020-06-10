package com.yf.perfectdemo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class BookListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<BookListResult.DataBean> mList;
    private AsyncHttpClient client;
    private static final String TAG = "BookListAdapter-1";
    public BookListAdapter(Context context, List<BookListResult.DataBean> list) {
        mContext = context;
        mList = list;
        inflater = LayoutInflater.from(context);
        client = new AsyncHttpClient();

    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public BookListResult.DataBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_book, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textName.setText(getItem(position).getBookname());
        String path  =Environment.getExternalStorageDirectory() + "/yf/" + getItem(position).getBookname() + ".txt";
        File file = new File(path);

        if(file.exists()){
            viewHolder.bookbtn.setText("点击打开");
        }else {
            viewHolder.bookbtn.setText("点击下载");
        }
        viewHolder.bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(file.exists()){
                   BookActivity.start(mContext,path);
                }else {
                    client.addHeader("Accept-Encoding","identity");
                    client.get(getItem(position).getBookfile(), new FileAsyncHttpResponseHandler(file) {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                              File file) {
                            Log.d(TAG, "onFailure: error=="+throwable.getMessage());
                                viewHolder.bookbtn.setText("下载失败");
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, File file) {
                            viewHolder.bookbtn.setText("点击打开");
                        }

                        @Override
                        public void onProgress(long bytesWritten, long totalSize) {
                            viewHolder.bookbtn.setText((bytesWritten*100/totalSize)+"%");
                        }
                    });
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.book)
        ImageView book;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.bookbtn)
        Button bookbtn;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

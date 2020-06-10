package com.yf.myloaer.myloader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.yf.myloaer.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomLoader extends AppCompatActivity  {

    @BindView(R.id.listuser)
    ListView listuser;
    private List<UserBean> list = new ArrayList<>();
    LoaderManager manager;
 private UserAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_loader);
        ButterKnife.bind(this);
        mAdapter = new UserAdapter();
        listuser.setAdapter(mAdapter);
        manager = LoaderManager.getInstance(this);
        manager.initLoader(12, null, new LoaderManager.LoaderCallbacks<List<UserBean>>() {
            @NonNull
            @Override
            public Loader<List<UserBean>> onCreateLoader(int id, @Nullable Bundle args) {
                return new UserLoader(CustomLoader.this);
            }

            @Override
            public void onLoadFinished(@NonNull Loader<List<UserBean>> loader, List<UserBean> data) {
                list.addAll(data);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoaderReset(@NonNull Loader<List<UserBean>> loader) {

            }

        });
    }


    class UserAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public UserBean getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(CustomLoader.this).inflate(R.layout.user_item,
                        parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.username.setText(getItem(position).getUsername());
            viewHolder.password.setText(getItem(position).getPassword());
            return convertView;
        }

         class ViewHolder {
            @BindView(R.id.username)
            TextView username;
            @BindView(R.id.password)
            TextView password;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}

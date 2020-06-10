package com.yf.myloaer.myloader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class UserLoader extends AsyncTaskLoader<List<UserBean>> {

    public UserLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(isStarted()){
            forceLoad();
        }
    }

    @Nullable
    @Override
    public List<UserBean> loadInBackground() {
        List<UserBean> list = new ArrayList<>();
        list.add(new UserBean("a123","asb"));
        list.add(new UserBean("be21","yhew"));
        list.add(new UserBean("cqw2","fwq"));
        list.add(new UserBean("43hy","yye"));
        list.add(new UserBean("b32","aew"));
        return list;
    }
}

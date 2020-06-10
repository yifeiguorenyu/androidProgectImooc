package com.yf.sockethttp.tcp1.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class MsgPool {
    private MsgPool(){}
    private static MsgPool mInstance =null;
    public static MsgPool getInstance(){
        if(mInstance==null){
            mInstance = new MsgPool();
        }
        return mInstance;
    }

    private LinkedBlockingDeque<String> mQueue = new LinkedBlockingDeque<>();

    public void sendMsg(String msg){
        try {
            mQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public interface  MsgComingListener{
        void onMsgComing(String msg);
    }

    private List<MsgComingListener> listeners = new ArrayList<>();

    public void addListener(MsgComingListener listener){
        listeners.add(listener);
    }

    public void start(){
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        String msg = mQueue.take();
                        notifimsgcoming(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }

    private void notifimsgcoming(String msg) {
        for (MsgComingListener listener:listeners) {
            listener.onMsgComing(msg);
        }
    }


}

package com.yf.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.yf.service.aidl.IMyAidlInterface;

public class MyService extends Service {
    private static final String TAG = "MyService-1";
    private int i;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        startForegroundService();
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (i = 0; i < 60; i++) {
                    try {
                        sleep(1000);
                        Log.d(TAG, "run: ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
                .start();
    }

    NotificationManager notificationManager;

    String notificationId = "channelId";

    String notificationName = "channelName";

    private void startForegroundService() {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //创建NotificationChannel

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(notificationId,
                    notificationName, NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(channel);

        }

        startForeground(1, getNotification());

    }

    private Notification getNotification() {
        Notification.Builder builder = new Notification.Builder(this)

                .setSmallIcon(R.drawable.ic_launcher_background)

                .setContentTitle("投屏服务")

                .setContentText("投屏服务正在运行...");

        //设置Notification的ChannelID,否则不能正常显示

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setChannelId(notificationId);

        }

        Notification notification = builder.build();

        return notification;

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return new IMyAidlInterface.Stub() {
            @Override
            public void showProgress() throws RemoteException {
                Log.d(TAG, "showProgress: 当前进度" + i);
            }
        };
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    class MyBind extends Binder {
        public int getProcess() {
            return i;
        }
    }
}

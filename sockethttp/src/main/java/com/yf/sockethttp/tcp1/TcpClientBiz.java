package com.yf.sockethttp.tcp1;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TcpClientBiz {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private OnMsgComingListener listener;

    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    public void setListener(OnMsgComingListener listener) {
        this.listener = listener;
    }

    public interface OnMsgComingListener{
        void onMsgComing(String msg);
        void onError(Exception ex);
    }

    public TcpClientBiz() {

        new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket("192.168.77.196", 9090);
                    inputStream = socket.getInputStream();
                    outputStream = socket.getOutputStream();
                    readServerMsg(inputStream);

                } catch (final IOException e) {
                    e.printStackTrace();
                    if(listener!=null){
                        mMainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onError(e);
                            }
                        });


                    }
                }
            }
        }.start();


    }

    private void readServerMsg(InputStream inputStream) {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line =null;
            while ((line=bufferedReader.readLine())!=null){
                if(listener!=null){
                    final String finalLine = line;
                    mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onMsgComing(finalLine);
                        }
                    });
                }
            }
        }catch (Exception ex){

        }

    }

    public void sendMsg(final String msg) {
        new Thread() {
            @Override
            public void run() {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                try {
                    bw.write(msg);
                    bw.newLine();
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


    public void onDestory(){
        try {
            socket.close();
            if(inputStream!=null){
                inputStream.close();
            }
            if(outputStream!=null)
            {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.yf.sockethttp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient1 {
    private String mServerIp = "192.168.77.196";
    private InetAddress mServerAddress;
    private int mServerPort = 7777;
    private DatagramSocket mSocket;
    private Scanner mScanner;
    private static final String TAG = "UdpClient-1";


    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {

        }
    };

    public UdpClient1() {
        try {
            mSocket = new DatagramSocket();
            mScanner = new Scanner(System.in);
            mScanner.useDelimiter("\n");
            mServerAddress = InetAddress.getByName(mServerIp);

        } catch (Exception e) {
            Log.d(TAG, "UdpClient1: "+e.getMessage());
        }
    }

    public void start() {
        while (true) {
            try {
                String sendmsg = mScanner.next();
                DatagramPacket datagramPacket = new DatagramPacket(sendmsg.getBytes(),
                        sendmsg.getBytes().length, mServerAddress, mServerPort);
                mSocket.send(datagramPacket);
                byte[] bytes = new byte[1024];
                DatagramPacket receiverPacket = new DatagramPacket(bytes,bytes.length);
                mSocket.receive(receiverPacket);
                System.out.println( "serverMsg"+receiverPacket.getAddress()+"=="+new String(receiverPacket.getData(),0,receiverPacket.getLength()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public interface OnMsgReturnedListener{
        void onMsgReturned(String msg);
        void onError(Exception ex);
    }
    public void sendMsg(final String msg, final OnMsgReturnedListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = msg.getBytes();
                DatagramPacket clientPacket = new DatagramPacket(bytes,bytes.length,mServerAddress,mServerPort);
                try {
                    Log.d(TAG, "send===+"+mSocket);
                    mSocket.send(clientPacket);

                    byte[] receivebytes = new byte[1024];
                    final DatagramPacket receiverPacket = new DatagramPacket(receivebytes,receivebytes.length);
                    mSocket.receive(receiverPacket);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onMsgReturned(new String(receiverPacket.getData(),0,receiverPacket.getLength()));
                        }
                    });

                } catch (final IOException e) {
                    e.printStackTrace();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onError(e);
                        }
                    });

                }
            }
        })
                .start();

    }
    public void onDestory()
    {
        if(mSocket!=null){
            mSocket.close();
        }

    }




}

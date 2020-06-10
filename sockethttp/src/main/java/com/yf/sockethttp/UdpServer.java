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
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UdpServer {
    private InetAddress mInetAddress;
    private int mPort = 7777;
    private DatagramSocket mSocket;
    private static final String TAG = "UdpServer-1";
    private Scanner mScanner;

    public UdpServer(){
        try {
            mInetAddress = InetAddress.getLocalHost();
            mSocket = new DatagramSocket(mPort,mInetAddress);
            mScanner = new Scanner(System.in);
            mScanner.useDelimiter("\n");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            Log.d(TAG, "UdpServer: ");
            e.printStackTrace();
        }

    }

    public void start(){
        while (true){
              try {
                  byte[] bytes= new byte[1024];
                  DatagramPacket receivedPacket = new DatagramPacket(bytes,bytes.length);
                  mSocket.receive(receivedPacket);
                  InetAddress address = receivedPacket.getAddress();
                  int port = receivedPacket.getPort();
                  String clientMsg =  new String(receivedPacket.getData(),0,receivedPacket.getLength()) ;

                 System.out.println( "clietnMsg: address=="+address+"==port=="+port+"==mesg=="+clientMsg);
                  String returnMsg = mScanner.next();
                  byte[] sendbyts = returnMsg.getBytes();
                  DatagramPacket sendPacket = new DatagramPacket(sendbyts,sendbyts.length,address,port);
                  mSocket.send(sendPacket);
              } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        UdpServer udpServer = new UdpServer();
        udpServer.start();
    }
}

package com.yf.sockethttp;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
    private String mServerIp = "192.168.77.196";
    private InetAddress mServerAddress;
    private int mServerPort = 7777;
    private DatagramSocket mSocket;
    private Scanner mScanner;
    private static final String TAG = "UdpClient-1";

    public UdpClient() {
        try {
            mSocket = new DatagramSocket();
            mScanner = new Scanner(System.in);
            mScanner.useDelimiter("\n");
            mServerAddress = InetAddress.getByName(mServerIp);

        } catch (Exception e) {

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

    public static void main(String[] args) {
        UdpClient udpClient = new UdpClient();
        udpClient.start();
    }
}

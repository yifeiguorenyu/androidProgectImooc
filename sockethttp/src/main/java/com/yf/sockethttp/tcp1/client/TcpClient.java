package com.yf.sockethttp.tcp1.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    private Scanner mScanner;

    public TcpClient(){
        mScanner = new Scanner(System.in);
    }


    public void start(){

        Socket socket = null;
        try {
            socket = new Socket("192.168.77.196",9090);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));

            new Thread(){
                @Override
                public void run() {

                    try {

                        String line =null;
                        while ((line=br.readLine())!=null){
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

            while (true){
                String next = mScanner.next();
                bw.write(next);
                bw.newLine();
                bw.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        TcpClient tcpClient = new TcpClient();
        tcpClient.start();
    }
}

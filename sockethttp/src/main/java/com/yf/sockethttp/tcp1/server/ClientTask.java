package com.yf.sockethttp.tcp1.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientTask extends Thread implements MsgPool.MsgComingListener {
    private Socket mSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    public ClientTask(Socket socket){
        this.mSocket = socket;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        try {
            while ((line = bf.readLine())!=null){
                System.out.println("read==="+line);
                //转发消息至其他socket
                MsgPool.getInstance().sendMsg("port===="+mSocket.getPort()+"===="+line+"\n");

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void onMsgComing(String msg) {
        try {
            outputStream.write(msg.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

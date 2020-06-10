package com.yf.mutilpartthread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startBtn1(View view) throws InterruptedException {
//            ScaleTickets thread1 = new ScaleTickets(10);
//            thread1.start();
//        Log.d(TAG, "startBtn1: wait thread done");
//        thread1.join();
//        Log.d(TAG, "join returned");
//        ScaleTickets thread2 = new ScaleTickets(20);
//        thread2.start();
//        ScaleTickets thread3 = new ScaleTickets(6);
//        thread3.start();
//        ScaleTickets thread4 = new ScaleTickets(10);
//        thread4.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(15000);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
                    System.out.println("运行线程一"+simpleDateFormat.format(new Date()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.run();
        System.out.println("abc");
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(12000);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
                    System.out.println("运行线程二"+simpleDateFormat.format(new Date()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.run();
    }

    private class ScaleTickets extends Thread {
        private int tickets = 0;

        public ScaleTickets(int tickets) {
            this.tickets = tickets;
        }

        @Override
        public void run() {
            super.run();
            //scal tickes

            while (tickets>0){
                saleTicket();
            }
            Log.d(TAG, "run: sale done ");
        }



        private void saleTicket(){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "saleTicket: left"+currentThread().getName()+"====="+tickets);
            tickets--;
        }
    }
}

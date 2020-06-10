package com.yf.mutilpartthread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity-1";
    private SaleTicket saleTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        saleTicket = new SaleTicket(10);
    }

    public void startBtn2(View view) {
        new Thread(saleTicket,"--123--").start();
    }

    private class SaleTicket implements Runnable {
        private int tickets = 0;

        public SaleTicket(int tickets) {
            this.tickets = tickets;
        }

        @Override
        public void run() {
            while (tickets > 0) {
                sale();

            }

            Log.d(TAG, "没票了");
        }

        private void sale() {

            tickets--;
            Log.d(TAG, "卖了dddd left===="+Thread.currentThread().getName()+"===" + tickets);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

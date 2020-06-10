package com.yf.myapplication;

import com.squareup.otto.Bus;

public class BusProvider {
    private BusProvider(){}
    private static final Bus bus = new Bus();
    public static  Bus getBus(){
        return bus;
    }
}

package com.bruce.chen.part3.ch5.dp;

import java.util.concurrent.DelayQueue;

public class TestDelayQueue {

    static DelayQueue<ItemVo<Order>> delayQueue =  new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {

        new Thread(new PutOrder(delayQueue)).start();
        new Thread(new FetchOrder(delayQueue)).start();


        for(int i=1;i<15;i++){
            Thread.sleep(500);
            System.out.println(i*500);
        }

    }

}

package com.bruce.chen.part3.ch5.dp;


import java.util.concurrent.DelayQueue;

/**
 * 取出订单功能
 */
public class FetchOrder implements Runnable{
    private DelayQueue<ItemVo<Order>> queue;

    public FetchOrder(DelayQueue<ItemVo<Order>> queue){
        this.queue = queue;
    }


    @Override
    public void run() {
        while (true) {
            try {
                ItemVo<Order> item = queue.take();
                Order order = item.getData();
                System.out.println("Get From Queue:"+"data="
                        +order.getOrderNo()+";"+order.getOrderMoney());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

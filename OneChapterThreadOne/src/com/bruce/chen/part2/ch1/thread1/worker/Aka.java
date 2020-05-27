package com.bruce.chen.part2.ch1.thread1.worker;

public class Aka {

    private static final int MAX_VALUE = 20;

    private int cartridge = 0;

    public Aka(int cartridge) {
        this.cartridge = cartridge;
    }

    /**
     * 生产者
     */
    public synchronized void produce() {
        try {
//            while (cartridge < MAX_VALUE) {
//                cartridge++;
//                System.out.println(Thread.currentThread().getName() + "压入子弹-----");
//                wait();
//            }
//
//            System.out.println(Thread.currentThread().getName() + "弹夹已满-----");
//
//            notifyAll();
            while (cartridge > MAX_VALUE) {
                System.out.println(Thread.currentThread().getName() + "弹夹已满-----");
                wait();
            }
            cartridge++;
            System.out.println(Thread.currentThread().getName() + "压入子弹-----");
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * 消费者
     */
    public synchronized void consume() {
//        while (cartridge > 0) {
//            cartridge--;
//            notifyAll();
//            System.out.println(Thread.currentThread().getName() + "一枚子弹 射出");
//        }
//
//        try {
//            System.out.println(Thread.currentThread().getName() + "等待子弹压入");
//            wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        try {
            while (cartridge < 0) {
                System.out.println(Thread.currentThread().getName() + "等待子弹压入");
                wait();
            }
            cartridge--;
            notifyAll();
            System.out.println(Thread.currentThread().getName() + "一枚子弹 射出");

        } catch (InterruptedException e) {
            e.printStackTrace();

        }

    }

}

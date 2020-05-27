package com.bruce.chen.part2.ch1.syn.volatiledemo;


/**
 * Volatile 轻量同步机制
 *
 */
public class VolatileDemo1 {

//    public  static boolean isFlag;
    //此处显示加了volatile同步
    public volatile static boolean isFlag;

    public static class Thread1 extends Thread{

        @Override
        public void run() {
            super.run();
            System.out.println("thread1 starting");
            while (!isFlag);
            System.out.println("123123213123");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(isFlag);
        Thread1 thread1 = new Thread1();
        thread1.start();
        Thread.sleep(5);
        isFlag = true;
        System.out.println("ending");

    }
}

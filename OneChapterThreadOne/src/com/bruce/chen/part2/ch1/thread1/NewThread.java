package com.bruce.chen.part2.ch1.thread1;

/**
 * JAVA创建线程有两种方式
 * 1、new Thread() 对于线程的抽象
 * 2、implement Runnable  对业务的抽象
 */
public class NewThread {

    private static class ChenThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("i am chen");
        }
    }

    public static class ChenRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("i am chen Runnable");
        }
    }

    public static void main(String[] args) {

        ChenThread chenThread = new ChenThread();
        chenThread.start();
//        chenThread.run();
//        chenThread.run();
//        chenThread.stop();

        new Thread(new ChenRunnable()).start();

    }

}

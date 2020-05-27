package com.bruce.chen.part2.ch1.thread1;


/**
 * 守护线程
 * 主线程结束 守护线程也会结束
 */
public class DaemonThread {


    static class UseThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                while (!isInterrupted()) {
                    System.out.println(Thread.currentThread().getName()
                            + " I am extends Thread.");
                }
                System.out.println(Thread.currentThread().getName()
                        + " interrupt flag is " + isInterrupted());
            } finally {
                //守护线程中finally不一定起作用
                System.out.println(" .............finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
//        守护线程
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(5);
//        中断线程
//        useThread.interrupt();

    }


}
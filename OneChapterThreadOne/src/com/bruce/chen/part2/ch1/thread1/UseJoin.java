package com.bruce.chen.part2.ch1.thread1;

/**
 * 线程join
 * 就绪的线程进行排队，join会插队处理，插队者完成后才会让后续线程执行
 */
public class UseJoin {

    private static class ARunnable implements Runnable{
        private Thread mThread;

        public ARunnable(Thread mThread) {
            this.mThread = mThread;
        }

        @Override
        public void run() {
            System.out.println("AAAA--------------");
            try {
                mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static class BRunnable implements Runnable{

        private Thread mThread;

        public BRunnable(Thread mThread) {
            this.mThread = mThread;
        }
        @Override
        public void run() {
            try {
                mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("BBBB-------------");

        }
    }

    public static void main(String[] args) {
        System.out.println("C--------------开始");
        Thread t = Thread.currentThread();
        Thread at = new Thread(new BRunnable(t));
        Thread a = new Thread(new ARunnable(at));

        at.start();
        a.start();

        System.out.println("结束");

    }


}

package com.bruce.chen.part2.ch1.syn;


/**
 * 锁的演示
 * 代码块锁、方法快锁、类锁
 *
 */
public class SyncTest {

    int i = 0;

    //类锁
    public void setI() {
        synchronized (SyncTest.class) {
            i++;
        }
    }

    //    //代码块加锁
//    public void setI() {
//        synchronized (this){
//        i++;
//        }
//    }

    //方法锁
//    public synchronized void setI() {
//      System.out.println(Thread.currentThread().getName()
//                    +"synStatic going...");
//        i++;
//    }

    public static class CountThread extends Thread {
        private SyncTest syncTest;

        public CountThread(SyncTest syncTest) {
            this.syncTest = syncTest;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100000; i++) {

                syncTest.setI();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest = new SyncTest();
        new CountThread(syncTest).start();
        new CountThread(syncTest).start();
        System.out.println(syncTest.i);
        Thread.sleep(100);
        System.out.println(syncTest.i);

    }


}

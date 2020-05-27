package com.bruce.chen.part2.ch2.aqs;

import com.bruce.chen.part2.ch1.utils.SleepTools;

import java.util.concurrent.locks.Lock;

public class TestLock {

    private void test(){
        final Lock lock = new TrinityLock();
        class Worker extends Thread{
            @Override
            public void run() {
                super.run();
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                try {
                    SleepTools.second(1);
                } finally {
                    lock.unlock();
                }
            }
        }

        // 启动4个子线程
        for (int i = 0; i < 12; i++) {
            Worker w = new Worker();
            //w.setDaemon(true);
            w.start();
        }

        // 主线程每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepTools.second(1);
            //System.out.println();
        }

    }

    public static void main(String[] args) {
       TestLock lock = new TestLock();
       lock.test();
    }

}

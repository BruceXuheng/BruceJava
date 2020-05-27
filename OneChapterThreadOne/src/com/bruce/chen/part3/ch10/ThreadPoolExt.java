package com.bruce.chen.part3.ch10;

import java.util.concurrent.*;

public class ThreadPoolExt {

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                System.out.println("任务操作中。。。");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(2,2,0, TimeUnit.MILLISECONDS
        , new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardOldestPolicy()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
            }

            @Override
            protected void terminated() {
                super.terminated();
            }
        };

        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

    }


}

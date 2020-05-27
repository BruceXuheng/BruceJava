package com.bruce.chen.part2.ch1.forkjoin1;


import java.util.concurrent.CountDownLatch;

/**
 *类说明：演示CountDownLatch用法，
 * 共5个初始化子线程，6个闭锁扣除点，扣除完毕后，主线程和业务线程才能继续执行
 */
public class UseCountDownLatch2 {

    static CountDownLatch latch = new CountDownLatch(3);

   private static class Branch implements Runnable{

       @Override
       public void run() {
           try {
               System.out.println("Branch—"+Thread.currentThread().getId()
               );

               Thread.sleep(2000);
               latch.countDown();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       }
   }

   private static class Main implements Runnable{

       @Override
       public void run() {
           try {
               System.out.println("Main—await");
               latch.await();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(new Branch()).start();
        }
        new Thread(new Main()).start();
//        latch.await();
    }


}

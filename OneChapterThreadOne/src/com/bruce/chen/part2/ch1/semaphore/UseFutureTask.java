package com.bruce.chen.part2.ch1.semaphore;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UseFutureTask {

    private static class UseCallable implements Callable{

        private int sum;

        @Override
        public Object call() throws Exception {
            System.out.println("Callable子线程开始计算");
            Thread.sleep(2000);
            for (int i = 0; i < 5000; i++) {
                sum++;
            }
            System.out.println("计算完成---");
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallable callable =new UseCallable();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        new Thread(futureTask).start();
        int a = futureTask.get();
        System.out.println("---"+a);
//        futureTask.cancel(true);
    }


}

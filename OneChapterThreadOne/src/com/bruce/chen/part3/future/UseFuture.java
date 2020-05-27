package com.bruce.chen.part3.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class UseFuture {

    private static class UseCallable implements Callable<Integer>{
        private int sum;
        @Override
        public Integer call() throws Exception {
            System.out.println("开始计算");
            Thread.sleep(1000);
            for (int i = 0; i < 5000; i++) {
                sum = sum+i;
            }
            System.out.println("Callable子线程计算结束！结果为: "+sum);
            return sum;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UseCallable useCallable = new UseCallable();
        MyFutureTask<Integer> futureTask
                = new MyFutureTask<>(useCallable);
        new Thread(futureTask).start();
        Thread.sleep(2000);
        System.out.println("Main get UseCallable result = "+futureTask.get());
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Sub get UseCallable result = "
                            +futureTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

}

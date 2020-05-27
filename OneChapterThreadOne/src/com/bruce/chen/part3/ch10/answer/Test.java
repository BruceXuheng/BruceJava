package com.bruce.chen.part3.ch10.answer;

import java.util.Random;

public class Test {

    private static class PutThread implements Runnable{

        private IBoundedBuffer<String> boundedBuffer;

        public PutThread(IBoundedBuffer<String> boundedBuffer) {
            this.boundedBuffer = boundedBuffer;
        }

        @Override
        public void run() {
            System.out.println("PutThread is running.....");
            Random r1 = new Random();
            //Random r2 = new Random();
            for(int i =0;i<10;i++){
                int number = r1.nextInt()+10;
                try {

                    //Thread.sleep(r2.nextInt(2000)+500);
                    boundedBuffer.put("count["+i+"] number:"+number);
                    System.out.println("生产"+"count["+i+"]put number:"+number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static class TakeThread implements Runnable{

        private String t_name;
        private IBoundedBuffer<String> boundedBuffer;

        public TakeThread(IBoundedBuffer<String> boundedBuffer,String t_name) {
            this.boundedBuffer = boundedBuffer;
            this.t_name = t_name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(t_name);
            System.out.println(t_name+" is running.....");
            Random r2 = new Random();
            while(!Thread.currentThread().isInterrupted()){
                try {
                    String msg = boundedBuffer.take();
                    System.out.println("消费 get "+msg);
//                    Thread.sleep(r2.nextInt(2000)+500);
                } catch (InterruptedException e) {
                    System.out.println(t_name+"被中断！");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IBoundedBuffer<String> boundedBuffer = new LoopSleepImpl<>(8);
        Thread put1 = new Thread(new PutThread(boundedBuffer));
        Thread take1 = new Thread(new TakeThread(boundedBuffer,"T_1"));
        Thread take2 = new Thread(new TakeThread(boundedBuffer,"T_2"));
        put1.start();
        Thread.sleep(2000);
        take1.start();
        take2.start();


    }
}

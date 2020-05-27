package com.bruce.chen.part3.ch10.block;

public class MyTest {

    static class PutThread extends Thread{

        private IPutGetBlock<String> iPutGetBlock;

        public PutThread(IPutGetBlock<String> iPutGetBlock) {
            this.iPutGetBlock = iPutGetBlock;
        }

        @Override
        public void run() {
            super.run();
//            while (!Thread.currentThread().isInterrupted()){
                for (int i = 0; i < 10; i++) {
                    try {
                        iPutGetBlock.putThread("i="+i);
                        System.out.println("生产=i="+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//            }

        }
    }

    static class TaskThread extends Thread{

        private IPutGetBlock<String> iPutGetBlock;

        public TaskThread(IPutGetBlock<String> iPutGetBlock) {
            this.iPutGetBlock = iPutGetBlock;
        }

        @Override
        public void run() {
            super.run();

            while(!Thread.currentThread().isInterrupted()){
                try {
                    String msg = iPutGetBlock.getThread();
                    System.out.println("消费="+msg);
                } catch (InterruptedException e) {
                    System.out.println("被中断！");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
//        IPutGetBlock waitNotifyBlock = new WaitNotifyBlock(5);
//        IPutGetBlock waitNotifyBlock = new SemphoreBlock<>(5);
        IPutGetBlock waitNotifyBlock = new LockConditionBlock(5);
        new PutThread(waitNotifyBlock).start();
        new TaskThread(waitNotifyBlock).start();
        new TaskThread(waitNotifyBlock).start();

    }

}

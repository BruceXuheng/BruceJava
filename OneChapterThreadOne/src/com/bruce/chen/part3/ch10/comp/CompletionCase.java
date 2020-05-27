package com.bruce.chen.part3.ch10.comp;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletionCase {

    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int TOTAL_TASK = Runtime.getRuntime().availableProcessors()*10;


    public static void main(String[] args) throws Exception {
        CompletionCase c = new CompletionCase();
        c.testByCompletion();
    }


    public void testByQueue() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        //队列，拿任务的执行结果
        LinkedBlockingQueue<Future<Integer>> blockingDeque
                = new LinkedBlockingQueue<Future<Integer>>();
        //增加任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<Integer> future = pool.submit(new WorkTask("ExecutorSe"+i));
            blockingDeque.add(future);
        }

        //检查线程池执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleepTime = blockingDeque.take().get();
            count.addAndGet(sleepTime);
            System.out.println("get count = "+sleepTime);
        }

        pool.shutdown();
        System.out.println("-------------tasks sleep time "+count.get()
                +"ms,and spend time "
                +(System.currentTimeMillis()-start)+" ms");


    }

    public void testByCompletion() throws Exception{
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        CompletionService<Integer> cSevice
                = new ExecutorCompletionService<>(pool);

        // 向里面扔任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            cSevice.submit(new WorkTask("ExecTask" + i));
        }

        // 检查线程池任务执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleptTime = cSevice.take().get();
            //System.out.println(" slept "+sleptTime+" ms ...");
            count.addAndGet(sleptTime);
        }

        // 关闭线程池
        pool.shutdown();
        System.out.println("-------------tasks sleep time "+count.get()
                +"ms,and spend time "
                +(System.currentTimeMillis()-start)+" ms");

    }

}

package com.bruce.chen.part3.future;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 *
 * FutureTask的get方法实现：
 * 1、允许多个线程get这个结果
 * 2、多个线程get这个结果时，可能任务还没运行完。
 * 3、任务运行完成后才能拿到结果，而且这个时候要让get结果的多个线程都可以拿到结果
 */
public class MyFutureTask<V> implements Runnable,Future<V> {

    private Sync sync;

    public MyFutureTask(Callable callable) {
        if(callable==null){
            throw new NullPointerException();
        }
        sync = new Sync(callable);
    }

    private  class Sync extends AbstractQueuedSynchronizer{

        /** 表示任务正在执行 */
        private static final int RUNNING = 1;
        /** 表示任务已经运行完毕 */
        private static final int RAN = 2;
        /** 执行结果 */
        private V result;

        private Callable<V> callable;

        public Sync(Callable callable){
            this.callable = callable;
        }

        /*任务没完成，让get结果的线程全部进入同步队列
         * acquireShared方法返回了，说明可以拿结果了，直接返回结果*/
        V innertGet(){
            acquireShared(0);
            return result;
        }

        /*对任务的状态进行变化，设置执行结果，并唤醒所有等待结果的线程*/
        void innerSet(V v){
            for(;;){
                int s = getState();
                if(s==RAN){
                    return;//==2 任务已经执行完毕
                }
                //尝试将任务状态设置为执行完成
                if(compareAndSetState(s,RAN)){
                    result = v;
                    releaseShared(0);//释放控制权
                    return;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return true;
        }

        /*任务没完成，返回-1，让get结果的线程全部进入同步队列
         * 返回1，可以让所有在同步队列上等待的线程一一去拿结果*/
        @Override
        protected int tryAcquireShared(int arg) {
            return this.getState()==RAN ? 1 : -1;
        }

        void innerRunn(){
            if(this.compareAndSetState(0,RUNNING)){
                if(this.getState() == RUNNING){//再检查一次
                    try {
                        this.innerSet(this.callable.call());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else{

                    this.acquireShared(0);
                }
            }
        }

    }



    @Override
    public void run() {
        this.sync.innerRunn();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDone() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return this.sync.innertGet();
    }



    @Override
    public V get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException,
            TimeoutException {
        throw new UnsupportedOperationException();
    }

}

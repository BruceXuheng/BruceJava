package com.bruce.chen.part2.ch2.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 类说明：共享同步工具类
 */
public class TrinityLock implements Lock {


    private static class Sync extends AbstractQueuedSynchronizer{

        Sync(int count){
            if(count <= 0){
                throw new IllegalMonitorStateException("Count 0");
            }
            setState(count);
        }

        /**
         *
         * @param reduceCount 扣减个数
         * @return 返回小于0，表示当前线程获得同步状态失败
         * 大于0，表示当前线程获得同步状态成功
         */
        @Override
        protected int tryAcquireShared(int reduceCount) {

            for (;;){
                int current = getState();
                int newCount = current-reduceCount;
                if(newCount < 0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        /**
         *
         * @param returnCount 归还个数
         * @return 归还完毕
         */
        @Override
        protected boolean tryReleaseShared(int returnCount) {

            for (;;){
                int current = getState();
                int newCount = current+returnCount;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }

        }

        Condition newCondition(){return new ConditionObject();}

    }

    private Sync sync = new Sync(4);


    /**
     * 锁
     */
    @Override
    public void lock() {
        sync.acquireShared(1);
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1)>=0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

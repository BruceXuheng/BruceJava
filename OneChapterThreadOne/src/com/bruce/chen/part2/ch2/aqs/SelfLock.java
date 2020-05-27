package com.bruce.chen.part2.ch2.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * 类说明：独占锁，不可重入的锁
 */
public class SelfLock implements Lock {

    /*实现一个自定义同步器*/
    private static class Sync extends AbstractQueuedSynchronizer{

        /*判断处于占有状态*/
        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

        /*获取锁 0->1 拿到锁*/
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                /*独占锁*/
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /*释放锁 1->0 释放锁*/
        @Override
        protected boolean tryRelease(int arg) {
            if(getState()==0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /*返回一个Condition ，每个condition都包含了一个condition*/
        Condition newCondition(){
            return  new ConditionObject();
        }
    }

    // 仅需要将操作代理到Sync上即可
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        System.out.println(Thread.currentThread().getName()+" ready get lock");
        sync.acquire(1);
        System.out.println(Thread.currentThread().getName()+" ready get lock over");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName()+" ready release unlock");
        sync.release(1);
        System.out.println(Thread.currentThread().getName()+" ready released unlock over");

    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

package com.bruce.chen.part3.ch10.block;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionBlock<E> implements IPutGetBlock<E>{

    private List<E> queue = new LinkedList<E>();
    private int limit = 2;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();



    public LockConditionBlock() {
        this(2);
    }

    public LockConditionBlock(int limit) {
        this.limit = limit;
    }

    @Override
    public void putThread(E e) throws InterruptedException {
        lock.lock();
        try {
            while(queue.size()==limit){
                notFull.await();
            }
            queue.add(e);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }

    }

    @Override
    public E getThread() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            E e = queue.remove(0);
            notFull.signal();
            return e;
        }finally {
            lock.unlock();
        }

    }

}

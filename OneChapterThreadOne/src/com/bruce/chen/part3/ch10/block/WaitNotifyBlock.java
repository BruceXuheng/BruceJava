package com.bruce.chen.part3.ch10.block;

import java.util.LinkedList;
import java.util.List;

public class WaitNotifyBlock<E> implements IPutGetBlock<E>{

    private List queue = new LinkedList();
    private int limit=0;

    public WaitNotifyBlock(int limit) {
        this.limit = limit;
    }

    @Override
    public synchronized void putThread(E o) throws InterruptedException {
        while (queue.size()>=limit)
            wait();
        queue.add(o);
        notifyAll();
    }

    @Override
    public synchronized E getThread() throws InterruptedException {
        while (queue.size()==0)
            wait();
        E e = (E) queue.remove(0);
        notifyAll();
        return e;
    }
}

package com.bruce.chen.part3.ch10.block;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemphoreBlock<E> implements IPutGetBlock<E> {

    private Semaphore items, spaces;

    private List queue = new LinkedList<>();

    public SemphoreBlock(int items) {
        this.spaces = new Semaphore(items);
        this.items = new Semaphore(0);
    }


    @Override
    public void putThread(E e) throws InterruptedException {
        spaces.acquire();
        synchronized (this) {
            queue.add(e);
        }
        items.release();
    }

    @Override
    public E getThread() throws InterruptedException {
        items.acquire();
        E e;
        synchronized (this) {
            e = (E) queue.remove(0);
        }
        spaces.release();
        return e;
    }

}




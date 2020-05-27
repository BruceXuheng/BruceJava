package com.bruce.chen.part3.ch10.block;

import java.util.LinkedList;
import java.util.List;

public class LoopSleepBlock<E> implements IPutGetBlock<E> {

    private List queue = new LinkedList();
    private final int limit;
    private final static int  INTERVAL = 50;

    public LoopSleepBlock(int limit) {
        this.limit = limit;
    }


    @Override
    public void putThread(E e) throws InterruptedException {
        while(true){
            synchronized (this){
                if(!(queue.size()==0)){
                    queue.add(e);
                    return;
                }
            }
            Thread.sleep(INTERVAL);
        }
    }

    @Override
    public E getThread() throws InterruptedException {
        while(true){
            synchronized (this){
                if(!(queue.size()==0)){
                    return (E)queue.remove(0);
                }
            }
            Thread.sleep(INTERVAL);
        }
    }
}

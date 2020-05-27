package com.bruce.chen.part3.ch10.block;

public interface IPutGetBlock<I> {

    void putThread(I i) throws InterruptedException;
    I getThread() throws InterruptedException;

}

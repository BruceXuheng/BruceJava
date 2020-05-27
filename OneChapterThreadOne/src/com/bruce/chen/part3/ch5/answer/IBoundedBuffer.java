package com.bruce.chen.part3.ch5.answer;

public interface IBoundedBuffer<T> {

    void put(T x) throws InterruptedException;
    T take() throws InterruptedException;

}

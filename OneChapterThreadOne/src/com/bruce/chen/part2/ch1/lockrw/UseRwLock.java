package com.bruce.chen.part2.ch1.lockrw;

import com.bruce.chen.part2.ch1.utils.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseRwLock implements GoodsService {

    private GoodsInfo goodsInfo;

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock getLock = lock.readLock();
    private Lock setLocak = lock.writeLock();


    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public GoodsInfo getNum() {
        getLock.lock();
        try {
            SleepTools.ms(5);
            return this.goodsInfo;
        } finally {
            getLock.unlock();
        }
    }

    @Override
    public void setNum(int number) {
        setLocak.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        }finally {
            setLocak.unlock();
        }
    }
}

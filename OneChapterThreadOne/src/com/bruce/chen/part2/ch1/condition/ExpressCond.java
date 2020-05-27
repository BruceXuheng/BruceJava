package com.bruce.chen.part2.ch1.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExpressCond {

    public final static String CITY = "SHANGHAI";
    private int km;/*快递到达地点*/
    private String site;/*快递到达地点*/
    private Lock kmLock = new ReentrantLock();
    private Lock siteLock = new ReentrantLock();
    private Condition kmCond = kmLock.newCondition();
    private Condition siteCond = siteLock.newCondition();

    public ExpressCond() {
    }

    public ExpressCond(int km, String site) {
        this.km = km;
        this.site = site;
    }

    public void changeKm() {
        kmLock.lock();
        try {
            this.km = 101;
            kmCond.signal();
        } finally {
            kmLock.unlock();
        }
    }

    public void changeSite() {
        siteLock.lock();
        try {
            this.site = "BeiJing";
            siteCond.signal();
        } finally {
            siteLock.unlock();
        }
    }

    public void waitKm() {
        kmLock.lock();
        try {
            while (this.km < 100) {
                try {
                    kmCond.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } finally {
            kmLock.unlock();
        }
        System.out.println("the Km is " + this.km + ",I will change db");
    }

    public void waitSite() {
        siteLock.lock();
        try {

            while (this.site.equals(CITY)) {
                try {
                    siteCond.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            siteLock.unlock();
        }
        System.out.println("the site is " + this.site + ",I will call user");
    }


}

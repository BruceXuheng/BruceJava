package com.bruce.chen.part2.ch1.condition;

public class TestCondition {

    static ExpressCond cond = new ExpressCond(0,ExpressCond.CITY);

    public static class checkThread extends Thread{
        @Override
        public void run() {
            super.run();
            cond.waitKm();
        }
    }

    public static class CheckSite extends Thread{
        @Override
        public void run() {
            super.run();
            cond.waitSite();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new checkThread().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cond.changeKm();

    }


}

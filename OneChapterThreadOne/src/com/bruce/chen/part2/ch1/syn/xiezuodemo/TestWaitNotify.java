package com.bruce.chen.part2.ch1.syn.xiezuodemo;

public class TestWaitNotify {

    private static Express express = new Express(0,Express.CITY);

    /**
     * 地点
     */
    public static class SiteThread extends Thread{
        @Override
        public void run() {
            super.run();
            express.waitSite();
        }
    }

    /**
     * 里程
     */
    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new SiteThread().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }
        Thread.sleep(1000);
        express.changeKm();//快递里程变化
//        express.changeSite();//快递地点变化
    }

}

package com.bruce.chen.part2.ch1.thread1.worker1;

import java.util.concurrent.TimeUnit;

public class MainClass {

    public static class ZhuangDan implements Runnable {
        public NfcAKa gun;

        public ZhuangDan(NfcAKa gun) {
            this.gun = gun;
        }

        @Override
        public void run() {
            while (true) {
                gun.zhuangdan();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public static class Sheji implements Runnable {
        public NfcAKa gun;

        public Sheji(NfcAKa gun) {
            this.gun = gun;
        }

        @Override
        public void run() {
            while (true) {
                gun.sheji();
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        NfcAKa gun = new NfcAKa();

        Thread threadZhuangdan = new Thread(new ZhuangDan(gun));
        threadZhuangdan.start();

        Thread threadSheji = new Thread(new Sheji(gun));
        threadSheji.start();

    }



}

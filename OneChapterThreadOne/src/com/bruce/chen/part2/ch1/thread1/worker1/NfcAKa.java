package com.bruce.chen.part2.ch1.thread1.worker1;

public class NfcAKa {
    /**
     * 枪内可存放20颗子弹
     */
    int magazine = 0;

    synchronized void zhuangdan() {
        try {
            while (magazine >= 20) {
                System.out.println("弹匣已满，无法装弹");
                wait();
            }
            magazine ++;
            System.out.println("弹匣未满，开始装弹，现有子弹:" + magazine);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void sheji() {
        try {
            while (magazine <= 0) {
                System.out.println("弹匣已空，无法射击");
                wait();
            }
            System.out.println("弹匣有弹，开始射击，现有子弹:" + magazine);
            magazine --;
            System.out.println("射击完还剩子弹:" + magazine);
            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

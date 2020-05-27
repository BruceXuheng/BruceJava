package com.bruce.chen.part2.ch1.thread1.worker;

public class TestMain {

    static Aka aka = new Aka(0);

    public static class ThreadConsume extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                aka.consume();
            }
        }
    }

    public static class ThreadProduce extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                aka.produce();
            }
        }
    }

    public static void main(String[] args) {
        new ThreadConsume().start();
        new ThreadProduce().start();

    }


}

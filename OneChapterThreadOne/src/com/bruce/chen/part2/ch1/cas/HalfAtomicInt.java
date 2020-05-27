package com.bruce.chen.part2.ch1.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类说明：有一个残缺A类实现了线程安全的：
 * get方法和compareAndSet()方法
 * 自行实现它的递增方法
 * <p>
 * answer：
 * 小明中了彩票享受生活去了，他遗留了一个类，是个计数器的功能，现在请你完成这个计数器。
 * 在这个类里，他已经完成了线程安全的get方法和compareAndSet()方法，请你用循环CAS机制完成它。
 * 这个类是cn.enjoyedu.ch3.answer.HalfAtomicInt
 * 完成后自行启动多个线程测试是否能正常工作。
 */
public class HalfAtomicInt {
    private AtomicInteger atomicI = new AtomicInteger(0);

    /*请完成这个递增方法*/
    public void increament() {
        //TODO
        for (; ; ) {
//            System.out.println("---");
            int i = getCount();
            boolean a = compareAndSet(i, ++i);
            if (a) {
                break;
            }
        }
    }

    public int getCount() {
        return atomicI.get();
    }

    public boolean compareAndSet(int oldValue, int newValue) {
        return atomicI.compareAndSet(oldValue, newValue);
    }

    public static void main(String[] args) {
//        HalfAtomicInt halfAtomicInt =new HalfAtomicInt();
//        for (int i = 0; i < 10; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for(int i=0;i<1000;i++){
//                        halfAtomicInt.increament();
//                    }
//                }
//            }).start();
//        }
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(halfAtomicInt.getCount());
/*
        for (;;){
            System.out.println("------------------------");
        }*/

        int i = 0;
        System.out.println(i++);
        System.out.println(++i);
        System.out.println(i);

    }


}

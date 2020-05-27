package com.bruce.chen.part3.ch10.comp;

import java.util.Random;
import java.util.concurrent.Callable;

public class WorkTask implements Callable<Integer> {

    private String name;

    public WorkTask(String name){this.name = name;}

    @Override
    public Integer call() throws Exception {
        int sleepTime = new Random().nextInt(1000);
        System.out.println("random next "+sleepTime);
        try{
            Thread.sleep(sleepTime);
        }catch (Exception e){
            e.printStackTrace();
        }

        return sleepTime;
    }
}

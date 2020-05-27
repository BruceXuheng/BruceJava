package com.bruce.chen.part2.ch2.templatepattern;

public abstract class AbstractBank {
    abstract void shape();
    abstract void apply();
    abstract void brake();

    /*模板方法*/
    public final void run(){
        this.shape();
        this.apply();
        this.brake();
    }
    /*模板方法*/
    public final void run2(){
        this.shape();
        if(this.shouldApply()){
            this.apply();
        }
        this.brake();
    }
    protected boolean shouldApply(){
        return true;
    }

}

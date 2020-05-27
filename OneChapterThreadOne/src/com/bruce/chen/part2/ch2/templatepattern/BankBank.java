package com.bruce.chen.part2.ch2.templatepattern;

public class BankBank extends AbstractBank {

    /*用于记录标志位，用于父类模板的判断*/
    private boolean flag = false;
    public void setFlag(boolean shouldApply){
        flag = shouldApply;
    }
    @Override
    protected boolean shouldApply() {
        return this.flag;
    }
    @Override
    void shape() {
        System.out.println("预存数据");
    }

    @Override
    void apply() {
        System.out.println("上送数据");
    }

    @Override
    void brake() {
        System.out.println("消费成功");
    }

}

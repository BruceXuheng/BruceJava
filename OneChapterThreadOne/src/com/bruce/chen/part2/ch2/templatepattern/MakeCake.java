package com.bruce.chen.part2.ch2.templatepattern;

public class MakeCake {

    public static void main(String[] args) {
        AbstractBank abstractBank = new BankBank();
        /*设置标志位*/
        ((BankBank) abstractBank).setFlag(false);
        abstractBank.run2();
    }

}

package com.bruce.chen.part3.ch5.dp;

public class Order {
    private final String orderNo;//订单的编号
    private final double orderMoney;//订单的金额

    public Order(String orderNo, double orderMoney) {
        super();
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getOrderMoney() {
        return orderMoney;
    }



}

package com.example.enticement.bean;


import java.util.List;

public class ItemOrderTitle {
    public String orderNum;//订单编号



   /* status 0 已经取消的订单，包含已经退款的订单
    status 1 预订单，还没有收货地址，需要确认后才能支付（此状态不存在）
    status 2 新订单，待支付
    status 3 已支付，待发货
    status 4 已发货，待完成收货
    status 5 已确认收货，订单完成*/

    public int status;//订单状态

    public ItemOrderTitle(String orderNum, int status) {
        this.orderNum = orderNum;
        this.status = status;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

package com.cuci.enticement.plate.common.eventbus;

public class OrderEvent {
    private int code;
    public static final int CANCEL_ORDER=100;

    public static final int CONFIRM_ORDER=101;
    public static final int REFRESH_OUTSIDE=102;
    public static final int FINISH_ACTIVITY=103;
    public static final int SELECT_STATUS=104;
    public static final int SET_ADDRESS=105;
    public static final int INTENT_MY_ORDER=106;
    private int cur;

    //此处是viewpager光标位
    public static final int NEED_PAY_CUR=1;//待支付
    public static final int NEED_EXPRESS_CUR=2;//待发货
    public static final int NEED_CONFIRM_CUR=3;//待收货
    public static final int HAS_FINISH_CUR=4;//已完成


    public OrderEvent(int code) {
        this.code = code;
    }


    public OrderEvent(int code,int cur) {
        this.code = code;
        this.cur=cur;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }
}


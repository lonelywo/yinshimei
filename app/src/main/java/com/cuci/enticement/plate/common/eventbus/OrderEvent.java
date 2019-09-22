package com.cuci.enticement.plate.common.eventbus;

public class OrderEvent {
    private int code;
    public static final int CANCEL_ORDER=100;
    public static final int CONFIRM_ORDER=101;

    public OrderEvent(int code) {
        this.code = code;
    }



}


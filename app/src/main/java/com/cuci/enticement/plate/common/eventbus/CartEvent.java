package com.cuci.enticement.plate.common.eventbus;

public class CartEvent {
    private int code;

    public static final int REFRESH_CART_LIST=100;
    public CartEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}


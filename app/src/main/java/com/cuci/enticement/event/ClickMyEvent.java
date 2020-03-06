package com.cuci.enticement.event;

/**
 * Created by Cloud on 2018/1/7.
 */

public class ClickMyEvent {
    private int code;

    public static final int CHECK_ITEM0=100;
    public static final int CHECK_ITEM3=101;
    public ClickMyEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

package com.cuci.enticement.event;

public class ReceiveEvent {
    private int code;

    public static final int REFRESH_LIST=100;
    public ReceiveEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}


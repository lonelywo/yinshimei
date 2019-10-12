package com.cuci.enticement.event;

public class HxEvent {
    private int code;

    public static final int REFRESH_hx_LIST=102;
    public HxEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}


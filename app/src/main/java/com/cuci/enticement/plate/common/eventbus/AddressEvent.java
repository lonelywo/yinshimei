package com.cuci.enticement.plate.common.eventbus;

public class AddressEvent {
    private int code;

    public static final int REFRESH_ADRESS_LIST=102;
    public AddressEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}


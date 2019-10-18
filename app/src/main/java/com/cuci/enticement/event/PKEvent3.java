package com.cuci.enticement.event;

public class PKEvent3 {
    private String code;



    public static final int REFRESH_hx_LIST=102;
    public PKEvent3(String code ) {
        this.code = code;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


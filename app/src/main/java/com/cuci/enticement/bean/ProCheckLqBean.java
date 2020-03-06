package com.cuci.enticement.bean;

import java.io.Serializable;

public class ProCheckLqBean implements Serializable {


    /**
     * code : 1
     * info : 领取优惠券成功
     * data : null
     */

    private int code;
    private String info;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

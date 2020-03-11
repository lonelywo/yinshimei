package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ReceiveCodeBean  {

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * code : 0
     * info : 请求有误！
     * data : {}
     */

    private int code;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String info;
}

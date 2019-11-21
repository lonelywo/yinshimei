package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class GeTuibean implements Serializable {

    /**
     * code : 1
     * info : 成功！
     * data : []
     */

    private int code;
    private String info;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}

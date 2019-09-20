package com.cuci.enticement.bean;

import java.io.Serializable;

public class CartNum implements Serializable {


    /**
     * code : 1
     * info : 查询成功！
     * data : {"c_num":1}
     */

    private int code;
    private String info;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * c_num : 1
         */

        private int c_num;

        public int getC_num() {
            return c_num;
        }

        public void setC_num(int c_num) {
            this.c_num = c_num;
        }
    }
}

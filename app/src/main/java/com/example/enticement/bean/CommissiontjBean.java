package com.example.enticement.bean;

import java.io.Serializable;

public class CommissiontjBean implements Serializable {


    /**
     * code : 1
     * info : 获取拥金统计数据成功！
     * data : {"used":0,"total":0}
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
         * used : 0
         * total : 0
         */

        private int used;
        private int total;

        public int getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}

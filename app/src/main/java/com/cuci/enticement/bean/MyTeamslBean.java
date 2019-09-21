package com.cuci.enticement.bean;

import java.io.Serializable;

public class MyTeamslBean implements Serializable {

    /**
     * code : 1
     * info : 获取团队统计
     * data : {"total_all":0,"total_mon":0}
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
         * total_all : 0
         * total_mon : 0
         */

        private int total_all;
        private int total_mon;

        public int getTotal_all() {
            return total_all;
        }

        public void setTotal_all(int total_all) {
            this.total_all = total_all;
        }

        public int getTotal_mon() {
            return total_mon;
        }

        public void setTotal_mon(int total_mon) {
            this.total_mon = total_mon;
        }
    }
}

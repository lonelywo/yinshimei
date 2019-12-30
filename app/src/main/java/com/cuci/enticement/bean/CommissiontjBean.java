package com.cuci.enticement.bean;

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

        private double used;
        private double total;
        private double lock_profit;
        private String lock_desc;

        public double getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
        }

        public double getTotal() {
            return total;
        }
        public void setTotal(int total) {
            this.total = total;
        }

        public double getLock_profit() {
            return lock_profit;
        }

        public void setLock_profit(double lock_profit) {
            this.lock_profit = lock_profit;
        }
        public String getLock_desc() {
            return lock_desc;
        }

        public void setLock_desc(String lock_desc) {
            this.lock_desc = lock_desc;
        }
    }

}

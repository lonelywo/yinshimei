package com.cuci.enticement.bean;

import java.io.Serializable;

public class JiFenDuiHuanSubmitBean implements Serializable {

    /**
     * code : 1
     * info : ok
     * data : {"mid":60000000,"order_no":123123123,"status":3,"points":1000}
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
         * mid : 60000000
         * order_no : 123123123
         * status : 3
         * points : 1000
         */

        private int mid;
        private int order_no;
        private int status;
        private int points;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getOrder_no() {
            return order_no;
        }

        public void setOrder_no(int order_no) {
            this.order_no = order_no;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }
}

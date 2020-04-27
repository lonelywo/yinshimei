package com.cuci.enticement.bean;

import java.io.Serializable;

public class JiFenDuiHuanSubmitBean implements Serializable {


    /**
     * code : 1
     * info : ok
     * data : {"mid":"52202","order_no":"2268799139659","status":3,"points":400}
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

    public static class DataBean implements Serializable{
        /**
         * mid : 52202
         * order_no : 2268799139659
         * status : 3
         * points : 400
         */

        private String mid;
        private String order_no;
        private int status;
        private int points;

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
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

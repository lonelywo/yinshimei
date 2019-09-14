package com.example.enticement.bean;

import java.util.List;

public class OrderStatistics {


    /**
     * code : 1
     * info : 获取订单统计记录！
     * data : [{"mid":1,"status":0,"count":3},{"mid":1,"status":1,"count":1},{"mid":1,"status":3,"count":1}]
     */

    private int code;
    private String info;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mid : 1
         * status : 0
         * count : 3
         */

        private int mid;
        private int status;
        private int count;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}

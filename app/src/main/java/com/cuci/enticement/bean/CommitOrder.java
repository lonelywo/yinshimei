package com.cuci.enticement.bean;

import java.io.Serializable;

public class CommitOrder implements Serializable {


    /**
     * code : 1
     * info : 订单创建成功
     * data : {"order":{"order_no":"675282065182","aid":"","mid":"21045","type":"1","status":"1","price_express":0,"from_mid":"0"}}
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
         * order : {"order_no":"675282065182","aid":"","mid":"21045","type":"1","status":"1","price_express":0,"from_mid":"0"}
         */

        private OrderBean order;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * order_no : 675282065182
             * aid :
             * mid : 21045
             * type : 1
             * status : 1
             * price_express : 0
             * from_mid : 0
             */

            private String order_no;
            private String aid;
            private String mid;
            private String type;
            private String status;
            private int price_express;
            private String from_mid;

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getPrice_express() {
                return price_express;
            }

            public void setPrice_express(int price_express) {
                this.price_express = price_express;
            }

            public String getFrom_mid() {
                return from_mid;
            }

            public void setFrom_mid(String from_mid) {
                this.from_mid = from_mid;
            }
        }
    }
}

package com.example.enticement.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 预定单返回
 */
public class OrderResult implements Serializable {


    /**
     * code : 1
     * info : 订单创建成功，请补全收货信息后支付！
     * data : {"order":{"order_no":"668963428099","mid":"18281","type":"1","status":"1","from_mid":""}}
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
         * order : {"order_no":"668963428099","mid":"18281","type":"1","status":"1","from_mid":""}
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
             * order_no : 668963428099
             * mid : 18281
             * type : 1
             * status : 1
             * from_mid :
             */

            private String order_no;
            private String mid;
            private String type;
            private String status;
            private String from_mid;

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
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

            public String getFrom_mid() {
                return from_mid;
            }

            public void setFrom_mid(String from_mid) {
                this.from_mid = from_mid;
            }
        }
    }
}

package com.example.enticement.bean;

import java.io.Serializable;

public class YeJiYueFanBean implements Serializable {


    /**
     * code : 1
     * info : 查询成功
     * data : {"amount":"","gift_name":"","status":"","explain":"<p>\r\n规则说明：<\/p>\r\n\r\n<p>\r\n经销商补货达到指定的业绩后，公司按月返零售价产品给到经销商，系统自动核算。以月为单位，到期清零不累计。<\/p>\r\n\r\n<p>\r\n5000 返 价值600元产品<\/p>\r\n\r\n<p>\r\n8000 返 价值1200元产品<\/p>\r\n\r\n<p>\r\n10000 返 价值1500元产品<\/p>\r\n\r\n<p>\r\n15000 返 价值2400元产品<\/p>\r\n\r\n<p>\r\n20000 返 价值3400元产品<\/p>\r\n"}
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
         * amount :
         * gift_name :
         * status :
         * explain : <p>
         规则说明：</p>

         <p>
         经销商补货达到指定的业绩后，公司按月返零售价产品给到经销商，系统自动核算。以月为单位，到期清零不累计。</p>

         <p>
         5000 返 价值600元产品</p>

         <p>
         8000 返 价值1200元产品</p>

         <p>
         10000 返 价值1500元产品</p>

         <p>
         15000 返 价值2400元产品</p>

         <p>
         20000 返 价值3400元产品</p>

         */

        private String amount;
        private String gift_name;
        private String status;
        private String explain;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getGift_name() {
            return gift_name;
        }

        public void setGift_name(String gift_name) {
            this.gift_name = gift_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }
    }
}

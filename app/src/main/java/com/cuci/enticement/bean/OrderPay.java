package com.cuci.enticement.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderPay implements Serializable {


    /**
     * code : 1
     * info : 获取订单支付参数成功！
     * data : {"appid":"wxb15f89269aa2e2bd","partnerid":"1552252891","prepayid":"wx2216321033640925b4e468011342758300","package":"Sign=WXPay","timestamp":"1569141130","noncestr":"kqx0ns83conlfos2pczfo6gyajxlfuy6","sign":"880B1948C3CA01FD984AE92F20D362FE"}
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
         * appid : wxb15f89269aa2e2bd
         * partnerid : 1552252891
         * prepayid : wx2216321033640925b4e468011342758300
         * package : Sign=WXPay
         * timestamp : 1569141130
         * noncestr : kqx0ns83conlfos2pczfo6gyajxlfuy6
         * sign : 880B1948C3CA01FD984AE92F20D362FE
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String timestamp;
        private String noncestr;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}

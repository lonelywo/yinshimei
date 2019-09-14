package com.example.enticement.bean;

import com.google.gson.annotations.SerializedName;

public class OrderPay {


    /**
     * code : 1
     * info : 获取订单支付参数成功！
     * data : {"appId":"wx6bb7b70258da09c6","timeStamp":"1548153280","nonceStr":"elixaugruy8ww4ijo8y1x6epscbefqeh","package":"prepay_id=wx22183440132211464af955411809028561","signType":"MD5","paySign":"5AC755D31D0FE2E0D16B96FBFA70EE6A","timestamp":"1548153280"}
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
         * appId : wx6bb7b70258da09c6
         * timeStamp : 1548153280
         * nonceStr : elixaugruy8ww4ijo8y1x6epscbefqeh
         * package : prepay_id=wx22183440132211464af955411809028561
         * signType : MD5
         * paySign : 5AC755D31D0FE2E0D16B96FBFA70EE6A
         * timestamp : 1548153280
         */

        private String appId;
        private String timeStamp;
        private String nonceStr;
        @SerializedName("package")
        private String packageX;
        private String signType;
        private String paySign;
        private String timestamp;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}

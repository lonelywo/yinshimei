package com.cuci.enticement.bean;


import java.io.Serializable;

public class ShareimgBean implements Serializable {


    /**
     * code : 1
     * info : 生成成功！
     * data : {"mid":40,"goods_id":"6732230775","qrcode":"https://qiniu.cdn.enticementchina.com/9e6280853cd5d1ae/88991adbf3d77aa4.jpg","poster":"https://qiniu.cdn.enticementchina.com/0636ac2f0ab258b9/a0ed16e7dccaf4e7.jpg"}
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
         * mid : 40
         * goods_id : 6732230775
         * qrcode : https://qiniu.cdn.enticementchina.com/9e6280853cd5d1ae/88991adbf3d77aa4.jpg
         * poster : https://qiniu.cdn.enticementchina.com/0636ac2f0ab258b9/a0ed16e7dccaf4e7.jpg
         */

        private int mid;
        private String goods_id;
        private String qrcode;
        private String poster;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }
    }
}

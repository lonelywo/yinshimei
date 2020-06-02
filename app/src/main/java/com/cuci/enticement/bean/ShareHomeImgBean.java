package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class ShareHomeImgBean implements Serializable {

    /**
     * code : 1
     * info : 生成成功！
     * data : {"mid":"52202","nickname":"皮蛋君","goods_list":[{"goods_id":"6856280800","qrcode":"https://qiniu.cdn.enticementchina.com/cf3cd146e123931c/8efe8b79281f7b0a.jpg","poster":"https://qiniu.cdn.enticementchina.com/5239cf0ea825961b/604d071bbaa35602.jpg"}]}
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
         * nickname : 皮蛋君
         * goods_list : [{"goods_id":"6856280800","qrcode":"https://qiniu.cdn.enticementchina.com/cf3cd146e123931c/8efe8b79281f7b0a.jpg","poster":"https://qiniu.cdn.enticementchina.com/5239cf0ea825961b/604d071bbaa35602.jpg"}]
         */

        private String mid;
        private String nickname;

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        private String slogan;
        private List<GoodsListBean> goods_list;

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean implements Serializable{
            /**
             * goods_id : 6856280800
             * qrcode : https://qiniu.cdn.enticementchina.com/cf3cd146e123931c/8efe8b79281f7b0a.jpg
             * poster : https://qiniu.cdn.enticementchina.com/5239cf0ea825961b/604d071bbaa35602.jpg
             */

            private String goods_id;
            private String qrcode;
            private String poster;

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
}

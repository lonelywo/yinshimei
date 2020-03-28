package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class ZhuanPanBean implements Serializable {

    /**
     * code : 1
     * info : ok
     * data : {"is_lottery":0,"lottery":[{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"},{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"},{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"},{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"}],"is_coupon":0,"coupons":[],"img":"https://qiniu.cdn.enticementchina.com/baoguo@2x.png","desc":"派送员将会以最快的速度将包裹送到您手中\\n请美粉耐心等候哟~"}
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
         * is_lottery : 0
         * lottery : [{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"},{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"},{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"},{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"}]
         * is_coupon : 0
         * coupons : []
         * img : https://qiniu.cdn.enticementchina.com/baoguo@2x.png
         * desc : 派送员将会以最快的速度将包裹送到您手中\n请美粉耐心等候哟~
         */

        private int is_lottery;
        private int is_coupon;
        private String img;
        private String desc;
        private List<LotteryBean> lottery;
        private List<?> coupons;

        public int getIs_lottery() {
            return is_lottery;
        }

        public void setIs_lottery(int is_lottery) {
            this.is_lottery = is_lottery;
        }

        public int getIs_coupon() {
            return is_coupon;
        }

        public void setIs_coupon(int is_coupon) {
            this.is_coupon = is_coupon;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<LotteryBean> getLottery() {
            return lottery;
        }

        public void setLottery(List<LotteryBean> lottery) {
            this.lottery = lottery;
        }

        public List<?> getCoupons() {
            return coupons;
        }

        public void setCoupons(List<?> coupons) {
            this.coupons = coupons;
        }

        public static class LotteryBean {
            /**
             * title : 5元优惠劵1000
             * img : https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png
             */

            private String title;
            private String img;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}

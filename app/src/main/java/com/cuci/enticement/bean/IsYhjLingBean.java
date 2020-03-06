package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class IsYhjLingBean implements Serializable {


    /**
     * code : 1
     * info : ok
     * data : {"is_coupon":1,"type":-1,"coupons":[{"title":"新人专享券","limit_amount":20,"amount":150,"startstime":"2020-03-01 00:00:00","endtime":"2020-03-31 00:00:00"},{"title":"新人专享券-2","limit_amount":10,"amount":130,"startstime":"2020-03-01 00:00:00","endtime":"2020-03-31 00:00:00"}],"coupons_text":{"title":"新人大礼包","desc":"恭喜您，注册可获得新人大礼包","button":"注册"}}
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
         * is_coupon : 1
         * type : -1
         * coupons : [{"title":"新人专享券","limit_amount":20,"amount":150,"startstime":"2020-03-01 00:00:00","endtime":"2020-03-31 00:00:00"},{"title":"新人专享券-2","limit_amount":10,"amount":130,"startstime":"2020-03-01 00:00:00","endtime":"2020-03-31 00:00:00"}]
         * coupons_text : {"title":"新人大礼包","desc":"恭喜您，注册可获得新人大礼包","button":"注册"}
         */

        private int is_coupon;
        private int type;
        private CouponsTextBean coupons_text;
        private List<CouponsBean> coupons;

        public int getIs_coupon() {
            return is_coupon;
        }

        public void setIs_coupon(int is_coupon) {
            this.is_coupon = is_coupon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public CouponsTextBean getCoupons_text() {
            return coupons_text;
        }

        public void setCoupons_text(CouponsTextBean coupons_text) {
            this.coupons_text = coupons_text;
        }

        public List<CouponsBean> getCoupons() {
            return coupons;
        }

        public void setCoupons(List<CouponsBean> coupons) {
            this.coupons = coupons;
        }

        public static class CouponsTextBean {
            /**
             * title : 新人大礼包
             * desc : 恭喜您，注册可获得新人大礼包
             * button : 注册
             */

            private String title;
            private String desc;
            private String button;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getButton() {
                return button;
            }

            public void setButton(String button) {
                this.button = button;
            }
        }

        public static class CouponsBean {
            /**
             * title : 新人专享券
             * limit_amount : 20.0
             * amount : 150.0
             * startstime : 2020-03-01 00:00:00
             * endtime : 2020-03-31 00:00:00
             */

            private String title;
            private String limit_amount;
            private String amount;
            private String startstime;
            private String endtime;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLimit_amount() {
                return limit_amount;
            }

            public void setLimit_amount(String limit_amount) {
                this.limit_amount = limit_amount;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getStartstime() {
                return startstime;
            }

            public void setStartstime(String startstime) {
                this.startstime = startstime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }
        }
    }
}

package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class ProYhqBean implements Serializable {


    /**
     * code : 1
     * info : ok
     * data : [{"id":1,"title":"女神节惊喜券","limit_amount":120,"amount":20,"is_receive":1,"startstime":"2020-03-07 00:00:00","endtime":"2020-03-08 23:59:59","date_range":"03-07 00:00 至 03-08 23:59","discount":"满120.00减20.00","rule_desc":"优惠券同时使用规则：\\n1、任何一种京券和东券都不能叠加使用。"},{"id":2,"title":"美粉礼券","limit_amount":120,"amount":5,"is_receive":0,"startstime":"2020-03-04 11:10:42","endtime":"2020-03-05 11:10:42","date_range":"03-04 11:10 至 03-05 11:10","discount":"满120.00减5.00","rule_desc":"优惠券同时使用规则：\\n1、任何一种京券和东券都不能叠加使用。"},{"id":3,"title":"下单后赠礼","limit_amount":60,"amount":3,"is_receive":1,"startstime":"2020-03-03 16:16:30","endtime":"2020-03-05 16:16:30","date_range":"03-03 16:16 至 03-05 16:16","discount":"满60.00减3.00","rule_desc":"优惠券同时使用规则：\\n1、任何一种京券和东券都不能叠加使用。"}]
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
         * id : 1
         * title : 女神节惊喜券
         * limit_amount : 120.0
         * amount : 20.0
         * is_receive : 1
         * startstime : 2020-03-07 00:00:00
         * endtime : 2020-03-08 23:59:59
         * date_range : 03-07 00:00 至 03-08 23:59
         * discount : 满120.00减20.00
         * rule_desc : 优惠券同时使用规则：\n1、任何一种京券和东券都不能叠加使用。
         */

        private int id;
        private String title;
        private double limit_amount;
        private double amount;
        private int is_receive;
        private String startstime;
        private String endtime;
        private String date_range;
        private String discount;
        private String rule_desc;

        public Boolean getIscheck() {
            return ischeck;
        }

        public void setIscheck(Boolean ischeck) {
            this.ischeck = ischeck;
        }

        private Boolean ischeck=false;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getLimit_amount() {
            return limit_amount;
        }

        public void setLimit_amount(double limit_amount) {
            this.limit_amount = limit_amount;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getIs_receive() {
            return is_receive;
        }

        public void setIs_receive(int is_receive) {
            this.is_receive = is_receive;
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

        public String getDate_range() {
            return date_range;
        }

        public void setDate_range(String date_range) {
            this.date_range = date_range;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getRule_desc() {
            return rule_desc;
        }

        public void setRule_desc(String rule_desc) {
            this.rule_desc = rule_desc;
        }
    }
}

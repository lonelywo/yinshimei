package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class PayOfterBean implements Serializable {


    /**
     * code : 1
     * info : 获取优惠券列表成功
     * data : {"is_lottery":1,"lottery":{"nums":3,"rules":[{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"}],"m_lottery_id":1,"ruleDescription":"http://web.enticementchina.com/appweb/rule_lottery.html"},"is_coupon":1,"coupons":[{"title":"购物赠送券","limit_amount":100,"amount":5,"startstime":"2020-02-26 18:44:30","endtime":"2020-02-26 20:44:30"},{"title":"购物赠送券-2","limit_amount":200,"amount":15,"startstime":"2020-02-26 18:44:30","endtime":"2020-02-26 20:44:30"}],"img":"https://lanhu.oss-cn-beijing.aliyuncs.com/pscd3cf5fd4096be0f-40ba-4272-bab1-85c0e84fe916","desc":"恭喜您获得优惠券2张 请移步到我的卡券中心查看"}
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
         * is_lottery : 1
         * lottery : {"nums":3,"rules":[{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"}],"m_lottery_id":1,"ruleDescription":"http://web.enticementchina.com/appweb/rule_lottery.html"}
         * is_coupon : 1
         * coupons : [{"title":"购物赠送券","limit_amount":100,"amount":5,"startstime":"2020-02-26 18:44:30","endtime":"2020-02-26 20:44:30"},{"title":"购物赠送券-2","limit_amount":200,"amount":15,"startstime":"2020-02-26 18:44:30","endtime":"2020-02-26 20:44:30"}]
         * img : https://lanhu.oss-cn-beijing.aliyuncs.com/pscd3cf5fd4096be0f-40ba-4272-bab1-85c0e84fe916
         * desc : 恭喜您获得优惠券2张 请移步到我的卡券中心查看
         */

        private int is_lottery;
        private LotteryBean lottery;
        private int is_coupon;
        private String img;
        private String desc;
        private List<CouponsBean> coupons;

        public int getIs_lottery() {
            return is_lottery;
        }

        public void setIs_lottery(int is_lottery) {
            this.is_lottery = is_lottery;
        }

        public LotteryBean getLottery() {
            return lottery;
        }

        public void setLottery(LotteryBean lottery) {
            this.lottery = lottery;
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

        public List<CouponsBean> getCoupons() {
            return coupons;
        }

        public void setCoupons(List<CouponsBean> coupons) {
            this.coupons = coupons;
        }

        public static class LotteryBean {
            /**
             * nums : 3
             * rules : [{"title":"5元优惠劵1000","img":"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png"},{"title":"化妆棉","img":"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png"}]
             * m_lottery_id : 1
             * ruleDescription : http://web.enticementchina.com/appweb/rule_lottery.html
             */

            private int nums;
            private int m_lottery_id;
            private String ruleDescription;
            private List<RulesBean> rules;

            public int getNums() {
                return nums;
            }

            public void setNums(int nums) {
                this.nums = nums;
            }

            public int getM_lottery_id() {
                return m_lottery_id;
            }

            public void setM_lottery_id(int m_lottery_id) {
                this.m_lottery_id = m_lottery_id;
            }

            public String getRuleDescription() {
                return ruleDescription;
            }

            public void setRuleDescription(String ruleDescription) {
                this.ruleDescription = ruleDescription;
            }

            public List<RulesBean> getRules() {
                return rules;
            }

            public void setRules(List<RulesBean> rules) {
                this.rules = rules;
            }

            public static class RulesBean {
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

        public static class CouponsBean {
            /**
             * title : 购物赠送券
             * limit_amount : 100
             * amount : 5
             * startstime : 2020-02-26 18:44:30
             * endtime : 2020-02-26 20:44:30
             */

            private String title;
            private int limit_amount;
            private int amount;
            private String startstime;
            private String endtime;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getLimit_amount() {
                return limit_amount;
            }

            public void setLimit_amount(int limit_amount) {
                this.limit_amount = limit_amount;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
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

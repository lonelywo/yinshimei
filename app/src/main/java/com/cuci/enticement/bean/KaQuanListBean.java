package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class KaQuanListBean implements Parcelable {


    /**
     * code : 1
     * info : ok
     * data : {"page":{"limit":"10","total":2,"pages":1,"current":1},"list":[{"id":8,"startstime":"2020-03-01 00:00:00","endtime":"2020-03-08 23:59:59","used_at":"0000-00-00 00:00:00","create_at":"2020-02-28 22:30:45","date_range":"03-01 00:00 至 03-08 23:59","coupon":{"title":"女神节惊喜券","limit_amount":"120.00","amount":"20.00","use_goods":"","amount_desc":"满120减20","rule_desc":"优惠券同时使用规则：\\n1、任何一种京券和东券都不能叠加使用。运费券可与京券和东券叠加使用。\\n 2、京券使用规则：\\n 2.1 同一订单下可以同时使用多张全品类京券，并可与限品类京券、店铺京券、运费券同时使用，但不能与东券叠加使用。\\n 2.2 使用限品类京券提交订单时，订单中的商品必须满足品类限制，当限品类京券使用的商品范围完全一致，或使用的商品范围完全不一致的情况下，则可以在同一订单下同时使用多张限品类京券。\\n"}},{"id":13,"startstime":"2020-03-01 00:00:00","endtime":"2020-03-08 23:59:59","used_at":"0000-00-00 00:00:00","create_at":"2020-02-28 22:30:45","date_range":"03-01 00:00 至 03-08 23:59","coupon":{"title":"立减券","limit_amount":"0.00","amount":"10.00","use_goods":"6713072951","amount_desc":"立减券","rule_desc":"优惠券同时使用规则：\\n1、任何一种京券和东券都不能叠加使用。运费券可与京券和东券叠加使用。\\n 2、京券使用规则：\\n 2.1 同一订单下可以同时使用多张全品类京券，并可与限品类京券、店铺京券、运费券同时使用，但不能与东券叠加使用。\\n 2.2 使用限品类京券提交订单时，订单中的商品必须满足品类限制，当限品类京券使用的商品范围完全一致，或使用的商品范围完全不一致的情况下，则可以在同一订单下同时使用多张限品类京券。\\n"}}],"count":{"no_used":2,"used":0,"expire":3}}
     */

    private int code;
    private String info;
    private DataBean data;

    protected KaQuanListBean(Parcel in) {
        code = in.readInt();
        info = in.readString();
    }

    public static final Creator<KaQuanListBean> CREATOR = new Creator<KaQuanListBean>() {
        @Override
        public KaQuanListBean createFromParcel(Parcel in) {
            return new KaQuanListBean(in);
        }

        @Override
        public KaQuanListBean[] newArray(int size) {
            return new KaQuanListBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(info);
    }

    public static class DataBean {
        /**
         * page : {"limit":"10","total":2,"pages":1,"current":1}
         * list : [{"id":8,"startstime":"2020-03-01 00:00:00","endtime":"2020-03-08 23:59:59","used_at":"0000-00-00 00:00:00","create_at":"2020-02-28 22:30:45","date_range":"03-01 00:00 至 03-08 23:59","coupon":{"title":"女神节惊喜券","limit_amount":"120.00","amount":"20.00","use_goods":"","amount_desc":"满120减20","rule_desc":"优惠券同时使用规则：\\n1、任何一种京券和东券都不能叠加使用。运费券可与京券和东券叠加使用。\\n 2、京券使用规则：\\n 2.1 同一订单下可以同时使用多张全品类京券，并可与限品类京券、店铺京券、运费券同时使用，但不能与东券叠加使用。\\n 2.2 使用限品类京券提交订单时，订单中的商品必须满足品类限制，当限品类京券使用的商品范围完全一致，或使用的商品范围完全不一致的情况下，则可以在同一订单下同时使用多张限品类京券。\\n"}},{"id":13,"startstime":"2020-03-01 00:00:00","endtime":"2020-03-08 23:59:59","used_at":"0000-00-00 00:00:00","create_at":"2020-02-28 22:30:45","date_range":"03-01 00:00 至 03-08 23:59","coupon":{"title":"立减券","limit_amount":"0.00","amount":"10.00","use_goods":"6713072951","amount_desc":"立减券","rule_desc":"优惠券同时使用规则：\\n1、任何一种京券和东券都不能叠加使用。运费券可与京券和东券叠加使用。\\n 2、京券使用规则：\\n 2.1 同一订单下可以同时使用多张全品类京券，并可与限品类京券、店铺京券、运费券同时使用，但不能与东券叠加使用。\\n 2.2 使用限品类京券提交订单时，订单中的商品必须满足品类限制，当限品类京券使用的商品范围完全一致，或使用的商品范围完全不一致的情况下，则可以在同一订单下同时使用多张限品类京券。\\n"}}]
         * count : {"no_used":2,"used":0,"expire":3}
         */

        private PageBean page;
        private CountBean count;
        private List<ListBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public CountBean getCount() {
            return count;
        }

        public void setCount(CountBean count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageBean {
            /**
             * limit : 10
             * total : 2
             * pages : 1
             * current : 1
             */

            private String limit;
            private int total;
            private int pages;
            private int current;

            public String getLimit() {
                return limit;
            }

            public void setLimit(String limit) {
                this.limit = limit;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getCurrent() {
                return current;
            }

            public void setCurrent(int current) {
                this.current = current;
            }
        }

        public static class CountBean {
            /**
             * no_used : 2
             * used : 0
             * expire : 3
             */

            private int no_used;
            private int used;
            private int expire;

            public int getNo_used() {
                return no_used;
            }

            public void setNo_used(int no_used) {
                this.no_used = no_used;
            }

            public int getUsed() {
                return used;
            }

            public void setUsed(int used) {
                this.used = used;
            }

            public int getExpire() {
                return expire;
            }

            public void setExpire(int expire) {
                this.expire = expire;
            }
        }

        public static class ListBean {
            /**
             * id : 8
             * startstime : 2020-03-01 00:00:00
             * endtime : 2020-03-08 23:59:59
             * used_at : 0000-00-00 00:00:00
             * create_at : 2020-02-28 22:30:45
             * date_range : 03-01 00:00 至 03-08 23:59
             * coupon : {"title":"女神节惊喜券","limit_amount":"120.00","amount":"20.00","use_goods":"","amount_desc":"满120减20","rule_desc":"优惠券同时使用规则：\\n1、任何一种京券和东券都不能叠加使用。运费券可与京券和东券叠加使用。\\n 2、京券使用规则：\\n 2.1 同一订单下可以同时使用多张全品类京券，并可与限品类京券、店铺京券、运费券同时使用，但不能与东券叠加使用。\\n 2.2 使用限品类京券提交订单时，订单中的商品必须满足品类限制，当限品类京券使用的商品范围完全一致，或使用的商品范围完全不一致的情况下，则可以在同一订单下同时使用多张限品类京券。\\n"}
             */

            private String id;
            private String startstime;
            private String endtime;
            private String used_at;
            private String create_at;
            private String date_range;
            private CouponBean coupon;

            public boolean isIsshow() {
                return isshow;
            }

            public void setIsshow(boolean isshow) {
                this.isshow = isshow;
            }
           //显示按钮
            private boolean isshow=false;
            public Boolean getIscheck() {
                return ischeck;
            }

            public void setIscheck(Boolean ischeck) {
                this.ischeck = ischeck;
            }

            private Boolean ischeck=false;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getUsed_at() {
                return used_at;
            }

            public void setUsed_at(String used_at) {
                this.used_at = used_at;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getDate_range() {
                return date_range;
            }

            public void setDate_range(String date_range) {
                this.date_range = date_range;
            }

            public CouponBean getCoupon() {
                return coupon;
            }

            public void setCoupon(CouponBean coupon) {
                this.coupon = coupon;
            }

            public static class CouponBean {
                /**
                 * title : 女神节惊喜券
                 * limit_amount : 120.00
                 * amount : 20.00
                 * use_goods :
                 * amount_desc : 满120减20
                 * rule_desc : 优惠券同时使用规则：\n1、任何一种京券和东券都不能叠加使用。运费券可与京券和东券叠加使用。\n 2、京券使用规则：\n 2.1 同一订单下可以同时使用多张全品类京券，并可与限品类京券、店铺京券、运费券同时使用，但不能与东券叠加使用。\n 2.2 使用限品类京券提交订单时，订单中的商品必须满足品类限制，当限品类京券使用的商品范围完全一致，或使用的商品范围完全不一致的情况下，则可以在同一订单下同时使用多张限品类京券。\n
                 */

                private String title;
                private String limit_amount;
                private String amount;
                private String use_goods;
                private String amount_desc;
                private String rule_desc;

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

                public String getUse_goods() {
                    return use_goods;
                }

                public void setUse_goods(String use_goods) {
                    this.use_goods = use_goods;
                }

                public String getAmount_desc() {
                    return amount_desc;
                }

                public void setAmount_desc(String amount_desc) {
                    this.amount_desc = amount_desc;
                }

                public String getRule_desc() {
                    return rule_desc;
                }

                public void setRule_desc(String rule_desc) {
                    this.rule_desc = rule_desc;
                }
            }
        }
    }
}

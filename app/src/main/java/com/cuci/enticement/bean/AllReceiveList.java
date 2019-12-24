package com.cuci.enticement.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AllReceiveList  implements Parcelable {


    /**
     * code : 1
     * info : ok
     * data : {"list":[{"create_at":"2019-12-23 17:34:04","express_company_code":"#@@#","express_company_name":"","price_goods":0,"pay_price":0,"list":[{"aid":36,"price_selling":"0.00","number":1,"goods_title":"49.9活动领取-原生水","goods_id":6765798002,"goods_logo":"http://qiniu.cdn.enticementchina.com/b2df297b033e86b7/080201611ed0959e.jpg","order_no":67709364487},{"aid":22403,"price_selling":"0.00","number":1,"goods_title":"49.9活动领取-洁面乳","goods_id":6765799319,"goods_logo":"http://qiniu.cdn.enticementchina.com/9920fd48c8ae11fd/5727e88d2fafce4c.jpg","order_no":67709364487}]}],"page":{"pages":1,"current":1,"total":1,"limit":20}}
     */

    private int code;
    private String info;
    private DataBean data;

    protected AllReceiveList(Parcel in) {
        code = in.readInt();
        info = in.readString();
    }

    public static final Creator<AllReceiveList> CREATOR = new Creator<AllReceiveList>() {
        @Override
        public AllReceiveList createFromParcel(Parcel in) {
            return new AllReceiveList(in);
        }

        @Override
        public AllReceiveList[] newArray(int size) {
            return new AllReceiveList[size];
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

    public static class DataBean implements Parcelable{
        /**
         * list : [{"create_at":"2019-12-23 17:34:04","express_company_code":"#@@#","express_company_name":"","price_goods":0,"pay_price":0,"list":[{"aid":36,"price_selling":"0.00","number":1,"goods_title":"49.9活动领取-原生水","goods_id":6765798002,"goods_logo":"http://qiniu.cdn.enticementchina.com/b2df297b033e86b7/080201611ed0959e.jpg","order_no":67709364487},{"aid":22403,"price_selling":"0.00","number":1,"goods_title":"49.9活动领取-洁面乳","goods_id":6765799319,"goods_logo":"http://qiniu.cdn.enticementchina.com/9920fd48c8ae11fd/5727e88d2fafce4c.jpg","order_no":67709364487}]}]
         * page : {"pages":1,"current":1,"total":1,"limit":20}
         */

        private PageBean page;
        private List<ListBeanX> list;

        protected DataBean(Parcel in) {
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }

        public static class PageBean implements Parcelable{
            /**
             * pages : 1
             * current : 1
             * total : 1
             * limit : 20
             */

            private int pages;
            private int current;
            private int total;
            private int limit;

            protected PageBean(Parcel in) {
                pages = in.readInt();
                current = in.readInt();
                total = in.readInt();
                limit = in.readInt();
            }

            public static final Creator<PageBean> CREATOR = new Creator<PageBean>() {
                @Override
                public PageBean createFromParcel(Parcel in) {
                    return new PageBean(in);
                }

                @Override
                public PageBean[] newArray(int size) {
                    return new PageBean[size];
                }
            };

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

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(pages);
                parcel.writeInt(current);
                parcel.writeInt(total);
                parcel.writeInt(limit);
            }
        }

        public static class ListBeanX implements Parcelable{
            /**
             * create_at : 2019-12-23 17:34:04
             * express_company_code : #@@#
             * express_company_name :
             * price_goods : 0
             * pay_price : 0
             * list : [{"aid":36,"price_selling":"0.00","number":1,"goods_title":"49.9活动领取-原生水","goods_id":6765798002,"goods_logo":"http://qiniu.cdn.enticementchina.com/b2df297b033e86b7/080201611ed0959e.jpg","order_no":67709364487},{"aid":22403,"price_selling":"0.00","number":1,"goods_title":"49.9活动领取-洁面乳","goods_id":6765799319,"goods_logo":"http://qiniu.cdn.enticementchina.com/9920fd48c8ae11fd/5727e88d2fafce4c.jpg","order_no":67709364487}]
             */

            private String create_at;
            private String express_company_code;
            private String express_company_name;

            public String getExpress_send_no() {
                return express_send_no;
            }

            public void setExpress_send_no(String express_send_no) {
                this.express_send_no = express_send_no;
            }

            private String express_send_no;
            private int price_goods;
            private int pay_price;
            private List<ReceiveGoods> list;

            protected ListBeanX(Parcel in) {
                create_at = in.readString();
                express_company_code = in.readString();
                express_company_name = in.readString();
                express_send_no = in.readString();
                price_goods = in.readInt();
                pay_price = in.readInt();
            }

            public static final Creator<ListBeanX> CREATOR = new Creator<ListBeanX>() {
                @Override
                public ListBeanX createFromParcel(Parcel in) {
                    return new ListBeanX(in);
                }

                @Override
                public ListBeanX[] newArray(int size) {
                    return new ListBeanX[size];
                }
            };

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getExpress_company_code() {
                return express_company_code;
            }

            public void setExpress_company_code(String express_company_code) {
                this.express_company_code = express_company_code;
            }

            public String getExpress_company_name() {
                return express_company_name;
            }

            public void setExpress_company_name(String express_company_name) {
                this.express_company_name = express_company_name;
            }

            public int getPrice_goods() {
                return price_goods;
            }

            public void setPrice_goods(int price_goods) {
                this.price_goods = price_goods;
            }

            public int getPay_price() {
                return pay_price;
            }

            public void setPay_price(int pay_price) {
                this.pay_price = pay_price;
            }

            public List<ReceiveGoods> getList() {
                return list;
            }

            public void setList(List<ReceiveGoods> list) {
                this.list = list;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(create_at);
                parcel.writeString(express_company_code);
                parcel.writeString(express_company_name);
                parcel.writeString(express_send_no);
                parcel.writeInt(price_goods);
                parcel.writeInt(pay_price);
            }
        }
    }
}

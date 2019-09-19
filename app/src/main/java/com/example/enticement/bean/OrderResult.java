package com.example.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OrderResult {

    /**
     * code : 1
     * info : 订单创建成功，请补全收货信息后支付！
     * data : {"order":{"order_no":"656448749498","mid":"37","type":"1","status":"1","from_mid":"0","price_goods":2100,"price_express":0,"price_total":2100}}
     */

    private int code;
    private String info;
    @SerializedName("datatest")
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
         * order : {"order_no":"656448749498","mid":"37","type":"1","status":"1","from_mid":"0","price_goods":2100,"price_express":0,"price_total":2100}
         */

        private OrderBean order;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class OrderBean implements Parcelable {
            /**
             * order_no : 656448749498
             * mid : 37
             * type : 1
             * status : 1
             * from_mid : 0
             * price_goods : 2100
             * price_express : 0
             * price_total : 2100
             */

            private String order_no;
            private String mid;
            private String type;
            private String status;
            private String from_mid;
            private int price_goods;
            private int price_express;
            private int price_total;

            protected OrderBean(Parcel in) {
                order_no = in.readString();
                mid = in.readString();
                type = in.readString();
                status = in.readString();
                from_mid = in.readString();
                price_goods = in.readInt();
                price_express = in.readInt();
                price_total = in.readInt();
            }

            public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
                @Override
                public OrderBean createFromParcel(Parcel in) {
                    return new OrderBean(in);
                }

                @Override
                public OrderBean[] newArray(int size) {
                    return new OrderBean[size];
                }
            };

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getFrom_mid() {
                return from_mid;
            }

            public void setFrom_mid(String from_mid) {
                this.from_mid = from_mid;
            }

            public int getPrice_goods() {
                return price_goods;
            }

            public void setPrice_goods(int price_goods) {
                this.price_goods = price_goods;
            }

            public int getPrice_express() {
                return price_express;
            }

            public void setPrice_express(int price_express) {
                this.price_express = price_express;
            }

            public int getPrice_total() {
                return price_total;
            }

            public void setPrice_total(int price_total) {
                this.price_total = price_total;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(order_no);
                parcel.writeString(mid);
                parcel.writeString(type);
                parcel.writeString(status);
                parcel.writeString(from_mid);
                parcel.writeInt(price_goods);
                parcel.writeInt(price_express);
                parcel.writeInt(price_total);
            }
        }
    }
}

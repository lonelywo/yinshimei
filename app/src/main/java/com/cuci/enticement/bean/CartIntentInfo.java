package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CartIntentInfo implements Parcelable {
    private String orderNo;



    private int count;
    private double totalMoney;

    private List<OrderGoods> items;

    public CartIntentInfo() {

    }


    protected CartIntentInfo(Parcel in) {
        orderNo = in.readString();
        count = in.readInt();
        totalMoney = in.readDouble();
    }

    public static final Creator<CartIntentInfo> CREATOR = new Creator<CartIntentInfo>() {
        @Override
        public CartIntentInfo createFromParcel(Parcel in) {
            return new CartIntentInfo(in);
        }

        @Override
        public CartIntentInfo[] newArray(int size) {
            return new CartIntentInfo[size];
        }
    };

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<OrderGoods> getItems() {
        return items;
    }

    public void setItems(List<OrderGoods> items) {
        this.items = items;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderNo);
        dest.writeInt(count);
        dest.writeDouble(totalMoney);
    }
}

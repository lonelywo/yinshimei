package com.example.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CartIntentInfo implements Parcelable {
    private int count;
    private double totalMoney;

    private List<CartDataBean.ListBean> items;

    public CartIntentInfo() {

    }
    protected CartIntentInfo(Parcel in) {
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

    public List<CartDataBean.ListBean> getItems() {
        return items;
    }

    public void setItems(List<CartDataBean.ListBean> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}

package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ReceiveBean implements Parcelable {

    /**
     * code : 0
     * info : 请求有误！
     * data : {}
     */

    private int code;
    private String info;
    private DataBean data;

    protected ReceiveBean(Parcel in) {
        code = in.readInt();
        info = in.readString();
    }

    public static final Creator<ReceiveBean> CREATOR = new Creator<ReceiveBean>() {
        @Override
        public ReceiveBean createFromParcel(Parcel in) {
            return new ReceiveBean(in);
        }

        @Override
        public ReceiveBean[] newArray(int size) {
            return new ReceiveBean[size];
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
    }
}

package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class BindTuijianBean implements Parcelable {

    /**
     * code : 0
     * info : 请求有误！
     * data : {}
     */

    private int code;
    private String info;
    private DataBean data;

    protected BindTuijianBean(Parcel in) {
        code = in.readInt();
        info = in.readString();
    }

    public static final Creator<BindTuijianBean> CREATOR = new Creator<BindTuijianBean>() {
        @Override
        public BindTuijianBean createFromParcel(Parcel in) {
            return new BindTuijianBean(in);
        }

        @Override
        public BindTuijianBean[] newArray(int size) {
            return new BindTuijianBean[size];
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

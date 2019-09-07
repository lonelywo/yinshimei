package com.example.enticement.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseList<T> {

    public int code;
    public List<T> data;
    public String msg;
    public String info;
    @SerializedName("min_id")
    public int minId;
    public String imageUrl;

    @Override
    public String toString() {
        return "BaseList{" +
                "info=" + info +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", minId=" + minId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

package com.cuci.enticement.bean;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Base<T> {

    public int code;
    public T data;
    public String msg;
    public String info;
    @SerializedName("min_id")
    public int minId;

    @NonNull
    @Override
    public String toString() {
        return "Base{" +
                "code=" + code +
                ", data=" + data +
                ", info=" + info +
                ", msg='" + msg + '\'' +
                '}';
    }
}

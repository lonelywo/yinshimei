package com.cuci.enticement.bean;



import java.util.List;

public class BaseList<T> {

    public int code;
    public List<T> data;
    public String msg;
    public String info;
    public String imageUrl;

    @Override
    public String toString() {
        return "BaseList{" +
                "info=" + info +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

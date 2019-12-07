package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ClauseBean implements Parcelable {

    /**
     * code : 1
     * info : 获取成功！
     * data : {"title":"注册协议及隐私政策","url":"http://web.enticementchina.com/appweb/protocol.html"}
     */

    private int code;
    private String info;
    private DataBean data;

    protected ClauseBean(Parcel in) {
        code = in.readInt();
        info = in.readString();
    }

    public static final Creator<ClauseBean> CREATOR = new Creator<ClauseBean>() {
        @Override
        public ClauseBean createFromParcel(Parcel in) {
            return new ClauseBean(in);
        }

        @Override
        public ClauseBean[] newArray(int size) {
            return new ClauseBean[size];
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
         * title : 注册协议及隐私政策
         * url : http://web.enticementchina.com/appweb/protocol.html
         */

        private String title;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

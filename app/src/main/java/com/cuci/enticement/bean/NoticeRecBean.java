package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class NoticeRecBean implements Parcelable {

    /**
     * code : 1
     * info : 获取信息成功！
     * data : {"content":"<p>中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红&nbsp;<\/p>\r\n"}
     */

    private int code;
    private String info;
    private DataBean data;

    protected NoticeRecBean(Parcel in) {
        code = in.readInt();
        info = in.readString();
    }

    public static final Creator<NoticeRecBean> CREATOR = new Creator<NoticeRecBean>() {
        @Override
        public NoticeRecBean createFromParcel(Parcel in) {
            return new NoticeRecBean(in);
        }

        @Override
        public NoticeRecBean[] newArray(int size) {
            return new NoticeRecBean[size];
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
         * content : <p>中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红&nbsp;</p>

         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

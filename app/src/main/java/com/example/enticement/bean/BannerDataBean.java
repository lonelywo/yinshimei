package com.example.enticement.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class BannerDataBean implements Parcelable {


    /**
     * title : 新零售礼包
     * img : https://yinshimei2.xd.cuci.cc/upload/4197575d76add9ba/4ef55117abdbf333.jpg
     * url : 6524686581
     */

    private String title;
    private String img;
    private String url;

    protected BannerDataBean(Parcel in) {
        title = in.readString();
        img = in.readString();
        url = in.readString();
    }

    public static final Creator<BannerDataBean> CREATOR = new Creator<BannerDataBean>() {
        @Override
        public BannerDataBean createFromParcel(Parcel in) {
            return new BannerDataBean(in);
        }

        @Override
        public BannerDataBean[] newArray(int size) {
            return new BannerDataBean[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(img);
        parcel.writeString(url);
    }
}

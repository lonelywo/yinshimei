package com.cuci.enticement.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class BannerDataBean implements Parcelable {
    /**
     * img : https://qiniu.cdn.enticementchina.com/6821c83d19ef5a15/adcfa2765bca1fb2.jpg
     * url : 6713060768
     * type : 1
     * link : 6713060768
     * title : #499
     * wei : #
     */

    private String img;
    private String url;
    private String type;
    private String link;
    private String title;
    private String wei;

    protected BannerDataBean(Parcel in) {
        img = in.readString();
        url = in.readString();
        type = in.readString();
        link = in.readString();
        title = in.readString();
        wei = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(img);
        dest.writeString(url);
        dest.writeString(type);
        dest.writeString(link);
        dest.writeString(title);
        dest.writeString(wei);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWei() {
        return wei;
    }

    public void setWei(String wei) {
        this.wei = wei;
    }




}

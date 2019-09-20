package com.example.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FunctionModel implements Parcelable {
    private String keyword;
    private String title;
    private String content;
    private String newsTime;
    private String icon;

    private List<String> videoArr;    //视频地址数组
    private List<String> imageArr;    //图片地址数组


    public FunctionModel() {
    }

    protected FunctionModel(Parcel in) {
        keyword = in.readString();
        title = in.readString();
        content = in.readString();
        newsTime = in.readString();
        icon = in.readString();
        videoArr = in.createStringArrayList();
        imageArr = in.createStringArrayList();
    }

    public static final Parcelable.Creator<FunctionModel> CREATOR = new Parcelable.Creator<FunctionModel>() {
        @Override
        public FunctionModel createFromParcel(Parcel in) {
            return new FunctionModel(in);
        }

        @Override
        public FunctionModel[] newArray(int size) {
            return new FunctionModel[size];
        }
    };

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getVideoArr() {
        return videoArr;
    }

    public void setVideoArr(List<String> videoArr) {
        this.videoArr = videoArr;
    }

    public List<String> getImageArr() {
        return imageArr;
    }

    public void setImageArr(List<String> imageArr) {
        this.imageArr = imageArr;
    }


    @Override
    public String toString() {
        return "FunctionModel{" +
                "keyword='" + keyword + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", newsTime='" + newsTime + '\'' +
                ", icon='" + icon + '\'' +
                ", videoArr=" + videoArr +
                ", imageArr=" + imageArr +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(keyword);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(newsTime);
        parcel.writeString(icon);
        parcel.writeStringList(videoArr);
        parcel.writeStringList(imageArr);
    }
}

package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Version implements Parcelable {

    private Data data;

    protected Version(Parcel in) {
    }

    public static final Creator<Version> CREATOR = new Creator<Version>() {
        @Override
        public Version createFromParcel(Parcel in) {
            return new Version(in);
        }

        @Override
        public Version[] newArray(int size) {
            return new Version[size];
        }
    };

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Version{" +
                "data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static class Data implements Parcelable {

        private int version;
        private String terrace;
        @SerializedName("version_name")
        private String versionName;
        private String url;
        private String content;
        @SerializedName("hl_content")
        private String hlContent;
        @SerializedName("forced_updating")
        private boolean forcedUpdating;
        @SerializedName("app_name")
        private String appName;

        protected Data(Parcel in) {
            version = in.readInt();
            terrace = in.readString();
            versionName = in.readString();
            url = in.readString();
            content = in.readString();
            hlContent = in.readString();
            forcedUpdating = in.readByte() != 0;
            appName = in.readString();
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getTerrace() {
            return terrace;
        }

        public void setTerrace(String terrace) {
            this.terrace = terrace;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHlContent() {
            return hlContent;
        }

        public void setHlContent(String hlContent) {
            this.hlContent = hlContent;
        }

        public boolean isForcedUpdating() {
            return forcedUpdating;
        }

        public void setForcedUpdating(boolean forcedUpdating) {
            this.forcedUpdating = forcedUpdating;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        @Override
        public String toString() {
            return "Version{" +
                    "version=" + version +
                    ", terrace='" + terrace + '\'' +
                    ", versionName='" + versionName + '\'' +
                    ", url='" + url + '\'' +
                    ", content='" + content + '\'' +
                    ", hlContent='" + hlContent + '\'' +
                    ", forcedUpdating=" + forcedUpdating +
                    ", appName='" + appName + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(version);
            dest.writeString(terrace);
            dest.writeString(versionName);
            dest.writeString(url);
            dest.writeString(content);
            dest.writeString(hlContent);
            dest.writeByte((byte) (forcedUpdating ? 1 : 0));
            dest.writeString(appName);
        }
    }

}

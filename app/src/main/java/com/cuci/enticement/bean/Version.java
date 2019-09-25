package com.cuci.enticement.bean;


import java.io.Serializable;

public class Version implements Serializable {

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public boolean isForcedUpdating() {
        return forcedUpdating;
    }

    public void setForcedUpdating(boolean forcedUpdating) {
        this.forcedUpdating = forcedUpdating;
    }

    /*
* 版本名 1.0.0
* 版本号 1
* APK下载链接
* 更新内容说明
* 是否强制更新
*
* */
        private int version;
        private String versionName;
        private String url;
        private String content;
        private boolean forcedUpdating;


}

package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class UploadTuBean implements Serializable {

    /**
     * code : 1
     * info : ok
     * data : {"url":["https://qiniu.cdn.enticementchina.com/b04268ccecd6506dbd65f86ffa1ad353.png","https://qiniu.cdn.enticementchina.com/b04268ccecd6506dbd65f86ffa1ad353.png"]}
     */

    private int code;
    private String info;
    private DataBean data;

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

    public static class DataBean {
        private List<String> url;

        public List<String> getUrl() {
            return url;
        }

        public void setUrl(List<String> url) {
            this.url = url;
        }
    }
}

package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class YsmBean implements Serializable {

    /**
     * code : 1
     * info : 获取成功！
     * data : [{"title":"用户服务协议","url":"http://www.enticementchina.com/user_agreement.html"}]
     */

    private int code;
    private String info;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * title : 用户服务协议
         * url : http://www.enticementchina.com/user_agreement.html
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

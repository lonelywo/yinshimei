package com.cuci.enticement.bean;

import java.io.Serializable;

public class OpenGGBean implements Serializable {


    /**
     * code : 1
     * info : ok
     * data : {"is_show":1,"ad_type":0,"url":"https://qiniu.cdn.enticementchina.com/001a4e0ab3a56078/d4a4b502a446796a.jpg","type":0,"link":6713068016,"endtime":"2020-03-03 23:59:59"}
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

    public static class DataBean implements Serializable{
        /**
         * is_show : 1
         * ad_type : 0
         * url : https://qiniu.cdn.enticementchina.com/001a4e0ab3a56078/d4a4b502a446796a.jpg
         * type : 0
         * link : 6713068016
         * endtime : 2020-03-03 23:59:59
         */

        private int is_show;
        private int ad_type;
        private String url;
        private int type;
        private long link;
        private String endtime;

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }

        public int getAd_type() {
            return ad_type;
        }

        public void setAd_type(int ad_type) {
            this.ad_type = ad_type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getLink() {
            return link;
        }

        public void setLink(long link) {
            this.link = link;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
    }
}

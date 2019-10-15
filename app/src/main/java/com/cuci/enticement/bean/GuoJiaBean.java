package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class GuoJiaBean implements Serializable {

    /**
     * code : 1
     * info : 获取成功！
     * data : [{"title":"中国 大陆","english":"Taiwan","code":86},{"title":"马来西亚","english":"Malaysia","code":60}]
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
         * title : 中国 大陆
         * english : Taiwan
         * code : 86
         */

        private String title;
        private String english;
        private int code;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}

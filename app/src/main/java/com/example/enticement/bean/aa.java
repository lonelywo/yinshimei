package com.example.enticement.bean;

import java.util.List;

public class aa {


    /**
     * code : 1
     * info : 获取首页播图列表成功！
     * data : [{"title":"新零售礼包","img":"https://yinshimei2.xd.cuci.cc/upload/4197575d76add9ba/4ef55117abdbf333.jpg","url":"6524686581"},{"title":"黑金魅惑蕾丝面膜","img":"https://yinshimei2.xd.cuci.cc/upload/23725abde02d9df9/8f0d9783010964f4.jpg","url":"6528376361"},{"title":"温柔高保湿洁面乳","img":"https://yinshimei2.xd.cuci.cc/upload/12486dc46aff11ee/2c31609528af6c3d.jpg","url":"6528384143"}]
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

    public static class DataBean {
        /**
         * title : 新零售礼包
         * img : https://yinshimei2.xd.cuci.cc/upload/4197575d76add9ba/4ef55117abdbf333.jpg
         * url : 6524686581
         */

        private String title;
        private String img;
        private String url;

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
    }
}

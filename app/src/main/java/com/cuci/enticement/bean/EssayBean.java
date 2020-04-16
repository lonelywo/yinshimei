package com.cuci.enticement.bean;

import java.io.Serializable;

public class EssayBean implements Serializable {


    /**
     * code : 1
     * info : 获取信息成功！
     * data : {"brief":"{\"title\":\"购面膜赠洁面乳\",\"logo\":\"https:\\/\\/qiniu.cdn.enticementchina.com\\/25683cd62fafe7ad\\/d43eb5210f8515ec.png\",\"goods_id\":6856280800}","share_info":"{\"title\":\"购面膜赠洁面乳\",\"logo\":\"https:\\/\\/qiniu.cdn.enticementchina.com\\/25683cd62fafe7ad\\/d43eb5210f8515ec.png\",\"goods_id\":6856280800}","content":"<body style=\"padding:0;margin:0\"><p><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/7874973f95581817/dd92caa5f9c8bf2d.jpg\" style=\"max-width:100%\" title=\"image\" /><\/p>\r\n<\/body>"}
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
         * brief : {"title":"购面膜赠洁面乳","logo":"https:\/\/qiniu.cdn.enticementchina.com\/25683cd62fafe7ad\/d43eb5210f8515ec.png","goods_id":6856280800}
         * share_info : {"title":"购面膜赠洁面乳","logo":"https:\/\/qiniu.cdn.enticementchina.com\/25683cd62fafe7ad\/d43eb5210f8515ec.png","goods_id":6856280800}
         * content : <body style="padding:0;margin:0"><p><img border="0" src="https://qiniu.cdn.enticementchina.com/7874973f95581817/dd92caa5f9c8bf2d.jpg" style="max-width:100%" title="image" /></p>
         </body>
         */

        private String brief;
        private String share_info;
        private String content;

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getShare_info() {
            return share_info;
        }

        public void setShare_info(String share_info) {
            this.share_info = share_info;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

package com.cuci.enticement.bean;

import java.io.Serializable;

public class ShareimgBean implements Serializable{

    /**
     * code : 1
     * info : 生成成功！
     * data : {"sharepic":"https://qiniu.cdn.enticementchina.com/f77b7aa0b5a2204c/ccc742ffa7448bf6.jpg"}
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

    public static class DataBean implements Serializable {
        /**
         * sharepic : https://qiniu.cdn.enticementchina.com/f77b7aa0b5a2204c/ccc742ffa7448bf6.jpg
         */

        private String sharepic;

        public String getSharepic() {
            return sharepic;
        }

        public void setSharepic(String sharepic) {
            this.sharepic = sharepic;
        }
    }
}

package com.cuci.enticement.bean;



public class LuckDrawBean {


    /**
     * code : 1
     * info : ok
     * data : {"desc":"","winIndex":6,"msg":"一定是姿势不对，什么也没有中到","logo":"https://qiniu.cdn.enticementchina.com/806daa57be294cac/6029c65f27575682.jpg"}
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
        /**
         * desc :
         * winIndex : 6
         * msg : 一定是姿势不对，什么也没有中到
         * logo : https://qiniu.cdn.enticementchina.com/806daa57be294cac/6029c65f27575682.jpg
         */

        private String desc;
        private int winIndex;
        private String msg;
        private String logo;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getWinIndex() {
            return winIndex;
        }

        public void setWinIndex(int winIndex) {
            this.winIndex = winIndex;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}

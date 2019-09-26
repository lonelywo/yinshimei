package com.cuci.enticement.bean;


import java.io.Serializable;

public class Version implements Serializable {


    /**
     * code : 1
     * info : 版本信息获取成功！
     * data : {"version":"1.8.0","versionName":1,"url":"http://a.app.qq.com/o/simple.jsp?pkgname=com.cuci.enticemen","content":"测试","is_force":0}
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
         * version : 1.8.0
         * versionName : 1
         * url : http://a.app.qq.com/o/simple.jsp?pkgname=com.cuci.enticemen
         * content : 测试
         * is_force : 0
         */

        private String version;
        private int versionName;
        private String url;
        private String content;
        private int is_force;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVersionName() {
            return versionName;
        }

        public void setVersionName(int versionName) {
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

        public int getIs_force() {
            return is_force;
        }

        public void setIs_force(int is_force) {
            this.is_force = is_force;
        }
    }
}

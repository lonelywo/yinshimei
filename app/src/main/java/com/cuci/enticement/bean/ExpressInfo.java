package com.cuci.enticement.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExpressInfo {


    /**
     * code : 1
     * info : 获取物流查询结果！
     * data : {"message":"ok","nu":"800398697338673378","ischeck":"1","condition":"F00","com":"yuantong","status":"200","state":"3","data":[{"time":"2018-07-03 13:18:11","ftime":"2018-07-03 13:18:11","context":"快件已签收 签收人 :蜂巢快递柜   感谢使用圆通速递，期待再次为您服务","location":null},{"time":"2018-07-03 09:03:27","ftime":"2018-07-03 09:03:27","context":"北京昌平区南口镇公司派件人 :卢晓波（173 1007 1319） 正在派件","location":null},{"time":"2018-07-03 06:50:22","ftime":"2018-07-03 06:50:22","context":"快件已发往 北京昌平区南口镇公司","location":null},{"time":"2018-07-03 05:20:37","ftime":"2018-07-03 05:20:37","context":"快件已到达 北京市昌平城区公司","location":null},{"time":"2018-07-02 13:12:11","ftime":"2018-07-02 13:12:11","context":"快件已发往 北京市昌平城区公司","location":null},{"time":"2018-07-02 11:12:08","ftime":"2018-07-02 11:12:08","context":"快件已到达 北京转运中心","location":null},{"time":"2018-06-30 20:55:29","ftime":"2018-06-30 20:55:29","context":"快件已发往 北京转运中心","location":null},{"time":"2018-06-30 06:32:39","ftime":"2018-06-30 06:32:39","context":"快件已到达 福州转运中心","location":null},{"time":"2018-06-30 04:27:04","ftime":"2018-06-30 04:27:04","context":"快件已发往 福州转运中心","location":null},{"time":"2018-06-30 02:56:24","ftime":"2018-06-30 02:56:24","context":"福建省莆田市公司（15359043454）  已收件","location":null}]}
     */

    private int code;
    private String info;
    @SerializedName("data")
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * message : ok
         * nu : 800398697338673378
         * ischeck : 1
         * condition : F00
         * com : yuantong
         * status : 200
         * state : 3
         * data : [{"time":"2018-07-03 13:18:11","ftime":"2018-07-03 13:18:11","context":"快件已签收 签收人 :蜂巢快递柜   感谢使用圆通速递，期待再次为您服务","location":null},{"time":"2018-07-03 09:03:27","ftime":"2018-07-03 09:03:27","context":"北京昌平区南口镇公司派件人 :卢晓波（173 1007 1319） 正在派件","location":null},{"time":"2018-07-03 06:50:22","ftime":"2018-07-03 06:50:22","context":"快件已发往 北京昌平区南口镇公司","location":null},{"time":"2018-07-03 05:20:37","ftime":"2018-07-03 05:20:37","context":"快件已到达 北京市昌平城区公司","location":null},{"time":"2018-07-02 13:12:11","ftime":"2018-07-02 13:12:11","context":"快件已发往 北京市昌平城区公司","location":null},{"time":"2018-07-02 11:12:08","ftime":"2018-07-02 11:12:08","context":"快件已到达 北京转运中心","location":null},{"time":"2018-06-30 20:55:29","ftime":"2018-06-30 20:55:29","context":"快件已发往 北京转运中心","location":null},{"time":"2018-06-30 06:32:39","ftime":"2018-06-30 06:32:39","context":"快件已到达 福州转运中心","location":null},{"time":"2018-06-30 04:27:04","ftime":"2018-06-30 04:27:04","context":"快件已发往 福州转运中心","location":null},{"time":"2018-06-30 02:56:24","ftime":"2018-06-30 02:56:24","context":"福建省莆田市公司（15359043454）  已收件","location":null}]
         */

        private String message;
        private String nu;
        private String ischeck;
        private String condition;
        private String com;
        private String status;
        private String state;
        private List<DataBean> data;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getNu() {
            return nu;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * time : 2018-07-03 13:18:11
             * ftime : 2018-07-03 13:18:11
             * context : 快件已签收 签收人 :蜂巢快递柜   感谢使用圆通速递，期待再次为您服务
             * location : null
             */

            private String time;
            private String ftime;
            private String context;
            private Object location;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }
        }
    }
}

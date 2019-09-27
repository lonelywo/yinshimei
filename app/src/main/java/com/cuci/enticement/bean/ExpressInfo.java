package com.cuci.enticement.bean;

import java.util.List;

public class ExpressInfo {


    /**
     * code : 1
     * info : 获取物流查询结果！
     * data : {"message":"ok","com":"debangwuliu","nu":"8478081206","data":[{"time":"2019-09-30 18:00:00","ftime":"2019-09-30 18:00:00","context":"预计送达"},{"time":"2019-09-27 06:53:00","ftime":"2019-09-27 06:53:00","context":"运输中，离开【深圳转运场】，下一站【乌鲁木齐转运场】"},{"time":"2019-09-27 02:50:00","ftime":"2019-09-27 02:50:00","context":"货物已到达深圳转运场"},{"time":"2019-09-26 23:55:00","ftime":"2019-09-26 23:55:00","context":"运输中，离开【广州转运中心】，下一站【深圳转运场】"},{"time":"2019-09-26 21:52:00","ftime":"2019-09-26 21:52:00","context":"货物已到达广州转运中心"},{"time":"2019-09-26 21:40:00","ftime":"2019-09-26 21:40:00","context":"运输中，离开【广州白云区鹤龙快递分部】，下一站【广州转运中心】"},{"time":"2019-09-26 19:17:00","ftime":"2019-09-26 19:17:00","context":"您的订单已被收件员揽收,【广州白云区鹤龙快递分部】库存中"}]}
     */

    private int code;
    private String info;
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
         * com : debangwuliu
         * nu : 8478081206
         * data : [{"time":"2019-09-30 18:00:00","ftime":"2019-09-30 18:00:00","context":"预计送达"},{"time":"2019-09-27 06:53:00","ftime":"2019-09-27 06:53:00","context":"运输中，离开【深圳转运场】，下一站【乌鲁木齐转运场】"},{"time":"2019-09-27 02:50:00","ftime":"2019-09-27 02:50:00","context":"货物已到达深圳转运场"},{"time":"2019-09-26 23:55:00","ftime":"2019-09-26 23:55:00","context":"运输中，离开【广州转运中心】，下一站【深圳转运场】"},{"time":"2019-09-26 21:52:00","ftime":"2019-09-26 21:52:00","context":"货物已到达广州转运中心"},{"time":"2019-09-26 21:40:00","ftime":"2019-09-26 21:40:00","context":"运输中，离开【广州白云区鹤龙快递分部】，下一站【广州转运中心】"},{"time":"2019-09-26 19:17:00","ftime":"2019-09-26 19:17:00","context":"您的订单已被收件员揽收,【广州白云区鹤龙快递分部】库存中"}]
         */

        private String message;
        private String com;
        private String nu;
        private List<DataBean> data;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getNu() {
            return nu;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * time : 2019-09-30 18:00:00
             * ftime : 2019-09-30 18:00:00
             * context : 预计送达
             */

            private String time;
            private String ftime;
            private String context;

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
        }
    }
}

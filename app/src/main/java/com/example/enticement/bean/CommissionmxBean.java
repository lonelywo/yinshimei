package com.example.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class CommissionmxBean implements Serializable {


    /**
     * code : 1
     * info : 获取拥金记录成功！
     * data : {"list":[{"id":1,"mid":3,"openid":"","trs_no":"","pay_no":"","pay_desc":"","pay_price":"12.20","pay_at":null,"status":1,"create_at":"2019-02-28 10:33:34"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * mid : 3
             * openid :
             * trs_no :
             * pay_no :
             * pay_desc :
             * pay_price : 12.20
             * pay_at : null
             * status : 1
             * create_at : 2019-02-28 10:33:34
             */

            private int id;
            private int mid;
            private String openid;
            private String trs_no;
            private String pay_no;
            private String pay_desc;
            private String pay_price;
            private Object pay_at;
            private int status;
            private String create_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getTrs_no() {
                return trs_no;
            }

            public void setTrs_no(String trs_no) {
                this.trs_no = trs_no;
            }

            public String getPay_no() {
                return pay_no;
            }

            public void setPay_no(String pay_no) {
                this.pay_no = pay_no;
            }

            public String getPay_desc() {
                return pay_desc;
            }

            public void setPay_desc(String pay_desc) {
                this.pay_desc = pay_desc;
            }

            public String getPay_price() {
                return pay_price;
            }

            public void setPay_price(String pay_price) {
                this.pay_price = pay_price;
            }

            public Object getPay_at() {
                return pay_at;
            }

            public void setPay_at(Object pay_at) {
                this.pay_at = pay_at;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }
        }
    }
}

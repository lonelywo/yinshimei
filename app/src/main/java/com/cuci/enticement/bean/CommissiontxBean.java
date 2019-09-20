package com.cuci.enticement.bean;

import java.util.List;

public class CommissiontxBean {

    /**
     * code : 1
     * info : 获取拥金记录成功！
     * data : {"list":[{"id":8,"type":1,"mid":3,"order_no":651261250911,"order_mid":5,"order_price":"89.00","profit_price":"8.90","create_at":"2019-02-27 17:54:43"}]}
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
             * id : 8
             * type : 1
             * mid : 3
             * order_no : 651261250911
             * order_mid : 5
             * order_price : 89.00
             * profit_price : 8.90
             * create_at : 2019-02-27 17:54:43
             */

            private int id;
            private int type;
            private int mid;
            private long order_no;
            private int order_mid;
            private String order_price;
            private String profit_price;
            private String create_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public long getOrder_no() {
                return order_no;
            }

            public void setOrder_no(long order_no) {
                this.order_no = order_no;
            }

            public int getOrder_mid() {
                return order_mid;
            }

            public void setOrder_mid(int order_mid) {
                this.order_mid = order_mid;
            }

            public String getOrder_price() {
                return order_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
            }

            public String getProfit_price() {
                return profit_price;
            }

            public void setProfit_price(String profit_price) {
                this.profit_price = profit_price;
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

package com.cuci.enticement.bean;

import java.io.Serializable;

public class TuikuanSQBean implements Serializable {

    /**
     * code : 1
     * info : 退款申请成功
     * data : {"refund_id":"28"}
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
         * refund_id : 28
         */

        private String refund_id;

        public String getRefund_id() {
            return refund_id;
        }

        public void setRefund_id(String refund_id) {
            this.refund_id = refund_id;
        }
    }
}

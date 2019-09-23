package com.cuci.enticement.bean;

import java.io.Serializable;

public class CheckPhoneBean implements Serializable {

    /**
     * code : 1
     * info : 查询成功
     * data : {"is_reg":1}
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
         * is_reg : 1
         */

        private int is_reg;

        public int getIs_reg() {
            return is_reg;
        }

        public void setIs_reg(int is_reg) {
            this.is_reg = is_reg;
        }
    }
}

package com.cuci.enticement.bean;

import java.io.Serializable;

public class ExpressCost implements Serializable {


    /**
     * code : 1
     * info : 查询订单的运费成功！
     * data : {"express_price":0,"express_type":"普通模板","express_desc":"订单总金额满2000.00元减免全部邮费"}
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
         * express_price : 0
         * express_type : 普通模板
         * express_desc : 订单总金额满2000.00元减免全部邮费
         */

        private String express_price;
        private String express_type;
        private String express_desc;

        public String getExpress_price() {
            return express_price;
        }

        public void setExpress_price(String express_price) {
            this.express_price = express_price;
        }

        public String getExpress_type() {
            return express_type;
        }

        public void setExpress_type(String express_type) {
            this.express_type = express_type;
        }

        public String getExpress_desc() {
            return express_desc;
        }

        public void setExpress_desc(String express_desc) {
            this.express_desc = express_desc;
        }
    }
}

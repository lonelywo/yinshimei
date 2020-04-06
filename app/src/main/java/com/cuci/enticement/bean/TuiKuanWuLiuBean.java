package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class TuiKuanWuLiuBean implements Serializable {

    /**
     * code : 1
     * info : ok
     * data : {"express":[{"title":"顺丰快递","code":"SF"},{"title":"圆通快递","code":"YT"},{"title":"申通快递","code":"ST"},{"title":"邮政快递","code":"EMS"}],"reason":"请您勿使用到付或平邮，且保证商品完好，以免产生拒签哦。为保障退款进度，退货时请您务必填写真实物流单号，并选择以下任意快递寄回：申通快递、中通快递、圆通快递、韵达快递、联邦快递 、百世快递、邮政、德邦快递、EMS、宅急送、优速快递。...","reason_url":"https://www.baidu.com/"}
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
         * express : [{"title":"顺丰快递","code":"SF"},{"title":"圆通快递","code":"YT"},{"title":"申通快递","code":"ST"},{"title":"邮政快递","code":"EMS"}]
         * reason : 请您勿使用到付或平邮，且保证商品完好，以免产生拒签哦。为保障退款进度，退货时请您务必填写真实物流单号，并选择以下任意快递寄回：申通快递、中通快递、圆通快递、韵达快递、联邦快递 、百世快递、邮政、德邦快递、EMS、宅急送、优速快递。...
         * reason_url : https://www.baidu.com/
         */

        private String reason;
        private String reason_url;
        private List<ExpressBean> express;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getReason_url() {
            return reason_url;
        }

        public void setReason_url(String reason_url) {
            this.reason_url = reason_url;
        }

        public List<ExpressBean> getExpress() {
            return express;
        }

        public void setExpress(List<ExpressBean> express) {
            this.express = express;
        }

        public static class ExpressBean {
            /**
             * title : 顺丰快递
             * code : SF
             */

            private String title;
            private String code;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}

package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class DuiHuanXiangQingBean implements Serializable {

    /**
     * code : 1
     * info : ok
     * data : {"order_no":2268770105497,"mid":60000000,"price_express":"0.00","pay_points":400,"express_rule_desc":"积分订单免邮费","express_rule_number":1,"express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":"","express_address_id":189605,"express_name":"呵呵","express_phone":"13526538801","express_province":"广东省","express_city":"广州市","express_area":"天河区","express_address":"黄埔大道西688号-马会家居(花城大道)","express_desc":"","status":3,"is_deleted":0,"create_at":"2020-04-24 12:04:14","order_list":[{"id":1,"mid":60000000,"order_no":2268770105497,"goods_id":6876310413,"goods_title":"黑金蕾丝面膜-vip装","goods_logo":"https://qiniu.cdn.enticementchina.com/e1aea22e48fee4b0/ca69e17e3c5f27f7.jpg","points_selling":400,"number":1,"status":1,"create_at":"2020-04-24 12:04:14"}]}
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

    public static class DataBean implements Serializable{
        /**
         * order_no : 2268770105497
         * mid : 60000000
         * price_express : 0.00
         * pay_points : 400
         * express_rule_desc : 积分订单免邮费
         * express_rule_number : 1
         * express_state : 0
         * express_company_code :
         * express_company_title :
         * express_send_no :
         * express_send_at :
         * express_address_id : 189605
         * express_name : 呵呵
         * express_phone : 13526538801
         * express_province : 广东省
         * express_city : 广州市
         * express_area : 天河区
         * express_address : 黄埔大道西688号-马会家居(花城大道)
         * express_desc :
         * status : 3
         * is_deleted : 0
         * create_at : 2020-04-24 12:04:14
         * order_list : [{"id":1,"mid":60000000,"order_no":2268770105497,"goods_id":6876310413,"goods_title":"黑金蕾丝面膜-vip装","goods_logo":"https://qiniu.cdn.enticementchina.com/e1aea22e48fee4b0/ca69e17e3c5f27f7.jpg","points_selling":400,"number":1,"status":1,"create_at":"2020-04-24 12:04:14"}]
         */

        private long order_no;
        private int mid;
        private String price_express;
        private int pay_points;
        private String express_rule_desc;
        private int express_rule_number;
        private int express_state;
        private String express_company_code;
        private String express_company_title;
        private String express_send_no;
        private String express_send_at;
        private int express_address_id;
        private String express_name;
        private String express_phone;
        private String express_province;
        private String express_city;
        private String express_area;
        private String express_address;
        private String express_desc;
        private int status;
        private int is_deleted;
        private String create_at;
        private List<OrderListBean> order_list;

        public long getOrder_no() {
            return order_no;
        }

        public void setOrder_no(long order_no) {
            this.order_no = order_no;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public String getPrice_express() {
            return price_express;
        }

        public void setPrice_express(String price_express) {
            this.price_express = price_express;
        }

        public int getPay_points() {
            return pay_points;
        }

        public void setPay_points(int pay_points) {
            this.pay_points = pay_points;
        }

        public String getExpress_rule_desc() {
            return express_rule_desc;
        }

        public void setExpress_rule_desc(String express_rule_desc) {
            this.express_rule_desc = express_rule_desc;
        }

        public int getExpress_rule_number() {
            return express_rule_number;
        }

        public void setExpress_rule_number(int express_rule_number) {
            this.express_rule_number = express_rule_number;
        }

        public int getExpress_state() {
            return express_state;
        }

        public void setExpress_state(int express_state) {
            this.express_state = express_state;
        }

        public String getExpress_company_code() {
            return express_company_code;
        }

        public void setExpress_company_code(String express_company_code) {
            this.express_company_code = express_company_code;
        }

        public String getExpress_company_title() {
            return express_company_title;
        }

        public void setExpress_company_title(String express_company_title) {
            this.express_company_title = express_company_title;
        }

        public String getExpress_send_no() {
            return express_send_no;
        }

        public void setExpress_send_no(String express_send_no) {
            this.express_send_no = express_send_no;
        }

        public String getExpress_send_at() {
            return express_send_at;
        }

        public void setExpress_send_at(String express_send_at) {
            this.express_send_at = express_send_at;
        }

        public int getExpress_address_id() {
            return express_address_id;
        }

        public void setExpress_address_id(int express_address_id) {
            this.express_address_id = express_address_id;
        }

        public String getExpress_name() {
            return express_name;
        }

        public void setExpress_name(String express_name) {
            this.express_name = express_name;
        }

        public String getExpress_phone() {
            return express_phone;
        }

        public void setExpress_phone(String express_phone) {
            this.express_phone = express_phone;
        }

        public String getExpress_province() {
            return express_province;
        }

        public void setExpress_province(String express_province) {
            this.express_province = express_province;
        }

        public String getExpress_city() {
            return express_city;
        }

        public void setExpress_city(String express_city) {
            this.express_city = express_city;
        }

        public String getExpress_area() {
            return express_area;
        }

        public void setExpress_area(String express_area) {
            this.express_area = express_area;
        }

        public String getExpress_address() {
            return express_address;
        }

        public void setExpress_address(String express_address) {
            this.express_address = express_address;
        }

        public String getExpress_desc() {
            return express_desc;
        }

        public void setExpress_desc(String express_desc) {
            this.express_desc = express_desc;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public List<OrderListBean> getOrder_list() {
            return order_list;
        }

        public void setOrder_list(List<OrderListBean> order_list) {
            this.order_list = order_list;
        }

        public static class OrderListBean implements Serializable{
            /**
             * id : 1
             * mid : 60000000
             * order_no : 2268770105497
             * goods_id : 6876310413
             * goods_title : 黑金蕾丝面膜-vip装
             * goods_logo : https://qiniu.cdn.enticementchina.com/e1aea22e48fee4b0/ca69e17e3c5f27f7.jpg
             * points_selling : 400
             * number : 1
             * status : 1
             * create_at : 2020-04-24 12:04:14
             */

            private int id;
            private int mid;
            private long order_no;
            private long goods_id;
            private String goods_title;
            private String goods_logo;
            private int points_selling;
            private int number;
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

            public long getOrder_no() {
                return order_no;
            }

            public void setOrder_no(long order_no) {
                this.order_no = order_no;
            }

            public long getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(long goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_title() {
                return goods_title;
            }

            public void setGoods_title(String goods_title) {
                this.goods_title = goods_title;
            }

            public String getGoods_logo() {
                return goods_logo;
            }

            public void setGoods_logo(String goods_logo) {
                this.goods_logo = goods_logo;
            }

            public int getPoints_selling() {
                return points_selling;
            }

            public void setPoints_selling(int points_selling) {
                this.points_selling = points_selling;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
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

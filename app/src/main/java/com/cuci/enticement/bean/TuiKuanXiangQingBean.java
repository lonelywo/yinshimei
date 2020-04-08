package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class TuiKuanXiangQingBean implements Serializable {

    /**
     * code : 1
     * info : 撤销成功
     * data : {"express":{"company":"圆通快递","send_no":"1111"},"refund":{"id":18,"order_no":685809469754,"mid":248696,"type":2,"goods_status":2,"amount":"569.00","express_amount":"20.00","image":"","reason":"包装/商品破损","mobile":"","desc":"哈哈你不","status":4,"audit_at":"2020-04-02 14:50:29","audit_desc":"","create_at":"2020-04-02 14:49:00","order_refund_list":[{"id":19,"refund_id":18,"order_no":685809469754,"order_list_id":526,"goods_id":6713068016,"goods_title":"黑金魅惑蕾丝面膜","goods_logo":"https://qiniu.cdn.enticementchina.com/88254adbc4a82ee7/8dc20f1ec63564ba.jpg","goods_spec":"规格:5片/盒","price_refund":"70.00","price_sales":"70.00","number":1,"is_refund":1,"create_at":"2020-04-02 14:49:00"},{"id":20,"refund_id":18,"order_no":685809469754,"order_list_id":527,"goods_id":6713072951,"goods_title":"白金礼盒","goods_logo":"https://qiniu.cdn.enticementchina.com/2c71dbc434b82b28/f26f730268a6e9ca.jpg","goods_spec":"规格:28片/盒","price_refund":"400.00","price_sales":"400.00","number":1,"is_refund":1,"create_at":"2020-04-02 14:49:00"},{"id":21,"refund_id":18,"order_no":685809469754,"order_list_id":528,"goods_id":6713118081,"goods_title":"因诗美黑金T恤","goods_logo":"https://qiniu.cdn.enticementchina.com/24f5b1737587d122/9be1eacced4d557e.png","goods_spec":"码数:XL","price_refund":"99.00","price_sales":"99.00","number":1,"is_refund":1,"create_at":"2020-04-02 14:49:00"}]}}
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
         * express : {"company":"圆通快递","send_no":"1111"}
         * refund : {"id":18,"order_no":685809469754,"mid":248696,"type":2,"goods_status":2,"amount":"569.00","express_amount":"20.00","image":"","reason":"包装/商品破损","mobile":"","desc":"哈哈你不","status":4,"audit_at":"2020-04-02 14:50:29","audit_desc":"","create_at":"2020-04-02 14:49:00","order_refund_list":[{"id":19,"refund_id":18,"order_no":685809469754,"order_list_id":526,"goods_id":6713068016,"goods_title":"黑金魅惑蕾丝面膜","goods_logo":"https://qiniu.cdn.enticementchina.com/88254adbc4a82ee7/8dc20f1ec63564ba.jpg","goods_spec":"规格:5片/盒","price_refund":"70.00","price_sales":"70.00","number":1,"is_refund":1,"create_at":"2020-04-02 14:49:00"},{"id":20,"refund_id":18,"order_no":685809469754,"order_list_id":527,"goods_id":6713072951,"goods_title":"白金礼盒","goods_logo":"https://qiniu.cdn.enticementchina.com/2c71dbc434b82b28/f26f730268a6e9ca.jpg","goods_spec":"规格:28片/盒","price_refund":"400.00","price_sales":"400.00","number":1,"is_refund":1,"create_at":"2020-04-02 14:49:00"},{"id":21,"refund_id":18,"order_no":685809469754,"order_list_id":528,"goods_id":6713118081,"goods_title":"因诗美黑金T恤","goods_logo":"https://qiniu.cdn.enticementchina.com/24f5b1737587d122/9be1eacced4d557e.png","goods_spec":"码数:XL","price_refund":"99.00","price_sales":"99.00","number":1,"is_refund":1,"create_at":"2020-04-02 14:49:00"}]}
         */

        private ExpressBean express;
        private RefundBean refund;

        public ExpressBean getExpress() {
            return express;
        }

        public void setExpress(ExpressBean express) {
            this.express = express;
        }

        public RefundBean getRefund() {
            return refund;
        }

        public void setRefund(RefundBean refund) {
            this.refund = refund;
        }

        public static class ExpressBean implements Serializable{
            /**
             * company : 圆通快递
             * send_no : 1111
             */

            private String company;
            private String send_no;

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getSend_no() {
                return send_no;
            }

            public void setSend_no(String send_no) {
                this.send_no = send_no;
            }
        }

        public static class RefundBean implements Serializable{
            /**
             * id : 18
             * order_no : 685809469754
             * mid : 248696
             * type : 2
             * goods_status : 2
             * amount : 569.00
             * express_amount : 20.00
             * image :
             * reason : 包装/商品破损
             * mobile :
             * desc : 哈哈你不
             * status : 4
             * audit_at : 2020-04-02 14:50:29
             * audit_desc :
             * create_at : 2020-04-02 14:49:00
             * address : 2020-04-02 14:49:00
             * contocts : 2020-04-02 14:49:00
             * order_refund_list : [{"id":19,"refund_id":18,"order_no":685809469754,"order_list_id":526,"goods_id":6713068016,"goods_title":"黑金魅惑蕾丝面膜","goods_logo":"https://qiniu.cdn.enticementchina.com/88254adbc4a82ee7/8dc20f1ec63564ba.jpg","goods_spec":"规格:5片/盒","price_refund":"70.00","price_sales":"70.00","number":1,"is_refund":1,"create_at":"2020-04-02 14:49:00"},{"id":20,"refund_id":18,"order_no":685809469754,"order_list_id":527,"goods_id":6713072951,"goods_title":"白金礼盒","goods_logo":"https://qiniu.cdn.enticementchina.com/2c71dbc434b82b28/f26f730268a6e9ca.jpg","goods_spec":"规格:28片/盒","price_refund":"400.00","price_sales":"400.00","number":1,"is_refund":1,"create_at":"2020-04-02 14:49:00"},{"id":21,"refund_id":18,"order_no":685809469754,"order_list_id":528,"goods_id":6713118081,"goods_title":"因诗美黑金T恤","goods_logo":"https://qiniu.cdn.enticementchina.com/24f5b1737587d122/9be1eacced4d557e.png","goods_spec":"码数:XL","price_refund":"99.00","price_sales":"99.00","number":1,"is_refund":1,"create_at":"2020-04-02 14:49:00"}]
             */

            private int id;
            private long order_no;
            private int mid;
            private int type;
            private int goods_status;
            private String amount;
            private String express_amount;
            private String image;
            private String reason;
            private String mobile;
            private String desc;
            private int status;
            private String audit_at;
            private String audit_desc;
            private String create_at;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            private String address;


            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }

            private String contacts;
            private List<OrderRefundListBean> order_refund_list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getGoods_status() {
                return goods_status;
            }

            public void setGoods_status(int goods_status) {
                this.goods_status = goods_status;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getExpress_amount() {
                return express_amount;
            }

            public void setExpress_amount(String express_amount) {
                this.express_amount = express_amount;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAudit_at() {
                return audit_at;
            }

            public void setAudit_at(String audit_at) {
                this.audit_at = audit_at;
            }

            public String getAudit_desc() {
                return audit_desc;
            }

            public void setAudit_desc(String audit_desc) {
                this.audit_desc = audit_desc;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public List<OrderRefundListBean> getOrder_refund_list() {
                return order_refund_list;
            }

            public void setOrder_refund_list(List<OrderRefundListBean> order_refund_list) {
                this.order_refund_list = order_refund_list;
            }

            public static class OrderRefundListBean implements Serializable{
                /**
                 * id : 19
                 * refund_id : 18
                 * order_no : 685809469754
                 * order_list_id : 526
                 * goods_id : 6713068016
                 * goods_title : 黑金魅惑蕾丝面膜
                 * goods_logo : https://qiniu.cdn.enticementchina.com/88254adbc4a82ee7/8dc20f1ec63564ba.jpg
                 * goods_spec : 规格:5片/盒
                 * price_refund : 70.00
                 * price_sales : 70.00
                 * number : 1
                 * is_refund : 1
                 * create_at : 2020-04-02 14:49:00
                 */

                private int id;
                private int refund_id;
                private long order_no;
                private int order_list_id;
                private long goods_id;
                private String goods_title;
                private String goods_logo;
                private String goods_spec;
                private String price_refund;
                private String price_sales;
                private int number;
                private int is_refund;
                private String create_at;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getRefund_id() {
                    return refund_id;
                }

                public void setRefund_id(int refund_id) {
                    this.refund_id = refund_id;
                }

                public long getOrder_no() {
                    return order_no;
                }

                public void setOrder_no(long order_no) {
                    this.order_no = order_no;
                }

                public int getOrder_list_id() {
                    return order_list_id;
                }

                public void setOrder_list_id(int order_list_id) {
                    this.order_list_id = order_list_id;
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

                public String getGoods_spec() {
                    return goods_spec;
                }

                public void setGoods_spec(String goods_spec) {
                    this.goods_spec = goods_spec;
                }

                public String getPrice_refund() {
                    return price_refund;
                }

                public void setPrice_refund(String price_refund) {
                    this.price_refund = price_refund;
                }

                public String getPrice_sales() {
                    return price_sales;
                }

                public void setPrice_sales(String price_sales) {
                    this.price_sales = price_sales;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public int getIs_refund() {
                    return is_refund;
                }

                public void setIs_refund(int is_refund) {
                    this.is_refund = is_refund;
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
}

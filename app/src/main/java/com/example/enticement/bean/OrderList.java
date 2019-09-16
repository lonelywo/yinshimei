package com.example.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class OrderList {


    /**
     * code : 1
     * info : 获取订单列表成功！
     * data : {"page":{"limit":20,"total":14,"pages":1,"current":1},"list":[{"id":14,"mid":1,"order_no":648150929255,"agent_id":0,"agent_auth":0,"from_mid":0,"from_agent_id":0,"belong_mid":0,"price_total":"0.00","price_goods":"0.00","price_express":"0.00","price_service":"0.00","pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":0,"cancel_at":null,"cancel_desc":"","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_type":0,"express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","status":3,"calc_knot_rebate":0,"has_knot_rebate":0,"rebate_remark":"","is_deleted":0,"create_at":"2019-01-22 17:55:13","list":[{"id":14,"mid":1,"order_no":648150929255,"goods_id":1,"goods_title":"test1","goods_logo":"http://hao.xd.cuci.cc/upload/decb0fe26fa3f486/b3f6521bf29403c8.png","goods_spec":"默认分组::默认规格","price_real":"0.00","price_selling":"188.00","price_market":"200.00","price_member":"0.00","price_express":"0.00","price_service":"0.00","agent_auth":0,"agent_knot":0,"number":1,"create_at":"2019-01-22 17:55:13"}]}]}
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
         * page : {"limit":20,"total":14,"pages":1,"current":1}
         * list : [{"id":14,"mid":1,"order_no":648150929255,"agent_id":0,"agent_auth":0,"from_mid":0,"from_agent_id":0,"belong_mid":0,"price_total":"0.00","price_goods":"0.00","price_express":"0.00","price_service":"0.00","pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":0,"cancel_at":null,"cancel_desc":"","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_type":0,"express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","status":3,"calc_knot_rebate":0,"has_knot_rebate":0,"rebate_remark":"","is_deleted":0,"create_at":"2019-01-22 17:55:13","list":[{"id":14,"mid":1,"order_no":648150929255,"goods_id":1,"goods_title":"test1","goods_logo":"http://hao.xd.cuci.cc/upload/decb0fe26fa3f486/b3f6521bf29403c8.png","goods_spec":"默认分组::默认规格","price_real":"0.00","price_selling":"188.00","price_market":"200.00","price_member":"0.00","price_express":"0.00","price_service":"0.00","agent_auth":0,"agent_knot":0,"number":1,"create_at":"2019-01-22 17:55:13"}]}]
         */

        private PageBean page;
        private List<OrderBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<OrderBean> getList() {
            return list;
        }

        public void setList(List<OrderBean> list) {
            this.list = list;
        }

        public static class PageBean {
            /**
             * limit : 20
             * total : 14
             * pages : 1
             * current : 1
             */

            private int limit;
            private int total;
            private int pages;
            private int current;

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getCurrent() {
                return current;
            }

            public void setCurrent(int current) {
                this.current = current;
            }
        }

        public static class OrderBean implements Parcelable {
            /**
             * id : 14
             * mid : 1
             * order_no : 648150929255
             * agent_id : 0
             * agent_auth : 0
             * from_mid : 0
             * from_agent_id : 0
             * belong_mid : 0
             * price_total : 0.00
             * price_goods : 0.00
             * price_express : 0.00
             * price_service : 0.00
             * pay_state : 0
             * pay_type :
             * pay_price : 0.00
             * pay_no :
             * pay_at : null
             * cancel_state : 0
             * cancel_at : null
             * cancel_desc :
             * refund_state : 0
             * refund_at : null
             * refund_no :
             * refund_price : 0.00
             * refund_desc :
             * api_order_no :
             * api_tracking_no :
             * express_type : 0
             * express_state : 0
             * express_company_code :
             * express_company_title :
             * express_send_no :
             * express_send_at : null
             * express_address_id : 0
             * express_name :
             * express_phone :
             * express_province :
             * express_city :
             * express_area :
             * express_address :
             * status : 3
             * calc_knot_rebate : 0
             * has_knot_rebate : 0
             * rebate_remark :
             * is_deleted : 0
             * create_at : 2019-01-22 17:55:13
             * list : [{"id":14,"mid":1,"order_no":648150929255,"goods_id":1,"goods_title":"test1","goods_logo":"http://hao.xd.cuci.cc/upload/decb0fe26fa3f486/b3f6521bf29403c8.png","goods_spec":"默认分组::默认规格","price_real":"0.00","price_selling":"188.00","price_market":"200.00","price_member":"0.00","price_express":"0.00","price_service":"0.00","agent_auth":0,"agent_knot":0,"number":1,"create_at":"2019-01-22 17:55:13"}]
             */

            private int id;
            private int mid;
            private long order_no;
            private int agent_id;
            private int agent_auth;
            private int from_mid;
            private int from_agent_id;
            private int belong_mid;
            private String price_total;
            private String price_goods;
            private String price_express;
            private String price_service;
            private int pay_state;
            private String pay_type;
            private String pay_price;
            private String pay_no;
            private Object pay_at;
            private int cancel_state;
            private Object cancel_at;
            private String cancel_desc;
            private int refund_state;
            private Object refund_at;
            private String refund_no;
            private String refund_price;
            private String refund_desc;
            private String api_order_no;
            private String api_tracking_no;
            private int express_type;
            private int express_state;
            private String express_company_code;
            private String express_company_title;
            private String express_send_no;
            private Object express_send_at;
            private int express_address_id;
            private String express_name;
            private String express_phone;
            private String express_province;
            private String express_city;
            private String express_area;
            private String express_address;
            private int status;
            private int calc_knot_rebate;
            private int has_knot_rebate;
            private String rebate_remark;
            private int is_deleted;
            private String create_at;
            private List<GoodsBean> list;

            public OrderBean(){

            }


            protected OrderBean(Parcel in) {
                id = in.readInt();
                mid = in.readInt();
                order_no = in.readLong();
                agent_id = in.readInt();
                agent_auth = in.readInt();
                from_mid = in.readInt();
                from_agent_id = in.readInt();
                belong_mid = in.readInt();
                price_total = in.readString();
                price_goods = in.readString();
                price_express = in.readString();
                price_service = in.readString();
                pay_state = in.readInt();
                pay_type = in.readString();
                pay_price = in.readString();
                pay_no = in.readString();
                cancel_state = in.readInt();
                cancel_desc = in.readString();
                refund_state = in.readInt();
                refund_no = in.readString();
                refund_price = in.readString();
                refund_desc = in.readString();
                api_order_no = in.readString();
                api_tracking_no = in.readString();
                express_type = in.readInt();
                express_state = in.readInt();
                express_company_code = in.readString();
                express_company_title = in.readString();
                express_send_no = in.readString();
                express_address_id = in.readInt();
                express_name = in.readString();
                express_phone = in.readString();
                express_province = in.readString();
                express_city = in.readString();
                express_area = in.readString();
                express_address = in.readString();
                status = in.readInt();
                calc_knot_rebate = in.readInt();
                has_knot_rebate = in.readInt();
                rebate_remark = in.readString();
                is_deleted = in.readInt();
                create_at = in.readString();
            }

            public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
                @Override
                public OrderBean createFromParcel(Parcel in) {
                    return new OrderBean(in);
                }

                @Override
                public OrderBean[] newArray(int size) {
                    return new OrderBean[size];
                }
            };

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

            public int getAgent_id() {
                return agent_id;
            }

            public void setAgent_id(int agent_id) {
                this.agent_id = agent_id;
            }

            public int getAgent_auth() {
                return agent_auth;
            }

            public void setAgent_auth(int agent_auth) {
                this.agent_auth = agent_auth;
            }

            public int getFrom_mid() {
                return from_mid;
            }

            public void setFrom_mid(int from_mid) {
                this.from_mid = from_mid;
            }

            public int getFrom_agent_id() {
                return from_agent_id;
            }

            public void setFrom_agent_id(int from_agent_id) {
                this.from_agent_id = from_agent_id;
            }

            public int getBelong_mid() {
                return belong_mid;
            }

            public void setBelong_mid(int belong_mid) {
                this.belong_mid = belong_mid;
            }

            public String getPrice_total() {
                return price_total;
            }

            public void setPrice_total(String price_total) {
                this.price_total = price_total;
            }

            public String getPrice_goods() {
                return price_goods;
            }

            public void setPrice_goods(String price_goods) {
                this.price_goods = price_goods;
            }

            public String getPrice_express() {
                return price_express;
            }

            public void setPrice_express(String price_express) {
                this.price_express = price_express;
            }

            public String getPrice_service() {
                return price_service;
            }

            public void setPrice_service(String price_service) {
                this.price_service = price_service;
            }

            public int getPay_state() {
                return pay_state;
            }

            public void setPay_state(int pay_state) {
                this.pay_state = pay_state;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getPay_price() {
                return pay_price;
            }

            public void setPay_price(String pay_price) {
                this.pay_price = pay_price;
            }

            public String getPay_no() {
                return pay_no;
            }

            public void setPay_no(String pay_no) {
                this.pay_no = pay_no;
            }

            public Object getPay_at() {
                return pay_at;
            }

            public void setPay_at(Object pay_at) {
                this.pay_at = pay_at;
            }

            public int getCancel_state() {
                return cancel_state;
            }

            public void setCancel_state(int cancel_state) {
                this.cancel_state = cancel_state;
            }

            public Object getCancel_at() {
                return cancel_at;
            }

            public void setCancel_at(Object cancel_at) {
                this.cancel_at = cancel_at;
            }

            public String getCancel_desc() {
                return cancel_desc;
            }

            public void setCancel_desc(String cancel_desc) {
                this.cancel_desc = cancel_desc;
            }

            public int getRefund_state() {
                return refund_state;
            }

            public void setRefund_state(int refund_state) {
                this.refund_state = refund_state;
            }

            public Object getRefund_at() {
                return refund_at;
            }

            public void setRefund_at(Object refund_at) {
                this.refund_at = refund_at;
            }

            public String getRefund_no() {
                return refund_no;
            }

            public void setRefund_no(String refund_no) {
                this.refund_no = refund_no;
            }

            public String getRefund_price() {
                return refund_price;
            }

            public void setRefund_price(String refund_price) {
                this.refund_price = refund_price;
            }

            public String getRefund_desc() {
                return refund_desc;
            }

            public void setRefund_desc(String refund_desc) {
                this.refund_desc = refund_desc;
            }

            public String getApi_order_no() {
                return api_order_no;
            }

            public void setApi_order_no(String api_order_no) {
                this.api_order_no = api_order_no;
            }

            public String getApi_tracking_no() {
                return api_tracking_no;
            }

            public void setApi_tracking_no(String api_tracking_no) {
                this.api_tracking_no = api_tracking_no;
            }

            public int getExpress_type() {
                return express_type;
            }

            public void setExpress_type(int express_type) {
                this.express_type = express_type;
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

            public Object getExpress_send_at() {
                return express_send_at;
            }

            public void setExpress_send_at(Object express_send_at) {
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCalc_knot_rebate() {
                return calc_knot_rebate;
            }

            public void setCalc_knot_rebate(int calc_knot_rebate) {
                this.calc_knot_rebate = calc_knot_rebate;
            }

            public int getHas_knot_rebate() {
                return has_knot_rebate;
            }

            public void setHas_knot_rebate(int has_knot_rebate) {
                this.has_knot_rebate = has_knot_rebate;
            }

            public String getRebate_remark() {
                return rebate_remark;
            }

            public void setRebate_remark(String rebate_remark) {
                this.rebate_remark = rebate_remark;
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

            public List<GoodsBean> getList() {
                return list;
            }

            public void setList(List<GoodsBean> list) {
                this.list = list;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeInt(mid);
                dest.writeLong(order_no);
                dest.writeInt(agent_id);
                dest.writeInt(agent_auth);
                dest.writeInt(from_mid);
                dest.writeInt(from_agent_id);
                dest.writeInt(belong_mid);
                dest.writeString(price_total);
                dest.writeString(price_goods);
                dest.writeString(price_express);
                dest.writeString(price_service);
                dest.writeInt(pay_state);
                dest.writeString(pay_type);
                dest.writeString(pay_price);
                dest.writeString(pay_no);
                dest.writeInt(cancel_state);
                dest.writeString(cancel_desc);
                dest.writeInt(refund_state);
                dest.writeString(refund_no);
                dest.writeString(refund_price);
                dest.writeString(refund_desc);
                dest.writeString(api_order_no);
                dest.writeString(api_tracking_no);
                dest.writeInt(express_type);
                dest.writeInt(express_state);
                dest.writeString(express_company_code);
                dest.writeString(express_company_title);
                dest.writeString(express_send_no);
                dest.writeInt(express_address_id);
                dest.writeString(express_name);
                dest.writeString(express_phone);
                dest.writeString(express_province);
                dest.writeString(express_city);
                dest.writeString(express_area);
                dest.writeString(express_address);
                dest.writeInt(status);
                dest.writeInt(calc_knot_rebate);
                dest.writeInt(has_knot_rebate);
                dest.writeString(rebate_remark);
                dest.writeInt(is_deleted);
                dest.writeString(create_at);
            }

            public static class GoodsBean {
                /**
                 * id : 14
                 * mid : 1
                 * order_no : 648150929255
                 * goods_id : 1
                 * goods_title : test1
                 * goods_logo : http://hao.xd.cuci.cc/upload/decb0fe26fa3f486/b3f6521bf29403c8.png
                 * goods_spec : 默认分组::默认规格
                 * price_real : 0.00
                 * price_selling : 188.00
                 * price_market : 200.00
                 * price_member : 0.00
                 * price_express : 0.00
                 * price_service : 0.00
                 * agent_auth : 0
                 * agent_knot : 0
                 * number : 1
                 * create_at : 2019-01-22 17:55:13
                 */

                private int id;
                private int mid;
                private long order_no;
                private int goods_id;
                private String goods_title;
                private String goods_logo;
                private String goods_spec;
                private String price_real;
                private String price_selling;
                private String price_market;
                private String price_member;
                private String price_express;
                private String price_service;
                private int agent_auth;
                private int agent_knot;
                private int number;
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

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
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

                public String getPrice_real() {
                    return price_real;
                }

                public void setPrice_real(String price_real) {
                    this.price_real = price_real;
                }

                public String getPrice_selling() {
                    return price_selling;
                }

                public void setPrice_selling(String price_selling) {
                    this.price_selling = price_selling;
                }

                public String getPrice_market() {
                    return price_market;
                }

                public void setPrice_market(String price_market) {
                    this.price_market = price_market;
                }

                public String getPrice_member() {
                    return price_member;
                }

                public void setPrice_member(String price_member) {
                    this.price_member = price_member;
                }

                public String getPrice_express() {
                    return price_express;
                }

                public void setPrice_express(String price_express) {
                    this.price_express = price_express;
                }

                public String getPrice_service() {
                    return price_service;
                }

                public void setPrice_service(String price_service) {
                    this.price_service = price_service;
                }

                public int getAgent_auth() {
                    return agent_auth;
                }

                public void setAgent_auth(int agent_auth) {
                    this.agent_auth = agent_auth;
                }

                public int getAgent_knot() {
                    return agent_knot;
                }

                public void setAgent_knot(int agent_knot) {
                    this.agent_knot = agent_knot;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
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

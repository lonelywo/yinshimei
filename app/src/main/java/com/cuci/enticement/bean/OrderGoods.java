package com.cuci.enticement.bean;

import java.io.Serializable;

public class OrderGoods implements Serializable {
     /*  [{"id":41186,"mid":18281,"type":1,"order_no":669079056302,
     "goods_id":6672579002,"goods_title":"新零售礼包A方案",
     "goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg",
     "goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M",
     "price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00",
     "price_express":"0.00","price_service":"0.00","discount_price":"0.00",
     "discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,
     "vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-21 23:17:36"}]
            */
    private int cart_id;
    private int mid;
    private String goods_id;
    private String goods_title;
    private String goods_logo;
    private String goods_spec;
    private String goods_price_selling;
    private String goods_price_market;
    private int goods_num;
    private int stock;



    private int id;

    private int type;
    private long order_no;


    private String price_real;

    public String getPrice_sales() {
        return price_sales;
    }

    public void setPrice_sales(String price_sales) {
        this.price_sales = price_sales;
    }

    private String price_sales;
    private String price_selling;
    private String price_market;
    private String price_express;
    private String price_service;
    private String discount_price;
    private String discount_desc;
    private int number_limit;
    private int number_express;
    private int vip_mod;
    public int getIs_join() {
        return is_join;
    }

    public void setIs_join(int is_join) {
        this.is_join = is_join;
    }

    private int is_join;

    public String getIs_refund() {
        return is_refund;
    }

    public void setIs_refund(String is_refund) {
        this.is_refund = is_refund;
    }

    private String is_refund;


    private int vip_month;
    private String vip_discount;
    private int number;
    private String create_at;

    private boolean check;


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }



    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
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

    public String getGoods_price_selling() {
        return goods_price_selling;
    }

    public void setGoods_price_selling(String goods_price_selling) {
        this.goods_price_selling = goods_price_selling;
    }

    public String getGoods_price_market() {
        return goods_price_market;
    }

    public void setGoods_price_market(String goods_price_market) {
        this.goods_price_market = goods_price_market;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

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

    public long getOrder_no() {
        return order_no;
    }

    public void setOrder_no(long order_no) {
        this.order_no = order_no;
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

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getDiscount_desc() {
        return discount_desc;
    }

    public void setDiscount_desc(String discount_desc) {
        this.discount_desc = discount_desc;
    }

    public int getNumber_limit() {
        return number_limit;
    }

    public void setNumber_limit(int number_limit) {
        this.number_limit = number_limit;
    }

    public int getNumber_express() {
        return number_express;
    }

    public void setNumber_express(int number_express) {
        this.number_express = number_express;
    }

    public int getVip_mod() {
        return vip_mod;
    }

    public void setVip_mod(int vip_mod) {
        this.vip_mod = vip_mod;
    }

    public int getVip_month() {
        return vip_month;
    }

    public void setVip_month(int vip_month) {
        this.vip_month = vip_month;
    }

    public String getVip_discount() {
        return vip_discount;
    }

    public void setVip_discount(String vip_discount) {
        this.vip_discount = vip_discount;
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

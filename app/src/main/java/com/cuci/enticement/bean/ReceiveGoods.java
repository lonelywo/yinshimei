package com.cuci.enticement.bean;


public class ReceiveGoods  {

    /**
     * aid : 36
     * price_selling : 0.00
     * number : 1
     * goods_title : 49.9活动领取-原生水
     * goods_id : 6765798002
     * goods_logo : http://qiniu.cdn.enticementchina.com/b2df297b033e86b7/080201611ed0959e.jpg
     * order_no : 67709364487
     */

    private int aid;
    private String price_selling;
    private int number;
    private String goods_title;
    private long goods_id;
    private String goods_logo;
    private long order_no;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getPrice_selling() {
        return price_selling;
    }

    public void setPrice_selling(String price_selling) {
        this.price_selling = price_selling;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGoods_title() {
        return goods_title;
    }

    public void setGoods_title(String goods_title) {
        this.goods_title = goods_title;
    }

    public long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(long goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_logo() {
        return goods_logo;
    }

    public void setGoods_logo(String goods_logo) {
        this.goods_logo = goods_logo;
    }

    public long getOrder_no() {
        return order_no;
    }

    public void setOrder_no(long order_no) {
        this.order_no = order_no;
    }
}

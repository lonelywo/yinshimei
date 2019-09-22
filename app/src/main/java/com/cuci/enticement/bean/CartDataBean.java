package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class CartDataBean implements Serializable {

    /**
     * page : {"limit":12,"total":2,"pages":1,"current":1}
     * list : [{"cart_id":101,"mid":18281,"goods_id":6672588222,"goods_title":"新零售礼包B方案","goods_logo":"http://qiniu.cdn.enticementchina.com/ce62bb5fa9fdace8/f71348a7db4928df.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*40，温柔高保湿洁面乳(30g)*25，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","goods_price_selling":"3660.00","goods_price_market":"3660.00","goods_num":1,"stock":9928},{"cart_id":102,"mid":18281,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","goods_price_selling":"3660.00","goods_price_market":"3660.00","goods_num":1,"stock":9968}]
     * total : {"total_price":"7320.00"}
     */

    private PageBean page;
    private TotalBean total;
    private List<OrderGoods> list;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public List<OrderGoods> getList() {
        return list;
    }

    public void setList(List<OrderGoods> list) {
        this.list = list;
    }

    public static class PageBean {
        /**
         * limit : 12
         * total : 2
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

    public static class TotalBean {
        /**
         * total_price : 7320.00
         */

        private String total_price;

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }
    }





/*    public static class ListBean {
        *//**
         * cart_id : 101
         * mid : 18281
         * goods_id : 6672588222
         * goods_title : 新零售礼包B方案
         * goods_logo : http://qiniu.cdn.enticementchina.com/ce62bb5fa9fdace8/f71348a7db4928df.jpg
         * goods_spec : 默认分组:黑金魅惑蕾丝面膜5片/盒*40，温柔高保湿洁面乳(30g)*25，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M
         * goods_price_selling : 3660.00
         * goods_price_market : 3660.00
         * goods_num : 1
         * stock : 9928
         *//*

        private int cart_id;
        private int mid;
        private long goods_id;
        private String goods_title;
        private String goods_logo;
        private String goods_spec;
        private String goods_price_selling;
        private String goods_price_market;
        private int goods_num;
        private int stock;

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
    }*/


}

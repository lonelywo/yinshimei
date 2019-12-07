package com.cuci.enticement.bean;

import java.util.List;

public class HomeDetailsBean {


    /**
     * code : 1
     * info : 获取商品信息成功！
     * data : {"id":4,"title":"这也是普通商品","logo":"https://ssl.cdn.cuci.cc/0d0d68399f244efd/033e4f7ce3cdfae2.png","cate_id":1,"image":["https://hao.xd.cuci.cc/upload/282c197000cdc36d/d330b0798933405a.jpg","https://ssl.cdn.cuci.cc/a3789bd6b80d2ffb/cee6e9204d2a406c.jpg"],"initial_price_market":"160.00","initial_price_selling":"170.00","number_sales":5,"number_stock":410,"vip_mod":0,"content":"<p><img border=\"0\" src=\"https://ssl.cdn.cuci.cc/a3789bd6b80d2ffb/cee6e9204d2a406c.jpg\" style=\"max-width:100%\" title=\"image\" /><\/p>\r\n","specs":[{"name":"颜色","list":[{"name":"白色","check":true},{"name":"黑色","check":true}]},{"name":"尺寸","list":[{"name":"大","check":true},{"name":"中","check":true},{"name":"小","check":true}]}],"lists":[[{"name":"白色","check":true,"group":"颜色","span":3,"show":true,"key":"颜色::白色;;尺寸::大","virtual":19,"market":"22.00","selling":"11.00","status":true},{"name":"大","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"白色","check":true,"group":"颜色","span":2,"show":false,"key":"颜色::白色;;尺寸::中","virtual":19,"market":"33.00","selling":"12.00","status":true},{"name":"中","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"白色","check":true,"group":"颜色","span":1,"show":false,"key":"颜色::白色;;尺寸::小","virtual":19,"market":"44.00","selling":"13.00","status":true},{"name":"小","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"黑色","check":true,"group":"颜色","span":3,"show":true,"key":"颜色::黑色;;尺寸::大","virtual":19,"market":"55.00","selling":"10.00","status":true},{"name":"大","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"黑色","check":true,"group":"颜色","span":2,"show":false,"key":"颜色::黑色;;尺寸::中","virtual":19,"market":"66.00","selling":"17.00","status":true},{"name":"中","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"黑色","check":true,"group":"颜色","span":1,"show":false,"key":"颜色::黑色;;尺寸::小","virtual":19,"market":"88.00","selling":"9.00","status":false},{"name":"小","check":true,"group":"尺寸","span":1,"show":true}]],"list":[{"id":5,"goods_id":4,"goods_spec":"颜色::白色;;尺寸::大","price_market":"22.00","price_selling":"11.00","number_sales":2,"number_stock":100,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:36"},{"id":6,"goods_id":4,"goods_spec":"颜色::白色;;尺寸::中","price_market":"33.00","price_selling":"12.00","number_sales":1,"number_stock":110,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:37"},{"id":7,"goods_id":4,"goods_spec":"颜色::白色;;尺寸::小","price_market":"44.00","price_selling":"13.00","number_sales":1,"number_stock":100,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:37"},{"id":8,"goods_id":4,"goods_spec":"颜色::黑色;;尺寸::大","price_market":"55.00","price_selling":"10.00","number_sales":0,"number_stock":100,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:37"},{"id":9,"goods_id":4,"goods_spec":"颜色::黑色;;尺寸::中","price_market":"66.00","price_selling":"17.00","number_sales":0,"number_stock":0,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:37"},{"id":10,"goods_id":4,"goods_spec":"颜色::黑色;;尺寸::小","price_market":"88.00","price_selling":"9.00","number_sales":0,"number_stock":0,"number_virtual":19,"status":0,"create_at":"2019-01-22 17:45:37"}]}
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
         * id : 4
         * title : 这也是普通商品
         * logo : https://ssl.cdn.cuci.cc/0d0d68399f244efd/033e4f7ce3cdfae2.png
         * cate_id : 1
         * image : ["https://hao.xd.cuci.cc/upload/282c197000cdc36d/d330b0798933405a.jpg","https://ssl.cdn.cuci.cc/a3789bd6b80d2ffb/cee6e9204d2a406c.jpg"]
         * initial_price_market : 160.00
         * initial_price_selling : 170.00
         * number_sales : 5
         * number_stock : 410
         * vip_mod : 0
         * content : <p><img border="0" src="https://ssl.cdn.cuci.cc/a3789bd6b80d2ffb/cee6e9204d2a406c.jpg" style="max-width:100%" title="image" /></p>
         * specs : [{"name":"颜色","list":[{"name":"白色","check":true},{"name":"黑色","check":true}]},{"name":"尺寸","list":[{"name":"大","check":true},{"name":"中","check":true},{"name":"小","check":true}]}]
         * lists : [[{"name":"白色","check":true,"group":"颜色","span":3,"show":true,"key":"颜色::白色;;尺寸::大","virtual":19,"market":"22.00","selling":"11.00","status":true},{"name":"大","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"白色","check":true,"group":"颜色","span":2,"show":false,"key":"颜色::白色;;尺寸::中","virtual":19,"market":"33.00","selling":"12.00","status":true},{"name":"中","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"白色","check":true,"group":"颜色","span":1,"show":false,"key":"颜色::白色;;尺寸::小","virtual":19,"market":"44.00","selling":"13.00","status":true},{"name":"小","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"黑色","check":true,"group":"颜色","span":3,"show":true,"key":"颜色::黑色;;尺寸::大","virtual":19,"market":"55.00","selling":"10.00","status":true},{"name":"大","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"黑色","check":true,"group":"颜色","span":2,"show":false,"key":"颜色::黑色;;尺寸::中","virtual":19,"market":"66.00","selling":"17.00","status":true},{"name":"中","check":true,"group":"尺寸","span":1,"show":true}],[{"name":"黑色","check":true,"group":"颜色","span":1,"show":false,"key":"颜色::黑色;;尺寸::小","virtual":19,"market":"88.00","selling":"9.00","status":false},{"name":"小","check":true,"group":"尺寸","span":1,"show":true}]]
         * list : [{"id":5,"goods_id":4,"goods_spec":"颜色::白色;;尺寸::大","price_market":"22.00","price_selling":"11.00","number_sales":2,"number_stock":100,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:36"},{"id":6,"goods_id":4,"goods_spec":"颜色::白色;;尺寸::中","price_market":"33.00","price_selling":"12.00","number_sales":1,"number_stock":110,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:37"},{"id":7,"goods_id":4,"goods_spec":"颜色::白色;;尺寸::小","price_market":"44.00","price_selling":"13.00","number_sales":1,"number_stock":100,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:37"},{"id":8,"goods_id":4,"goods_spec":"颜色::黑色;;尺寸::大","price_market":"55.00","price_selling":"10.00","number_sales":0,"number_stock":100,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:37"},{"id":9,"goods_id":4,"goods_spec":"颜色::黑色;;尺寸::中","price_market":"66.00","price_selling":"17.00","number_sales":0,"number_stock":0,"number_virtual":19,"status":1,"create_at":"2019-01-22 17:45:37"},{"id":10,"goods_id":4,"goods_spec":"颜色::黑色;;尺寸::小","price_market":"88.00","price_selling":"9.00","number_sales":0,"number_stock":0,"number_virtual":19,"status":0,"create_at":"2019-01-22 17:45:37"}]
         */

        private long id;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        private int status;
        private String title;
        private String logo;
        private int cate_id;
        private String initial_price_market;
        private String initial_price_selling;

        public String getPricename() {
            return pricename;
        }

        public void setPricename(String pricename) {
            this.pricename = pricename;
        }

        private String pricename;
        private int number_sales;
        private int number_stock;
        private int vip_mod;
        private String content;
        private List<String> image;
        private List<SpecsBean> specs;
        private List<List<ListsBean>> lists;
        private List<ListBeanX> list;

        public long getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getCate_id() {
            return cate_id;
        }

        public void setCate_id(int cate_id) {
            this.cate_id = cate_id;
        }

        public String getInitial_price_market() {
            return initial_price_market;
        }

        public void setInitial_price_market(String initial_price_market) {
            this.initial_price_market = initial_price_market;
        }

        public String getInitial_price_selling() {
            return initial_price_selling;
        }

        public void setInitial_price_selling(String initial_price_selling) {
            this.initial_price_selling = initial_price_selling;
        }

        public int getNumber_sales() {
            return number_sales;
        }

        public void setNumber_sales(int number_sales) {
            this.number_sales = number_sales;
        }

        public int getNumber_stock() {
            return number_stock;
        }

        public void setNumber_stock(int number_stock) {
            this.number_stock = number_stock;
        }

        public int getVip_mod() {
            return vip_mod;
        }

        public void setVip_mod(int vip_mod) {
            this.vip_mod = vip_mod;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }

        public List<SpecsBean> getSpecs() {
            return specs;
        }

        public void setSpecs(List<SpecsBean> specs) {
            this.specs = specs;
        }

        public List<List<ListsBean>> getLists() {
            return lists;
        }

        public void setLists(List<List<ListsBean>> lists) {
            this.lists = lists;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class SpecsBean {
            /**
             * name : 颜色
             * list : [{"name":"白色","check":true},{"name":"黑色","check":true}]
             */

            private String name;
            private List<ListBean> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * name : 白色
                 * check : true
                 */

                private String name;
                private boolean check;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isCheck() {
                    return check;
                }

                public void setCheck(boolean check) {
                    this.check = check;
                }
            }
        }

        public static class ListsBean {
            /**
             * name : 白色
             * check : true
             * group : 颜色
             * span : 3
             * show : true
             * key : 颜色::白色;;尺寸::大
             * virtual : 19
             * market : 22.00
             * selling : 11.00
             * status : true
             */

            private String name;
            private boolean check;
            private String group;
            private int span;
            private boolean show;
            private String key;
            private int virtual;
            private String market;
            private String selling;
            private boolean status;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

            public String getGroup() {
                return group;
            }

            public void setGroup(String group) {
                this.group = group;
            }

            public int getSpan() {
                return span;
            }

            public void setSpan(int span) {
                this.span = span;
            }

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public int getVirtual() {
                return virtual;
            }

            public void setVirtual(int virtual) {
                this.virtual = virtual;
            }

            public String getMarket() {
                return market;
            }

            public void setMarket(String market) {
                this.market = market;
            }

            public String getSelling() {
                return selling;
            }

            public void setSelling(String selling) {
                this.selling = selling;
            }

            public boolean isStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
            }
        }

        public static class ListBeanX {
            /**
             * id : 5
             * goods_id : 4
             * goods_spec : 颜色::白色;;尺寸::大
             * price_market : 22.00
             * price_selling : 11.00
             * number_sales : 2
             * number_stock : 100
             * number_virtual : 19
             * status : 1
             * create_at : 2019-01-22 17:45:36
             */

            private long id;
            private long goods_id;
            private String goods_spec;
            private String price_market;
            private String price_selling;
            private int number_sales;
            private int number_stock;
            private int number_virtual;
            private int status;
            private String create_at;

            public long getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_spec() {
                return goods_spec;
            }

            public void setGoods_spec(String goods_spec) {
                this.goods_spec = goods_spec;
            }

            public String getPrice_market() {
                return price_market;
            }

            public void setPrice_market(String price_market) {
                this.price_market = price_market;
            }

            public String getPrice_selling() {
                return price_selling;
            }

            public void setPrice_selling(String price_selling) {
                this.price_selling = price_selling;
            }

            public int getNumber_sales() {
                return number_sales;
            }

            public void setNumber_sales(int number_sales) {
                this.number_sales = number_sales;
            }

            public int getNumber_stock() {
                return number_stock;
            }

            public void setNumber_stock(int number_stock) {
                this.number_stock = number_stock;
            }

            public int getNumber_virtual() {
                return number_virtual;
            }

            public void setNumber_virtual(int number_virtual) {
                this.number_virtual = number_virtual;
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

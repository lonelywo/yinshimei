package com.cuci.enticement.bean;


import java.io.Serializable;
import java.util.List;

public class Version implements Serializable {


    /**
     * code : 1
     * info : 版本信息获取成功！
     * data : {"version":"1.9.0","versionName":190,"url":"http://web.enticementchina.com/download.php","content":"1.9.0版本","is_force":0,"full":{"openfulle":1,"fullinfo":[{"itme":0,"amount":280,"quota":0,"desc":"满280元 赠送橙花精油护手霜×2支","goodslist":[{"goods_id":6732231601,"goods_title":"橙花精油护手霜","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":2}]},{"itme":0,"amount":500,"quota":0,"desc":"满500元 送橙花精油护手霜×2支+温柔化妆包×1个","goodslist":[{"goods_id":6732231601,"goods_title":"橙花精油护手霜","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":2},{"goods_id":6713074712,"goods_title":"温柔化妆包","goods_logo":"https://qiniu.cdn.enticementchina.com/e7a603fc9d59b031/c795cdb19e7e67d4.jpg","number":1}]},{"itme":0,"amount":1200,"quota":0,"desc":"满500元 送黑金臻丝睡袍×1件（限前80名）","goodslist":[{"goods_id":6755307649,"goods_title":"黑金臻丝睡袍","goods_logo":"https://qiniu.cdn.enticementchina.com/c92159895065084b/5d055f81d3c7ced7.png","number":1}]},{"itme":0,"amount":3000,"quota":0,"desc":"满3000元 送¥1680黑金凡盒×1盒（限前30名）","goodslist":[{"goods_id":6732231601,"goods_title":"黑金凡盒","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":1}]}]}}
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
         * version : 1.9.0
         * versionName : 190
         * url : http://web.enticementchina.com/download.php
         * content : 1.9.0版本
         * is_force : 0
         * full : {"openfulle":1,"fullinfo":[{"itme":0,"amount":280,"quota":0,"desc":"满280元 赠送橙花精油护手霜×2支","goodslist":[{"goods_id":6732231601,"goods_title":"橙花精油护手霜","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":2}]},{"itme":0,"amount":500,"quota":0,"desc":"满500元 送橙花精油护手霜×2支+温柔化妆包×1个","goodslist":[{"goods_id":6732231601,"goods_title":"橙花精油护手霜","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":2},{"goods_id":6713074712,"goods_title":"温柔化妆包","goods_logo":"https://qiniu.cdn.enticementchina.com/e7a603fc9d59b031/c795cdb19e7e67d4.jpg","number":1}]},{"itme":0,"amount":1200,"quota":0,"desc":"满500元 送黑金臻丝睡袍×1件（限前80名）","goodslist":[{"goods_id":6755307649,"goods_title":"黑金臻丝睡袍","goods_logo":"https://qiniu.cdn.enticementchina.com/c92159895065084b/5d055f81d3c7ced7.png","number":1}]},{"itme":0,"amount":3000,"quota":0,"desc":"满3000元 送¥1680黑金凡盒×1盒（限前30名）","goodslist":[{"goods_id":6732231601,"goods_title":"黑金凡盒","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":1}]}]}
         */

        private String version;
        private int versionName;
        private String url;
        private String content;
        private int is_force;
        private FullBean full;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVersionName() {
            return versionName;
        }

        public void setVersionName(int versionName) {
            this.versionName = versionName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIs_force() {
            return is_force;
        }

        public void setIs_force(int is_force) {
            this.is_force = is_force;
        }

        public FullBean getFull() {
            return full;
        }

        public void setFull(FullBean full) {
            this.full = full;
        }

        public static class FullBean {
            /**
             * openfulle : 1
             * fullinfo : [{"itme":0,"amount":280,"quota":0,"desc":"满280元 赠送橙花精油护手霜×2支","goodslist":[{"goods_id":6732231601,"goods_title":"橙花精油护手霜","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":2}]},{"itme":0,"amount":500,"quota":0,"desc":"满500元 送橙花精油护手霜×2支+温柔化妆包×1个","goodslist":[{"goods_id":6732231601,"goods_title":"橙花精油护手霜","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":2},{"goods_id":6713074712,"goods_title":"温柔化妆包","goods_logo":"https://qiniu.cdn.enticementchina.com/e7a603fc9d59b031/c795cdb19e7e67d4.jpg","number":1}]},{"itme":0,"amount":1200,"quota":0,"desc":"满500元 送黑金臻丝睡袍×1件（限前80名）","goodslist":[{"goods_id":6755307649,"goods_title":"黑金臻丝睡袍","goods_logo":"https://qiniu.cdn.enticementchina.com/c92159895065084b/5d055f81d3c7ced7.png","number":1}]},{"itme":0,"amount":3000,"quota":0,"desc":"满3000元 送¥1680黑金凡盒×1盒（限前30名）","goodslist":[{"goods_id":6732231601,"goods_title":"黑金凡盒","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":1}]}]
             */

            private int openfulle;
            private List<FullinfoBean> fullinfo;

            public int getOpenfulle() {
                return openfulle;
            }

            public void setOpenfulle(int openfulle) {
                this.openfulle = openfulle;
            }

            public List<FullinfoBean> getFullinfo() {
                return fullinfo;
            }

            public void setFullinfo(List<FullinfoBean> fullinfo) {
                this.fullinfo = fullinfo;
            }

            public static class FullinfoBean {
                /**
                 * itme : 0
                 * amount : 280
                 * quota : 0
                 * desc : 满280元 赠送橙花精油护手霜×2支
                 * goodslist : [{"goods_id":6732231601,"goods_title":"橙花精油护手霜","goods_logo":"https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg","number":2}]
                 */

                private int itme;
                private double amount;
                private int quota;
                private String desc;

                public String getAppmargin() {
                    return appmargin;
                }

                public void setAppmargin(String appmargin) {
                    this.appmargin = appmargin;
                }

                private String appmargin;
                private List<GoodslistBean> goodslist;

                public int getItme() {
                    return itme;
                }

                public void setItme(int itme) {
                    this.itme = itme;
                }

                public double getAmount() {
                    return amount;
                }

                public void setAmount(double amount) {
                    this.amount = amount;
                }

                public int getQuota() {
                    return quota;
                }

                public void setQuota(int quota) {
                    this.quota = quota;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public List<GoodslistBean> getGoodslist() {
                    return goodslist;
                }

                public void setGoodslist(List<GoodslistBean> goodslist) {
                    this.goodslist = goodslist;
                }

                public static class GoodslistBean {
                    /**
                     * goods_id : 6732231601
                     * goods_title : 橙花精油护手霜
                     * goods_logo : https://qiniu.cdn.enticementchina.com/2bbe2594164c1be8/d49f15143dfbb01a.jpg
                     * number : 2
                     */

                    private long goods_id;
                    private String goods_title;
                    private String goods_logo;
                    private int number;

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

                    public int getNumber() {
                        return number;
                    }

                    public void setNumber(int number) {
                        this.number = number;
                    }
                }
            }
        }
    }
}

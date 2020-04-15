package com.cuci.enticement.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QyandYHJBean implements Serializable {


    /**
     * code : 1
     * info : ok
     * data : {"coupon_show":1,"group_show":1,"coupon":[{"alias":"userlevel","imgs":"https://qiniu.cdn.enticementchina.com/09d10619edb99ae4/fe6bc8cd674f1ccd.png","link":"https://mp.weixin.qq.com/s/iMKtunXNA-HXpl0r44JOvQ","vip_level":1},{"alias":"userlevelno","imgs":"https://qiniu.cdn.enticementchina.com/be0f220cd670264d/65f96d7a1643605b.png","link":"http://www.enticementchina.com/","vip_level":0}],"groupbuy":{"alias":"groupbuy","imgs":"https://qiniu.cdn.enticementchina.com/b4b1b0eac651ffd2/18d7e3b963d02c7f.png","link":"http://web.enticementchina.com/appweb/fan_description.html"}}
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
         * coupon_show : 1
         * group_show : 1
         * coupon : [{"alias":"userlevel","imgs":"https://qiniu.cdn.enticementchina.com/09d10619edb99ae4/fe6bc8cd674f1ccd.png","link":"https://mp.weixin.qq.com/s/iMKtunXNA-HXpl0r44JOvQ","vip_level":1},{"alias":"userlevelno","imgs":"https://qiniu.cdn.enticementchina.com/be0f220cd670264d/65f96d7a1643605b.png","link":"http://www.enticementchina.com/","vip_level":0}]
         * groupbuy : {"alias":"groupbuy","imgs":"https://qiniu.cdn.enticementchina.com/b4b1b0eac651ffd2/18d7e3b963d02c7f.png","link":"http://web.enticementchina.com/appweb/fan_description.html"}
         */

        private int coupon_show;
        private int group_show;
        private GroupbuyBean groupbuy;
        private List<CouponBean> coupon;

        public List<ShareBean> getShare() {
            return share;
        }

        public void setShare(List<ShareBean> share) {
            this.share = share;
        }

        private List<ShareBean> share;
        public static class ShareBean {
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getJump_id() {
                return jump_id;
            }

            public void setJump_id(String jump_id) {
                this.jump_id = jump_id;
            }

            public String getImgs() {
                return imgs;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            /**
             * alias : userlevel
             * imgs : https://qiniu.cdn.enticementchina.com/09d10619edb99ae4/fe6bc8cd674f1ccd.png
             * link : https://mp.weixin.qq.com/s/iMKtunXNA-HXpl0r44JOvQ
             * vip_level : 1
             */
            private String type;
            private String jump_id;
            private String imgs;
            private String title;
        }
        public int getCoupon_show() {
            return coupon_show;
        }

        public void setCoupon_show(int coupon_show) {
            this.coupon_show = coupon_show;
        }

        public int getGroup_show() {
            return group_show;
        }

        public void setGroup_show(int group_show) {
            this.group_show = group_show;
        }

        public GroupbuyBean getGroupbuy() {
            return groupbuy;
        }

        public void setGroupbuy(GroupbuyBean groupbuy) {
            this.groupbuy = groupbuy;
        }

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public static class GroupbuyBean {
            /**
             * alias : groupbuy
             * imgs : https://qiniu.cdn.enticementchina.com/b4b1b0eac651ffd2/18d7e3b963d02c7f.png
             * link : http://web.enticementchina.com/appweb/fan_description.html
             */

            private String alias;
            private String imgs;
            private String link;

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getImgs() {
                return imgs;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }

        public static class CouponBean {
            /**
             * alias : userlevel
             * imgs : https://qiniu.cdn.enticementchina.com/09d10619edb99ae4/fe6bc8cd674f1ccd.png
             * link : https://mp.weixin.qq.com/s/iMKtunXNA-HXpl0r44JOvQ
             * vip_level : 1
             */

            private String alias;
            private String imgs;
            private String link;
            private int vip_level;

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getImgs() {
                return imgs;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getVip_level() {
                return vip_level;
            }

            public void setVip_level(int vip_level) {
                this.vip_level = vip_level;
            }
        }
    }
}

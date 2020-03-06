package com.cuci.enticement.bean;

import java.io.Serializable;

public class CouponBean implements Serializable {


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

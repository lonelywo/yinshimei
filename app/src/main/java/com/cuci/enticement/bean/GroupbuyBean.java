package com.cuci.enticement.bean;

import java.io.Serializable;

public class GroupbuyBean implements Serializable {


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

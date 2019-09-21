package com.cuci.enticement.plate.common.eventbus;

import com.cuci.enticement.bean.MallSourceBean;

import java.util.List;

public class MessageEvent {
    private List<MallSourceBean.DataBean.ListBean> items;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;
    public List<MallSourceBean.DataBean.ListBean> getItems() {
        return items;
    }

    public void setItems(List<MallSourceBean.DataBean.ListBean> items) {
        this.items = items;
    }


    public MessageEvent(List<MallSourceBean.DataBean.ListBean> items,int type) {
        this.items = items;
        this.type = type;
    }

}


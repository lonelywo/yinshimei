package com.cuci.enticement.plate.common.eventbus;

import com.cuci.enticement.bean.MallSourceBean;

import java.util.List;

public class MessageEvent1 {
    private String message;

    public MessageEvent1(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}


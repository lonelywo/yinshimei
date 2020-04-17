package com.cuci.enticement.plate.common.eventbus;

public class EssayEvent {
    private String id;


    public EssayEvent(String id) {
        this.id = id;
    }

    public String getCode() {
        return id;
    }

    public void setCode(String id) {
        this.id = id;
    }
}


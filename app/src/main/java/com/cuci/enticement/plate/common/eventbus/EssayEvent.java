package com.cuci.enticement.plate.common.eventbus;

public class EssayEvent {
    private int id;

    public static final int INTENT_Essay=100;
    public EssayEvent(int id) {
        this.id = id;
    }

    public int getCode() {
        return id;
    }

    public void setCode(int id) {
        this.id = id;
    }
}


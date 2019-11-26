package com.cuci.enticement.bean;

import java.io.Serializable;

public class PushBean implements Serializable {

    /**
     * type : 1
     * title : 201aasitle
     * content : 20152sessxt
     * badge : 0
     * time : 2019-11-26 17:49:06
     * id :
     */

    private int type;
    private String title;
    private String content;
    private int badge;
    private String time;
    private String id;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

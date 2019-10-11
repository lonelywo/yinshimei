package com.cuci.enticement.event;

public class PKEvent {
    private int code;

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String headimg;
    private String nickname;

    public static final int REFRESH_hx_LIST=102;
    public PKEvent(int code,String headimg,String nickname) {
        this.code = code;
        this.headimg = headimg;
        this.nickname = nickname;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}


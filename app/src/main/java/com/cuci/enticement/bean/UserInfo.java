package com.cuci.enticement.bean;



import java.io.Serializable;

public class UserInfo implements Serializable {


    /**
     * id : 18281.0
     * phone : 18588564260
     * nickname : 皮蛋君
     * headimg : https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqalst360wHqrqFJZbTMY4DSjd55iak7kspu6ic1bKKBUO3icQ7dlThwz5b6MlSwD9dfbmS5whwVicfRg/132
     * vip_level : 0.0
     * vip_auth : 0.0
     * token : bd9ef0241d02e98e6709fb249477d999
     */

    private int id;
    private String phone;
    private String nickname;
    private String headimg;
    private int vip_level;
    private int vip_auth;
    private String token;
    public String getBlack_state() {
        return black_state;
    }

    public void setBlack_state(String black_state) {
        this.black_state = black_state;
    }

    public String getIs_binding() {
        return is_binding;
    }

    public void setIs_binding(String is_binding) {
        this.is_binding = is_binding;
    }

    private String black_state;
    private String is_binding;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getVip_level() {
        return vip_level;
    }

    public void setVip_level(int vip_level) {
        this.vip_level = vip_level;
    }

    public int getVip_auth() {
        return vip_auth;
    }

    public void setVip_auth(int vip_auth) {
        this.vip_auth = vip_auth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

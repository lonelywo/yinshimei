package com.cuci.enticement.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataUserInfo implements Serializable {
    /**
     * code : 1
     * info : 获取会员资料成功！
     * data : {"id":20556,"bid":1,"pid":1,"ppid":0,"path":"-1-","path_initial":"-1-","teams":0,"teams_total":0,"daily_teams":0,"daily_teams_total":0,"total_amount":"0.00","unionid":"oWvYz1rvVOESFnDI71QKaleIkjzo","openid":"","headimg":"https://qiniu.cdn.enticementchina.com/headimg/57a48cf2fe686e92/6f3c982cb982c50f.jpeg","nickname":"皮蛋咯","phone":"18588564260","username":"","sex":"男","province":"北京市","city":"北京市","area":"东城区","amount_profit":"0.00","amount_used":"0.00","black_state":0,"vip_auth":0,"vip_level":0,"vip_date":null,"vip_desc":"","pay_at":null,"token":"7574db7c25e932e99a0d25a7e58f848b","token_time":1572358351,"create_at":"2019-10-10 18:57:17","is_bindingwx":1,"ordertotal":{"2":0,"3":0,"4":0,"5":0},"kf_status":"1"}
     */

    private int code;
    private String info;
    private UserInfo data;

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

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }


}

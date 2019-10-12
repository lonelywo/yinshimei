package com.cuci.enticement.bean;

public class ModifyInfo {


    /**
     * code : 1
     * info : 会员资料更新成功！
     * data : {"id":20556,"bid":1,"pid":1,"ppid":0,"path":"-1-","path_initial":"-1-","teams":0,"teams_total":0,"total_amount":"0.00","daily_teams":0,"unionid":"oWvYz1rvVOESFnDI71QKaleIkjzo","openid":"","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqOh0hCrjE0xuKRRsvQUuf6s9zplPKrI5S6gw7YtOajk5pAMD0Vbp3qTdN6UaNpiakJ9cG7ia0s7kBA/132","nickname":"皮蛋君","phone":"18588564260","username":"","sex":"","province":"","city":null,"area":"","amount_profit":"0.00","amount_used":"0.00","black_state":0,"vip_auth":0,"vip_level":0,"vip_date":null,"vip_desc":"","pay_at":null,"token":"dd685d23c99bccd055df7486083c70c8","token_time":1572162704,"create_at":"2019-10-10 18:57:17"}
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

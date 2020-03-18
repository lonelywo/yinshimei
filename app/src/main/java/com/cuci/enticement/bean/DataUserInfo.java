package com.cuci.enticement.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataUserInfo implements Serializable {


    /**
     * code : 1
     * info : 获取会员资料成功！
     * data : {"id":52202,"bid":48624,"pid":48624,"ppid":27868,"path":"-2-3-19982-27868-48624-","path_initial":"-2-3-19982-27868-48624-","teams":0,"teams_total":0,"daily_teams":0,"daily_teams_total":0,"total_amount":"49.90","unionid":"oWvYz1rvVOESFnDI71QKaleIkjzo","openid":"","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqOh0hCrjE0xuKRRsvQUuf6s9zplPKrI5S6gw7YtOajk5pAMD0Vbp3qTdN6UaNpiakJ9cG7ia0s7kBA/132","nickname":"皮蛋君","phone":"18588564260","username":"","sex":"男","province":"","city":"","area":"","amount_profit":"0.00","amount_used":"0.00","black_state":0,"vip_auth":1,"vip_level":1,"vip_date":"2019-12-16 02:06:18","vip_time":null,"vip_desc":"","is_new":1,"pay_at":"2019-12-16","sharepic":"","token":"2789c171a885e9e0ac493b0a780a0af0","token_time":1580481030,"create_at":"2019-12-16 02:02:50","is_bindingwx":1,"ordertotal":{"2":0,"3":0,"4":1,"5":0},"kf_status":"1","is_month":0,"daily_activity":1,"daily_activity_url":"http://web.enticementchina.com/update.html?akey=","menu":{"gift":0}}
     */

    private int code;
    private String info;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 52202
         * bid : 48624
         * pid : 48624
         * ppid : 27868
         * path : -2-3-19982-27868-48624-
         * path_initial : -2-3-19982-27868-48624-
         * teams : 0
         * teams_total : 0
         * daily_teams : 0
         * daily_teams_total : 0
         * total_amount : 49.90
         * unionid : oWvYz1rvVOESFnDI71QKaleIkjzo
         * openid :
         * headimg : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqOh0hCrjE0xuKRRsvQUuf6s9zplPKrI5S6gw7YtOajk5pAMD0Vbp3qTdN6UaNpiakJ9cG7ia0s7kBA/132
         * nickname : 皮蛋君
         * phone : 18588564260
         * username :
         * sex : 男
         * province :
         * city :
         * area :
         * amount_profit : 0.00
         * amount_used : 0.00
         * black_state : 0
         * vip_auth : 1
         * vip_level : 1
         * vip_date : 2019-12-16 02:06:18
         * vip_time : null
         * vip_desc :
         * is_new : 1
         * pay_at : 2019-12-16
         * sharepic :
         * token : 2789c171a885e9e0ac493b0a780a0af0
         * token_time : 1580481030
         * create_at : 2019-12-16 02:02:50
         * is_bindingwx : 1
         * ordertotal : {"2":0,"3":0,"4":1,"5":0}
         * kf_status : 1
         * is_month : 0
         * daily_activity : 1
         * daily_activity_url : http://web.enticementchina.com/update.html?akey=
         * menu : {"gift":0}
         */

        private int id;
        private int bid;
        private int pid;
        private int ppid;
        private String path;
        private String path_initial;
        private int teams;
        private int teams_total;
        private int daily_teams;
        private int daily_teams_total;
        private String total_amount;
        private String unionid;
        private String openid;
        private String headimg;
        private String nickname;
        private String phone;
        private String username;
        private String sex;
        private String province;
        private String city;
        private String area;
        private String amount_profit;
        private String amount_used;
        private int black_state;
        private int vip_auth;
        private int vip_level;
        private String vip_date;
        private Object vip_time;
        private String vip_desc;
        private int is_new;
        private String pay_at;
        private String sharepic;
        private String token;
        private int token_time;
        private String create_at;
        private int is_bindingwx;
        private OrdertotalBean ordertotal;
        private String kf_status;
        private int is_month;
        private int daily_activity;
        private String daily_activity_url;
        private MenuBean menu;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPath_initial() {
            return path_initial;
        }

        public void setPath_initial(String path_initial) {
            this.path_initial = path_initial;
        }

        public int getTeams() {
            return teams;
        }

        public void setTeams(int teams) {
            this.teams = teams;
        }

        public int getTeams_total() {
            return teams_total;
        }

        public void setTeams_total(int teams_total) {
            this.teams_total = teams_total;
        }

        public int getDaily_teams() {
            return daily_teams;
        }

        public void setDaily_teams(int daily_teams) {
            this.daily_teams = daily_teams;
        }

        public int getDaily_teams_total() {
            return daily_teams_total;
        }

        public void setDaily_teams_total(int daily_teams_total) {
            this.daily_teams_total = daily_teams_total;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAmount_profit() {
            return amount_profit;
        }

        public void setAmount_profit(String amount_profit) {
            this.amount_profit = amount_profit;
        }

        public String getAmount_used() {
            return amount_used;
        }

        public void setAmount_used(String amount_used) {
            this.amount_used = amount_used;
        }

        public int getBlack_state() {
            return black_state;
        }

        public void setBlack_state(int black_state) {
            this.black_state = black_state;
        }

        public int getVip_auth() {
            return vip_auth;
        }

        public void setVip_auth(int vip_auth) {
            this.vip_auth = vip_auth;
        }

        public int getVip_level() {
            return vip_level;
        }

        public void setVip_level(int vip_level) {
            this.vip_level = vip_level;
        }

        public String getVip_date() {
            return vip_date;
        }

        public void setVip_date(String vip_date) {
            this.vip_date = vip_date;
        }

        public Object getVip_time() {
            return vip_time;
        }

        public void setVip_time(Object vip_time) {
            this.vip_time = vip_time;
        }

        public String getVip_desc() {
            return vip_desc;
        }

        public void setVip_desc(String vip_desc) {
            this.vip_desc = vip_desc;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public String getPay_at() {
            return pay_at;
        }

        public void setPay_at(String pay_at) {
            this.pay_at = pay_at;
        }

        public String getSharepic() {
            return sharepic;
        }

        public void setSharepic(String sharepic) {
            this.sharepic = sharepic;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getToken_time() {
            return token_time;
        }

        public void setToken_time(int token_time) {
            this.token_time = token_time;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public int getIs_bindingwx() {
            return is_bindingwx;
        }

        public void setIs_bindingwx(int is_bindingwx) {
            this.is_bindingwx = is_bindingwx;
        }

        public OrdertotalBean getOrdertotal() {
            return ordertotal;
        }

        public void setOrdertotal(OrdertotalBean ordertotal) {
            this.ordertotal = ordertotal;
        }

        public String getKf_status() {
            return kf_status;
        }

        public void setKf_status(String kf_status) {
            this.kf_status = kf_status;
        }

        public int getIs_month() {
            return is_month;
        }

        public void setIs_month(int is_month) {
            this.is_month = is_month;
        }

        public int getDaily_activity() {
            return daily_activity;
        }

        public void setDaily_activity(int daily_activity) {
            this.daily_activity = daily_activity;
        }

        public String getDaily_activity_url() {
            return daily_activity_url;
        }

        public void setDaily_activity_url(String daily_activity_url) {
            this.daily_activity_url = daily_activity_url;
        }

        public MenuBean getMenu() {
            return menu;
        }

        public void setMenu(MenuBean menu) {
            this.menu = menu;
        }

        public static class OrdertotalBean implements Serializable{
            /**
             * 2 : 0
             * 3 : 0
             * 4 : 1
             * 5 : 0
             */

            @SerializedName("2")
            private int _$2;
            @SerializedName("3")
            private int _$3;
            @SerializedName("4")
            private int _$4;
            @SerializedName("5")
            private int _$5;

            public int get_$2() {
                return _$2;
            }

            public void set_$2(int _$2) {
                this._$2 = _$2;
            }

            public int get_$3() {
                return _$3;
            }

            public void set_$3(int _$3) {
                this._$3 = _$3;
            }

            public int get_$4() {
                return _$4;
            }

            public void set_$4(int _$4) {
                this._$4 = _$4;
            }

            public int get_$5() {
                return _$5;
            }

            public void set_$5(int _$5) {
                this._$5 = _$5;
            }
        }

        public static class MenuBean implements Serializable{
            /**
             * gift : 0
             */

            private int gift;

            public int getGift() {
                return gift;
            }

            public void setGift(int gift) {
                this.gift = gift;
            }
        }
    }
}

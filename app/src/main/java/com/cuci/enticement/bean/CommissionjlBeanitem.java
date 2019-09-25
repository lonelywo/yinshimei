package com.cuci.enticement.bean;

import java.io.Serializable;

public class CommissionjlBeanitem implements Serializable {

    /**
     * id : 14521
     * type : 1
     * mid : 1
     * order_no : 666890602901
     * order_mid : 41
     * order_price : 2750.00
     * profit_price : 275.00
     * desc : 直推10%
     * create_at : 2019-08-27 15:54:29
     * member : {"id":41,"bid":1,"pid":1,"ppid":0,"path":"-1-","path_initial":"-1-","teams":907,"teams_total":9685,"unionid":null,"openid":"oBMoo444M3lf1iyjjxRm7lwsiKh0","headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132","nickname":"凯婷","phone":"18819814949","username":"","amount_profit":"377083.20","amount_used":"370725.00","black_state":0,"vip_auth":1,"vip_level":2,"vip_date":"2019-04-11","vip_desc":"","pay_at":"2019-09-11","token":"aaa","token_time":0,"create_at":"2019-03-18 14:56:14"}
     */

    private int id;
    private int type;
    private int mid;
    private long order_no;
    private int order_mid;
    private String order_price;
    private String profit_price;
    private String desc;
    private String create_at;
    private MemberBean member;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public long getOrder_no() {
        return order_no;
    }

    public void setOrder_no(long order_no) {
        this.order_no = order_no;
    }

    public int getOrder_mid() {
        return order_mid;
    }

    public void setOrder_mid(int order_mid) {
        this.order_mid = order_mid;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getProfit_price() {
        return profit_price;
    }

    public void setProfit_price(String profit_price) {
        this.profit_price = profit_price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public static class MemberBean {
        /**
         * id : 41
         * bid : 1
         * pid : 1
         * ppid : 0
         * path : -1-
         * path_initial : -1-
         * teams : 907
         * teams_total : 9685
         * unionid : null
         * openid : oBMoo444M3lf1iyjjxRm7lwsiKh0
         * headimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132
         * nickname : 凯婷
         * phone : 18819814949
         * username :
         * amount_profit : 377083.20
         * amount_used : 370725.00
         * black_state : 0
         * vip_auth : 1
         * vip_level : 2
         * vip_date : 2019-04-11
         * vip_desc :
         * pay_at : 2019-09-11
         * token : aaa
         * token_time : 0
         * create_at : 2019-03-18 14:56:14
         */

        private int id;
        private int bid;
        private int pid;
        private int ppid;
        private String path;
        private String path_initial;
        private int teams;
        private int teams_total;
        private Object unionid;
        private String openid;
        private String headimg;
        private String nickname;
        private String phone;
        private String username;
        private String amount_profit;
        private String amount_used;
        private int black_state;
        private int vip_auth;
        private int vip_level;
        private String vip_date;
        private String vip_desc;
        private String pay_at;
        private String token;
        private int token_time;
        private String create_at;

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

        public Object getUnionid() {
            return unionid;
        }

        public void setUnionid(Object unionid) {
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

        public String getVip_desc() {
            return vip_desc;
        }

        public void setVip_desc(String vip_desc) {
            this.vip_desc = vip_desc;
        }

        public String getPay_at() {
            return pay_at;
        }

        public void setPay_at(String pay_at) {
            this.pay_at = pay_at;
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
    }
}

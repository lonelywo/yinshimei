package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class CommissionjlBean implements Serializable {


    /**
     * code : 1
     * info : 获取拥金记录成功！
     * data : {"page":{"limit":20,"total":1,"pages":1,"current":1},"list":[{"id":245997,"type":1,"mid":21045,"order_no":676843186840,"order_mid":47407,"order_price":"240.00","profit_price":"72.00","is_refund":1,"desc":"","create_at":"2019-12-20 20:01:17","member":{"id":47407,"bid":21045,"pid":21045,"ppid":3,"path":"-2-3-21045-","path_initial":"-2-3-6-","teams":0,"teams_total":0,"daily_teams":0,"daily_teams_total":0,"total_amount":"240.00","unionid":null,"openid":"","headimg":"","nickname":"YSM_T_80029","phone":"18000000029","username":"","sex":"","province":"","city":"","area":"","amount_profit":"0.00","amount_used":"0.00","black_state":0,"vip_auth":1,"vip_level":1,"vip_date":"2019-12-20 20:01:05","vip_time":null,"vip_desc":"","is_new":1,"pay_at":"2019-12-20","sharepic":"","token":"","token_time":0,"create_at":"2019-12-12 16:16:45"}}]}
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

    public static class DataBean {
        /**
         * page : {"limit":20,"total":1,"pages":1,"current":1}
         * list : [{"id":245997,"type":1,"mid":21045,"order_no":676843186840,"order_mid":47407,"order_price":"240.00","profit_price":"72.00","is_refund":1,"desc":"","create_at":"2019-12-20 20:01:17","member":{"id":47407,"bid":21045,"pid":21045,"ppid":3,"path":"-2-3-21045-","path_initial":"-2-3-6-","teams":0,"teams_total":0,"daily_teams":0,"daily_teams_total":0,"total_amount":"240.00","unionid":null,"openid":"","headimg":"","nickname":"YSM_T_80029","phone":"18000000029","username":"","sex":"","province":"","city":"","area":"","amount_profit":"0.00","amount_used":"0.00","black_state":0,"vip_auth":1,"vip_level":1,"vip_date":"2019-12-20 20:01:05","vip_time":null,"vip_desc":"","is_new":1,"pay_at":"2019-12-20","sharepic":"","token":"","token_time":0,"create_at":"2019-12-12 16:16:45"}}]
         */

        private PageBean page;
        private List<ListBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageBean {
            /**
             * limit : 20
             * total : 1
             * pages : 1
             * current : 1
             */

            private int limit;
            private int total;
            private int pages;
            private int current;

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getCurrent() {
                return current;
            }

            public void setCurrent(int current) {
                this.current = current;
            }
        }

        public static class ListBean {
            /**
             * id : 245997
             * type : 1
             * mid : 21045
             * order_no : 676843186840
             * order_mid : 47407
             * order_price : 240.00
             * profit_price : 72.00
             * is_refund : 1
             * desc :
             * create_at : 2019-12-20 20:01:17
             * member : {"id":47407,"bid":21045,"pid":21045,"ppid":3,"path":"-2-3-21045-","path_initial":"-2-3-6-","teams":0,"teams_total":0,"daily_teams":0,"daily_teams_total":0,"total_amount":"240.00","unionid":null,"openid":"","headimg":"","nickname":"YSM_T_80029","phone":"18000000029","username":"","sex":"","province":"","city":"","area":"","amount_profit":"0.00","amount_used":"0.00","black_state":0,"vip_auth":1,"vip_level":1,"vip_date":"2019-12-20 20:01:05","vip_time":null,"vip_desc":"","is_new":1,"pay_at":"2019-12-20","sharepic":"","token":"","token_time":0,"create_at":"2019-12-12 16:16:45"}
             */

            private int id;
            private int type;
            private int mid;
            private long order_no;
            private int order_mid;
            private String order_price;
            private String profit_price;
            private int is_refund;
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

            public int getIs_refund() {
                return is_refund;
            }

            public void setIs_refund(int is_refund) {
                this.is_refund = is_refund;
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
                 * id : 47407
                 * bid : 21045
                 * pid : 21045
                 * ppid : 3
                 * path : -2-3-21045-
                 * path_initial : -2-3-6-
                 * teams : 0
                 * teams_total : 0
                 * daily_teams : 0
                 * daily_teams_total : 0
                 * total_amount : 240.00
                 * unionid : null
                 * openid :
                 * headimg :
                 * nickname : YSM_T_80029
                 * phone : 18000000029
                 * username :
                 * sex :
                 * province :
                 * city :
                 * area :
                 * amount_profit : 0.00
                 * amount_used : 0.00
                 * black_state : 0
                 * vip_auth : 1
                 * vip_level : 1
                 * vip_date : 2019-12-20 20:01:05
                 * vip_time : null
                 * vip_desc :
                 * is_new : 1
                 * pay_at : 2019-12-20
                 * sharepic :
                 * token :
                 * token_time : 0
                 * create_at : 2019-12-12 16:16:45
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
                private Object unionid;
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
            }
        }
    }
}

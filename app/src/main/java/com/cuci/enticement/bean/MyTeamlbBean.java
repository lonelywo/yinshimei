package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class MyTeamlbBean implements Serializable {

    /**
     * code : 1
     * info : 获取团队列表
     * data : {"page":{"limit":20,"total":1,"pages":1,"current":1},"list":[{"id":5,"bid":3,"pid":3,"ppid":4,"path":"-1-4-3-","teams":1,"openid":"odTi05Iz452BXrop2tzSX2zgvi34","headimg":"https://wx.qlogo.cn/mmopen/vi_32/B7p8oRxOiabPsEfEuHqKTCvjTeBRJtg6kEElG3HkFVof3Td5NoXc7l0bQqJWN68ZKb4HNVgUNfuibumxbN62A0nQ/132","nickname":"Amy张\\ud83c\\udf6d18122377655","phone":"18122377655","username":"","vip_auth":1,"vip_level":1,"vip_date":"2019-02-27","create_at":"2019-02-27 17:53:19"}]}
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
         * list : [{"id":5,"bid":3,"pid":3,"ppid":4,"path":"-1-4-3-","teams":1,"openid":"odTi05Iz452BXrop2tzSX2zgvi34","headimg":"https://wx.qlogo.cn/mmopen/vi_32/B7p8oRxOiabPsEfEuHqKTCvjTeBRJtg6kEElG3HkFVof3Td5NoXc7l0bQqJWN68ZKb4HNVgUNfuibumxbN62A0nQ/132","nickname":"Amy张\\ud83c\\udf6d18122377655","phone":"18122377655","username":"","vip_auth":1,"vip_level":1,"vip_date":"2019-02-27","create_at":"2019-02-27 17:53:19"}]
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

        public static class ListBean implements Serializable{
            /**
             * id : 5
             * bid : 3
             * pid : 3
             * ppid : 4
             * path : -1-4-3-
             * teams : 1
             * openid : odTi05Iz452BXrop2tzSX2zgvi34
             * headimg : https://wx.qlogo.cn/mmopen/vi_32/B7p8oRxOiabPsEfEuHqKTCvjTeBRJtg6kEElG3HkFVof3Td5NoXc7l0bQqJWN68ZKb4HNVgUNfuibumxbN62A0nQ/132
             * nickname : Amy张\ud83c\udf6d18122377655
             * phone : 18122377655
             * username :
             * vip_auth : 1
             * vip_level : 1
             * vip_date : 2019-02-27
             * create_at : 2019-02-27 17:53:19
             */

            private int id;
            private int bid;
            private int pid;
            private int ppid;
            private String path;
            private int teams;
            private String openid;
            private String headimg;
            private String nickname;
            private String phone;
            private String username;
            private int vip_auth;
            private int vip_level;
            private String vip_date;
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

            public int getTeams() {
                return teams;
            }

            public void setTeams(int teams) {
                this.teams = teams;
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

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }
        }
    }
}

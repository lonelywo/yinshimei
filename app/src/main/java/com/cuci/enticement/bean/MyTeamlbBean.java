package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class MyTeamlbBean implements Serializable {


    /**
     * code : 1
     * info : 获取团队列表
     * data : {"page":{"limit":20,"total":130,"pages":7,"current":1},"list":[{"id":15527,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/tJex3UicbJnYP6hf2V4HkCdtTy9LsrcmWnI7LB8osbWrjdHH6iaQYwvTLRQLzh3oO1xicc92QGaoHqAjomJZMd0Tw/132","nickname":"姜姗","vip_date":"2019-08-30","create_at":"2019-08-29 23:04:35"},{"id":13644,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83equmwhev0OCVDBYrgXjYHJ6LYWvibKQsibt7MjLDPXAQEWfAUosHt8WyjZkgfickFxbvNozCpm5u730A/132","nickname":"叮当","vip_date":"2019-08-25","create_at":"2019-08-25 21:42:03"},{"id":13127,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIte8pz9dObYrdicFMPvuO4AcHJR3xthIOzRicb5CJ8tgeBJvUJBr5JL3uAw8n7BLEXyROhHcPg0kHg/132","nickname":"小猪","vip_date":"2019-08-24","create_at":"2019-08-24 14:37:08"},{"id":11448,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/mKJCELWXzOJibicDynCEhV0pmw7BqOibsV56qbAnawR4BSIqcPLf4rta5ahHUDoCGlZibGM65eMMecz0cvG5GrCNmQ/132","nickname":"快活女孩","vip_date":"2019-08-18","create_at":"2019-08-18 19:00:33"},{"id":9476,"bid":41,"pid":41,"ppid":2,"teams":9,"teams_total":9,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJjGypw65XYqZqa0D1CnFicRrPxFHBCKbm7jhmXgg7f7IglJJesdUYYeIiauZ0ia3VT3mulbC6NTTPfQ/132","nickname":"璇💦梓","vip_date":"2019-08-07","create_at":"2019-08-07 23:42:55"},{"id":5879,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/vLu8eic2Or0nMegTIT3C1zicJNronCicODMThVuodT2JxtJb2iaeJnHyTHW1gKWn44ibY3z9bSMrlacOet3j9353DpQ/132","nickname":"Q🌹","vip_date":"2019-07-13","create_at":"2019-07-13 00:28:00"},{"id":5452,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/dwZOpYeUhpDiane3pBdxFGfZRFjkXmtaSibVJ0bbg9kg5vltuteuRQsWt6HbY9gNZAFicMoCgNH2lddEDvv0h2zgA/132","nickname":"汝丽","vip_date":"2019-07-07","create_at":"2019-07-07 13:42:25"},{"id":4582,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/J05wib9YibJ0Q69ZB3U3cdwUEVyvj82dfOB8ubJRAgVbqQMuGbtaHztbgm8ibKESbvJkRfWH28QicA3vhDK0L3uOpQ/132","nickname":"安然","vip_date":"2019-06-27","create_at":"2019-06-27 14:28:30"},{"id":4539,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhJVXa3HmsdMAa1JKwhVvRPVrOI1Ij4Z6ic4X5zyfibbVM8lqFhianZd0TuWdbHgesxgK3QFknDO8ng/132","nickname":"婷&涛","vip_date":"2019-06-27","create_at":"2019-06-27 11:34:54"},{"id":4288,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/UDSr5g1R69RoX5icv9N03FNbPRGqZuePwemQtPrtDjqrEB6rGYYPrs62RDM8kXFckqiboYG1JJspRsz85M15abpg/132","nickname":"永康市唯尚新能源有限公司","vip_date":"2019-06-23","create_at":"2019-06-23 18:51:28"},{"id":4278,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJqibAQibGgndeXNugsbePudjML4z6Ej8KK0ehkNKdRLFEWicZFZdjOhoqhhN4MRPTYeib9wqQyKkAuuA/132","nickname":"Jessica","vip_date":"2019-06-23","create_at":"2019-06-23 17:31:59"},{"id":4201,"bid":41,"pid":41,"ppid":2,"teams":2,"teams_total":2,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/952hPjHqMeeZrGqTOPmWf38U0ej3oMQuyyGib9EqSkeRxJxM5962bC9MgumVf9ORMjJCy4Y8D11hblWPBM7Aicxw/132","nickname":"Cui 格媚","vip_date":"2019-06-22","create_at":"2019-06-22 14:30:15"},{"id":4020,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLanbZRoNegz9IMsxqzaIv4Q0FT7ZVt7aVRaRIYRha2c1Vo8mb4fvdCAlp2qwaRDuLLZGPgrfiae7Q/132","nickname":"春霞","vip_date":"2019-06-20","create_at":"2019-06-20 16:59:43"},{"id":4013,"bid":41,"pid":41,"ppid":2,"teams":5,"teams_total":5,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoY3ibvDspKwwlBbCLSv9gcvmWyEJYUIOgBREswogmDnCrClfRh3GEX9mycY9gLGe9fomMBfict6nog/132","nickname":"雪珍","vip_date":"2019-06-20","create_at":"2019-06-20 15:32:01"},{"id":3868,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/cHOeDBdzDibh9jGyOcjlicI4nLk06zSD1rL9ASialrN5iafU0M0miamWYDAjlI9LoibfWBGNnrcYHu71LjyK3MpBWcAQ/132","nickname":"豆子","vip_date":"2019-06-18","create_at":"2019-06-18 17:34:24"},{"id":3814,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKq6blyJAQ87OKpIWaJnq8pDgMRgicnCf90Yib2qBliaDjlT1RIQGib1c9nU7HA0icc0vBgl9kOvy5QPhg/132","nickname":"晓","vip_date":"2019-06-17","create_at":"2019-06-17 21:29:38"},{"id":3619,"bid":41,"pid":41,"ppid":2,"teams":2,"teams_total":2,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/kLqJ7mDvpuRpfkQmHZzxRraWxLau57ntoKRX5UgKKk0BsKPJ9h5t4cQibc3yIFDHfPekGlbRu0NP8VE3VBowaQQ/132","nickname":"家玲","vip_date":"2019-06-15","create_at":"2019-06-15 18:56:35"},{"id":3214,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/uPyYTVXibibl9UqbogYVR41WquYCNZWVicJCrbjbTePuCQGBsWG7UhI9EIcG6l6proLv1IUNdPTruvHe3ux69jPAg/132","nickname":"阿布点","vip_date":"2019-06-10","create_at":"2019-06-10 15:41:04"},{"id":2680,"bid":41,"pid":41,"ppid":2,"teams":18,"teams_total":20,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/YYxVSp2Yzics1Ajz5iaZYO5wMMpgMSlticPmwvVqExIvKzD2va4j8elicuxUTe7gXvk7AenYzttmBdtqAhfVUdWkVA/132","nickname":"水莲","vip_date":"2019-06-05","create_at":"2019-06-03 00:26:57"},{"id":2418,"bid":41,"pid":41,"ppid":2,"teams":4,"teams_total":4,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/NYfjRelia7DOIoMwgibPAHm4PKtxyRU1ts6fUbSE72DDOz3KFYpIyqgtD6u525tKRp5sfOKCZkfO7zUd1HuGgM5g/132","nickname":"_Chirsty     💋：","vip_date":"2019-05-31","create_at":"2019-05-31 23:30:06"}]}
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
         * page : {"limit":20,"total":130,"pages":7,"current":1}
         * list : [{"id":15527,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/tJex3UicbJnYP6hf2V4HkCdtTy9LsrcmWnI7LB8osbWrjdHH6iaQYwvTLRQLzh3oO1xicc92QGaoHqAjomJZMd0Tw/132","nickname":"姜姗","vip_date":"2019-08-30","create_at":"2019-08-29 23:04:35"},{"id":13644,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83equmwhev0OCVDBYrgXjYHJ6LYWvibKQsibt7MjLDPXAQEWfAUosHt8WyjZkgfickFxbvNozCpm5u730A/132","nickname":"叮当","vip_date":"2019-08-25","create_at":"2019-08-25 21:42:03"},{"id":13127,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIte8pz9dObYrdicFMPvuO4AcHJR3xthIOzRicb5CJ8tgeBJvUJBr5JL3uAw8n7BLEXyROhHcPg0kHg/132","nickname":"小猪","vip_date":"2019-08-24","create_at":"2019-08-24 14:37:08"},{"id":11448,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/mKJCELWXzOJibicDynCEhV0pmw7BqOibsV56qbAnawR4BSIqcPLf4rta5ahHUDoCGlZibGM65eMMecz0cvG5GrCNmQ/132","nickname":"快活女孩","vip_date":"2019-08-18","create_at":"2019-08-18 19:00:33"},{"id":9476,"bid":41,"pid":41,"ppid":2,"teams":9,"teams_total":9,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJjGypw65XYqZqa0D1CnFicRrPxFHBCKbm7jhmXgg7f7IglJJesdUYYeIiauZ0ia3VT3mulbC6NTTPfQ/132","nickname":"璇💦梓","vip_date":"2019-08-07","create_at":"2019-08-07 23:42:55"},{"id":5879,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/vLu8eic2Or0nMegTIT3C1zicJNronCicODMThVuodT2JxtJb2iaeJnHyTHW1gKWn44ibY3z9bSMrlacOet3j9353DpQ/132","nickname":"Q🌹","vip_date":"2019-07-13","create_at":"2019-07-13 00:28:00"},{"id":5452,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/dwZOpYeUhpDiane3pBdxFGfZRFjkXmtaSibVJ0bbg9kg5vltuteuRQsWt6HbY9gNZAFicMoCgNH2lddEDvv0h2zgA/132","nickname":"汝丽","vip_date":"2019-07-07","create_at":"2019-07-07 13:42:25"},{"id":4582,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/J05wib9YibJ0Q69ZB3U3cdwUEVyvj82dfOB8ubJRAgVbqQMuGbtaHztbgm8ibKESbvJkRfWH28QicA3vhDK0L3uOpQ/132","nickname":"安然","vip_date":"2019-06-27","create_at":"2019-06-27 14:28:30"},{"id":4539,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhJVXa3HmsdMAa1JKwhVvRPVrOI1Ij4Z6ic4X5zyfibbVM8lqFhianZd0TuWdbHgesxgK3QFknDO8ng/132","nickname":"婷&涛","vip_date":"2019-06-27","create_at":"2019-06-27 11:34:54"},{"id":4288,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/UDSr5g1R69RoX5icv9N03FNbPRGqZuePwemQtPrtDjqrEB6rGYYPrs62RDM8kXFckqiboYG1JJspRsz85M15abpg/132","nickname":"永康市唯尚新能源有限公司","vip_date":"2019-06-23","create_at":"2019-06-23 18:51:28"},{"id":4278,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJqibAQibGgndeXNugsbePudjML4z6Ej8KK0ehkNKdRLFEWicZFZdjOhoqhhN4MRPTYeib9wqQyKkAuuA/132","nickname":"Jessica","vip_date":"2019-06-23","create_at":"2019-06-23 17:31:59"},{"id":4201,"bid":41,"pid":41,"ppid":2,"teams":2,"teams_total":2,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/952hPjHqMeeZrGqTOPmWf38U0ej3oMQuyyGib9EqSkeRxJxM5962bC9MgumVf9ORMjJCy4Y8D11hblWPBM7Aicxw/132","nickname":"Cui 格媚","vip_date":"2019-06-22","create_at":"2019-06-22 14:30:15"},{"id":4020,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLanbZRoNegz9IMsxqzaIv4Q0FT7ZVt7aVRaRIYRha2c1Vo8mb4fvdCAlp2qwaRDuLLZGPgrfiae7Q/132","nickname":"春霞","vip_date":"2019-06-20","create_at":"2019-06-20 16:59:43"},{"id":4013,"bid":41,"pid":41,"ppid":2,"teams":5,"teams_total":5,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoY3ibvDspKwwlBbCLSv9gcvmWyEJYUIOgBREswogmDnCrClfRh3GEX9mycY9gLGe9fomMBfict6nog/132","nickname":"雪珍","vip_date":"2019-06-20","create_at":"2019-06-20 15:32:01"},{"id":3868,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/cHOeDBdzDibh9jGyOcjlicI4nLk06zSD1rL9ASialrN5iafU0M0miamWYDAjlI9LoibfWBGNnrcYHu71LjyK3MpBWcAQ/132","nickname":"豆子","vip_date":"2019-06-18","create_at":"2019-06-18 17:34:24"},{"id":3814,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKq6blyJAQ87OKpIWaJnq8pDgMRgicnCf90Yib2qBliaDjlT1RIQGib1c9nU7HA0icc0vBgl9kOvy5QPhg/132","nickname":"晓","vip_date":"2019-06-17","create_at":"2019-06-17 21:29:38"},{"id":3619,"bid":41,"pid":41,"ppid":2,"teams":2,"teams_total":2,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/kLqJ7mDvpuRpfkQmHZzxRraWxLau57ntoKRX5UgKKk0BsKPJ9h5t4cQibc3yIFDHfPekGlbRu0NP8VE3VBowaQQ/132","nickname":"家玲","vip_date":"2019-06-15","create_at":"2019-06-15 18:56:35"},{"id":3214,"bid":41,"pid":41,"ppid":2,"teams":0,"teams_total":0,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/uPyYTVXibibl9UqbogYVR41WquYCNZWVicJCrbjbTePuCQGBsWG7UhI9EIcG6l6proLv1IUNdPTruvHe3ux69jPAg/132","nickname":"阿布点","vip_date":"2019-06-10","create_at":"2019-06-10 15:41:04"},{"id":2680,"bid":41,"pid":41,"ppid":2,"teams":18,"teams_total":20,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/YYxVSp2Yzics1Ajz5iaZYO5wMMpgMSlticPmwvVqExIvKzD2va4j8elicuxUTe7gXvk7AenYzttmBdtqAhfVUdWkVA/132","nickname":"水莲","vip_date":"2019-06-05","create_at":"2019-06-03 00:26:57"},{"id":2418,"bid":41,"pid":41,"ppid":2,"teams":4,"teams_total":4,"daily_teams":0,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/NYfjRelia7DOIoMwgibPAHm4PKtxyRU1ts6fUbSE72DDOz3KFYpIyqgtD6u525tKRp5sfOKCZkfO7zUd1HuGgM5g/132","nickname":"_Chirsty     💋：","vip_date":"2019-05-31","create_at":"2019-05-31 23:30:06"}]
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

        public static class PageBean implements Serializable{
            /**
             * limit : 20
             * total : 130
             * pages : 7
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
             * id : 15527
             * bid : 41
             * pid : 41
             * ppid : 2
             * teams : 0
             * teams_total : 0
             * daily_teams : 0
             * headimg : https://wx.qlogo.cn/mmopen/vi_32/tJex3UicbJnYP6hf2V4HkCdtTy9LsrcmWnI7LB8osbWrjdHH6iaQYwvTLRQLzh3oO1xicc92QGaoHqAjomJZMd0Tw/132
             * nickname : 姜姗
             * vip_date : 2019-08-30
             * create_at : 2019-08-29 23:04:35
             */

            private int id;
            private int bid;
            private int pid;
            private int ppid;
            private int teams;
            private int teams_total;
            private int daily_teams;

            public int getDaily_teams_total() {
                return daily_teams_total;
            }

            public void setDaily_teams_total(int daily_teams_total) {
                this.daily_teams_total = daily_teams_total;
            }

            private int daily_teams_total;
            private String headimg;
            private String nickname;
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

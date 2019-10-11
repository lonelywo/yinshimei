package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class PKbean2 implements Serializable {


    /**
     * code : 1
     * info : 获取个人日消费榜成功！
     * data : {"page":{"limit":20,"total":2,"pages":1,"current":1},"list":[{"mid":1,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsRg0rPKBkPFDnTjRsEaXKkWpaAWJyVzoMic5gG9nEGhibSc1OFXwgPvZSFcrVKibZ4Fhz9PMho6gJQ/132","nickname":"因诗美","day_amount":"5130.00"},{"mid":74,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhKfKwcPX90cibibER6T2S6FYBclmt1RLzwN9CZRd4dUGS2t9eolMyDK1NacDOLNpF377VusFVRBBg/132","nickname":"yvonne","day_amount":"5060.00"}],"myself":{"day_amount":"5060.00","mid":74,"ranking":2,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhKfKwcPX90cibibER6T2S6FYBclmt1RLzwN9CZRd4dUGS2t9eolMyDK1NacDOLNpF377VusFVRBBg/132","nickname":"yvonne"}}
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
         * page : {"limit":20,"total":2,"pages":1,"current":1}
         * list : [{"mid":1,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsRg0rPKBkPFDnTjRsEaXKkWpaAWJyVzoMic5gG9nEGhibSc1OFXwgPvZSFcrVKibZ4Fhz9PMho6gJQ/132","nickname":"因诗美","day_amount":"5130.00"},{"mid":74,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhKfKwcPX90cibibER6T2S6FYBclmt1RLzwN9CZRd4dUGS2t9eolMyDK1NacDOLNpF377VusFVRBBg/132","nickname":"yvonne","day_amount":"5060.00"}]
         * myself : {"day_amount":"5060.00","mid":74,"ranking":2,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhKfKwcPX90cibibER6T2S6FYBclmt1RLzwN9CZRd4dUGS2t9eolMyDK1NacDOLNpF377VusFVRBBg/132","nickname":"yvonne"}
         */

        private PageBean page;
        private MyselfBean myself;
        private List<ListBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public MyselfBean getMyself() {
            return myself;
        }

        public void setMyself(MyselfBean myself) {
            this.myself = myself;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageBean implements Serializable {
            /**
             * limit : 20
             * total : 2
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

        public static class MyselfBean implements Serializable{
            /**
             * day_amount : 5060.00
             * mid : 74
             * ranking : 2
             * headimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhKfKwcPX90cibibER6T2S6FYBclmt1RLzwN9CZRd4dUGS2t9eolMyDK1NacDOLNpF377VusFVRBBg/132
             * nickname : yvonne
             */

            private String day_amount;
            private int mid;
            private int ranking;
            private String headimg;
            private String nickname;

            public String getDay_amount() {
                return day_amount;
            }

            public void setDay_amount(String day_amount) {
                this.day_amount = day_amount;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public int getRanking() {
                return ranking;
            }

            public void setRanking(int ranking) {
                this.ranking = ranking;
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
        }

        public static class ListBean implements Serializable{
            /**
             * mid : 1
             * headimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsRg0rPKBkPFDnTjRsEaXKkWpaAWJyVzoMic5gG9nEGhibSc1OFXwgPvZSFcrVKibZ4Fhz9PMho6gJQ/132
             * nickname : 因诗美
             * day_amount : 5130.00
             */

            private int mid;
            private String headimg;
            private String nickname;
            private String day_amount;

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
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

            public String getDay_amount() {
                return day_amount;
            }

            public void setDay_amount(String day_amount) {
                this.day_amount = day_amount;
            }
        }
    }
}

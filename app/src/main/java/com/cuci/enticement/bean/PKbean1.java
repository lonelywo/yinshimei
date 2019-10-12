package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class PKbean1 implements Serializable {


    /**
     * code : 1
     * info : 获取个人消费榜成功！
     * data : {"page":{"limit":20,"total":1,"pages":1,"current":1},"list":[{"mid":62,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEK1rn5ls0qBibICbC54StdA9vg0y3Ufo7YMqWP2jGEM2XeLwR4foXDmJReZ6jh0MjYhKWy7CczNHhQ/132","nickname":"lost","total_amount":"15400.00"}],"myself":{"id":41,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132","nickname":"凯婷","total_amount":"0.00","ranking":3284}}
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
         * page : {"limit":20,"total":1,"pages":1,"current":1}
         * list : [{"mid":62,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEK1rn5ls0qBibICbC54StdA9vg0y3Ufo7YMqWP2jGEM2XeLwR4foXDmJReZ6jh0MjYhKWy7CczNHhQ/132","nickname":"lost","total_amount":"15400.00"}]
         * myself : {"id":41,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132","nickname":"凯婷","total_amount":"0.00","ranking":3284}
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

        public static class PageBean implements Serializable{
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

        public static class MyselfBean implements Serializable{
            /**
             * id : 41
             * headimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132
             * nickname : 凯婷
             * total_amount : 0.00
             * ranking : 3284
             */

            private int id;
            private String headimg;
            private String nickname;
            private String total_amount;
            private int ranking;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public int getRanking() {
                return ranking;
            }

            public void setRanking(int ranking) {
                this.ranking = ranking;
            }
        }

        public static class ListBean implements Serializable{
            /**
             * mid : 62
             * headimg : https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEK1rn5ls0qBibICbC54StdA9vg0y3Ufo7YMqWP2jGEM2XeLwR4foXDmJReZ6jh0MjYhKWy7CczNHhQ/132
             * nickname : lost
             * total_amount : 15400.00
             */

            private int mid;
            private String headimg;
            private String nickname;
            private String total_amount;

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

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }
        }
    }
}

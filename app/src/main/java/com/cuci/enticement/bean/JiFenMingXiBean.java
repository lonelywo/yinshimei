package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class JiFenMingXiBean implements Serializable {


    /**
     * code : 1
     * info : ok
     * data : {"page":{"limit":"20","total":21,"pages":2,"current":"1"},"list":[{"id":42,"title":"签到6","mid":52202,"points":6,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":41,"title":"签到5","mid":52202,"points":5,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":40,"title":"签到4","mid":52202,"points":4,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":39,"title":"签到3","mid":52202,"points":3,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":38,"title":"签到2","mid":52202,"points":2,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":37,"title":"签到1","mid":52202,"points":1,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":36,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":35,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":34,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":33,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":32,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":31,"title":"签到6","mid":52202,"points":6,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":30,"title":"签到5","mid":52202,"points":5,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":29,"title":"签到4","mid":52202,"points":4,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":28,"title":"签到3","mid":52202,"points":3,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":27,"title":"签到2","mid":52202,"points":2,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":26,"title":"签到1","mid":52202,"points":1,"source":0,"create_at":"2020-04-23 16:15:48"},{"id":25,"title":"积分兑换","mid":52202,"points":-20,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":24,"title":"积分兑换","mid":52202,"points":-10,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":23,"title":"因诗美购物积分","mid":52202,"points":99,"source":0,"create_at":"2020-04-23 16:15:47"}],"points":246,"used_points":30}
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
         * page : {"limit":"20","total":21,"pages":2,"current":"1"}
         * list : [{"id":42,"title":"签到6","mid":52202,"points":6,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":41,"title":"签到5","mid":52202,"points":5,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":40,"title":"签到4","mid":52202,"points":4,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":39,"title":"签到3","mid":52202,"points":3,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":38,"title":"签到2","mid":52202,"points":2,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":37,"title":"签到1","mid":52202,"points":1,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":36,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":35,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":34,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":33,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":32,"title":"签到7","mid":52202,"points":7,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":31,"title":"签到6","mid":52202,"points":6,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":30,"title":"签到5","mid":52202,"points":5,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":29,"title":"签到4","mid":52202,"points":4,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":28,"title":"签到3","mid":52202,"points":3,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":27,"title":"签到2","mid":52202,"points":2,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":26,"title":"签到1","mid":52202,"points":1,"source":0,"create_at":"2020-04-23 16:15:48"},{"id":25,"title":"积分兑换","mid":52202,"points":-20,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":24,"title":"积分兑换","mid":52202,"points":-10,"source":0,"create_at":"2020-04-23 16:15:47"},{"id":23,"title":"因诗美购物积分","mid":52202,"points":99,"source":0,"create_at":"2020-04-23 16:15:47"}]
         * points : 246
         * used_points : 30
         */

        private PageBean page;
        private int points;
        private int used_points;
        private List<ListBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getUsed_points() {
            return used_points;
        }

        public void setUsed_points(int used_points) {
            this.used_points = used_points;
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
             * total : 21
             * pages : 2
             * current : 1
             */

            private String limit;
            private int total;
            private int pages;
            private int current;

            public String getLimit() {
                return limit;
            }

            public void setLimit(String limit) {
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
             * id : 42
             * title : 签到6
             * mid : 52202
             * points : 6
             * source : 0
             * create_at : 2020-04-23 16:15:47
             */

            private int id;
            private String title;
            private int mid;
            private int points;
            private int source;
            private String create_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            public int getSource() {
                return source;
            }

            public void setSource(int source) {
                this.source = source;
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

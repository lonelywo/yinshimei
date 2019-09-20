package com.cuci.enticement.bean;

import java.util.List;

public class MallSourceBean {

    /**
     * code : 1
     * info : 获取素材列表
     * data : {"page":{"limit":20,"total":129,"pages":7,"current":1},"list":[{"id":2032,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/1920cedc2599f166/36446de718ff9cb5.jpg","create_at":"2019-09-08 09:40:25"},{"id":2018,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/cb9c0544762504ae/1207aed4109652e5.jpg","create_at":"2019-09-07 09:29:33"},{"id":1992,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/22a27c35a17ae2c0/ea05fdf78fc95fd0.jpg","create_at":"2019-09-04 09:34:20"},{"id":1984,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/c1792b4718bcb149/ebc566fd8ec0b170.jpg","create_at":"2019-09-03 09:29:54"},{"id":1938,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/ff25a49c24d015c8/d31ec006d41963be.jpg","create_at":"2019-08-31 09:32:42"},{"id":1876,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/ba34b1191d5e573c/c04e05ba6025e829.jpg","create_at":"2019-08-29 09:28:58"},{"id":1868,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/eb5e2996ea8bc76e/1e716affd6fe36ab.jpg","create_at":"2019-08-28 09:31:20"},{"id":1852,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/cd01d9dcb22a0021/baf15a27c86577d6.jpg","create_at":"2019-08-27 09:28:46"},{"id":1840,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/06a9e455f4dcf5df/4e3c760a7fe4e359.jpg","create_at":"2019-08-25 09:35:14"},{"id":1826,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/77281f6e5e14157d/eecd4907ef8c8df1.jpg","create_at":"2019-08-24 09:30:32"},{"id":1801,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/f6ca8af6f93a12f9/98b7cf845c114954.jpg","create_at":"2019-08-22 09:27:49"},{"id":1791,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/275e0166c980efcb/760af351fc0142fa.jpg","create_at":"2019-08-21 09:28:51"},{"id":1784,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/da398dd825d1977f/9a993bd2d2b9983e.jpg","create_at":"2019-08-20 09:34:56"},{"id":1748,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/dd221a54ea193a98/793f2309c73a7ea4.jpg","create_at":"2019-08-17 09:11:32"},{"id":1733,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/609ee6f00aba20ec/4ee0ce2344d1c17e.jpg","create_at":"2019-08-15 09:31:49"},{"id":1720,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/ebde5218b0bbcfa9/a768c395438878a7.jpg","create_at":"2019-08-14 09:30:12"},{"id":1706,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/39f055c840db331a/8f725157b32cbfb3.jpg","create_at":"2019-08-13 09:30:35"},{"id":1667,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/57228af68eb5bab5/2d9c96b16c86c489.jpg","create_at":"2019-08-10 09:26:26"},{"id":1658,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/3f0fda624ef29ae1/6fb87cc2e2f835f6.jpg","create_at":"2019-08-08 09:46:31"},{"id":1656,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/3f0b1e3e3541692d/6f8a4ead11d94f89.jpg","create_at":"2019-08-07 09:36:44"}],"types":["产品图","创意图","文化图"]}
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
         * page : {"limit":20,"total":129,"pages":7,"current":1}
         * list : [{"id":2032,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/1920cedc2599f166/36446de718ff9cb5.jpg","create_at":"2019-09-08 09:40:25"},{"id":2018,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/cb9c0544762504ae/1207aed4109652e5.jpg","create_at":"2019-09-07 09:29:33"},{"id":1992,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/22a27c35a17ae2c0/ea05fdf78fc95fd0.jpg","create_at":"2019-09-04 09:34:20"},{"id":1984,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/c1792b4718bcb149/ebc566fd8ec0b170.jpg","create_at":"2019-09-03 09:29:54"},{"id":1938,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/ff25a49c24d015c8/d31ec006d41963be.jpg","create_at":"2019-08-31 09:32:42"},{"id":1876,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/ba34b1191d5e573c/c04e05ba6025e829.jpg","create_at":"2019-08-29 09:28:58"},{"id":1868,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/eb5e2996ea8bc76e/1e716affd6fe36ab.jpg","create_at":"2019-08-28 09:31:20"},{"id":1852,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/cd01d9dcb22a0021/baf15a27c86577d6.jpg","create_at":"2019-08-27 09:28:46"},{"id":1840,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/06a9e455f4dcf5df/4e3c760a7fe4e359.jpg","create_at":"2019-08-25 09:35:14"},{"id":1826,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/77281f6e5e14157d/eecd4907ef8c8df1.jpg","create_at":"2019-08-24 09:30:32"},{"id":1801,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/f6ca8af6f93a12f9/98b7cf845c114954.jpg","create_at":"2019-08-22 09:27:49"},{"id":1791,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/275e0166c980efcb/760af351fc0142fa.jpg","create_at":"2019-08-21 09:28:51"},{"id":1784,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/da398dd825d1977f/9a993bd2d2b9983e.jpg","create_at":"2019-08-20 09:34:56"},{"id":1748,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/dd221a54ea193a98/793f2309c73a7ea4.jpg","create_at":"2019-08-17 09:11:32"},{"id":1733,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/609ee6f00aba20ec/4ee0ce2344d1c17e.jpg","create_at":"2019-08-15 09:31:49"},{"id":1720,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/ebde5218b0bbcfa9/a768c395438878a7.jpg","create_at":"2019-08-14 09:30:12"},{"id":1706,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/39f055c840db331a/8f725157b32cbfb3.jpg","create_at":"2019-08-13 09:30:35"},{"id":1667,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/57228af68eb5bab5/2d9c96b16c86c489.jpg","create_at":"2019-08-10 09:26:26"},{"id":1658,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/3f0fda624ef29ae1/6fb87cc2e2f835f6.jpg","create_at":"2019-08-08 09:46:31"},{"id":1656,"type":"创意图","image":"https://yinshimei2.cdn.cuci.cc/3f0b1e3e3541692d/6f8a4ead11d94f89.jpg","create_at":"2019-08-07 09:36:44"}]
         * types : ["产品图","创意图","文化图"]
         */

        private PageBean page;
        private List<ListBean> list;
        private List<String> types;

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

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class PageBean {
            /**
             * limit : 20
             * total : 129
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

        public static class ListBean {
            /**
             * id : 2032
             * type : 创意图
             * image : https://yinshimei2.cdn.cuci.cc/1920cedc2599f166/36446de718ff9cb5.jpg
             * create_at : 2019-09-08 09:40:25
             */

            private int id;
            private String type;
            private String image;
            private String create_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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

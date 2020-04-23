package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class JiFenMallBean implements Serializable {

    /**
     * code : 1
     * info : ok
     * data : {"list":[{"id":6876310413,"title":"黑金蕾丝面膜-vip装","logo":"https://qiniu.cdn.enticementchina.com/e1aea22e48fee4b0/ca69e17e3c5f27f7.jpg","image":["https://qiniu.cdn.enticementchina.com/8bbdb53dcda659c7/74e5ad31c81421c3.jpg","https://qiniu.cdn.enticementchina.com/0da2e54b6608ef61/bbb034db96ff0227.jpg"],"content":"<p><img border=\"0\" src=\"https://app.enticementchina.com/upload/ee1441e933a4e426/17cc3a314c4e6060.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/b8d6cae476bb63b1/1491ab7ab5fe41ce.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/7cd48a5a26e93f86/ef04c6468faf6378.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/d332d8b2c5d9521c/9acb9203b1211696.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/01fc19473c71b5b6/1f8c0c73728182a1.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/bb216bf9dca9f458/d4b35279979516df.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/d878d783ca79e3c6/4e7c559d46d1b835.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/19c11f62884deab9/47d74eadb2cbf797.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/ac615597c9ee7603/1d808be6daa6c02e.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/07e266eeb14749b5/759d7a9a84d61eb1.jpg\" style=\"max-width:100%\" title=\"image\" /><\/p>\r\n","points":400,"number_sales":0,"number_stock":100,"status":1},{"id":6876312220,"title":"温柔高保湿洁面乳 ","logo":"https://app.enticementchina.com/upload/8ca2601ca1df3af0/d6ffd4578c46f0ab.jpg","image":["https://app.enticementchina.com/upload/8ca2601ca1df3af0/d6ffd4578c46f0ab.jpg"],"content":"<p><img border=\"0\" src=\"https://app.enticementchina.com/upload/2470764f41f71c61/f632d612d83dec5f.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/eb227ad2bbdf9f44/8980e210195908b1.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/a9b78164f083343d/2395b31d03f2ebb9.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/17b9bccb43ab2bbb/aab4852d3c041ae5.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/fc5bd858633a388e/7edb102676cc8524.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/e341b4ab6d935e11/b30a1ff8feb351e8.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/fbacaa26d4a5a109/80d236201d55ced7.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/a4004eb6f3662007/caa4b5d3ff5570bc.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/3f77e9c534a167a0/fd7cf2581a0f81f4.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/95e90993fb00e44e/cfbfa1486e136e87.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/07e266eeb14749b5/759d7a9a84d61eb1.jpg\" style=\"max-width:100%\" title=\"image\" /><\/p>\r\n","points":1500,"number_sales":0,"number_stock":1001,"status":1}],"page":{"limit":20,"total":2,"pages":1,"current":1}}
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
         * list : [{"id":6876310413,"title":"黑金蕾丝面膜-vip装","logo":"https://qiniu.cdn.enticementchina.com/e1aea22e48fee4b0/ca69e17e3c5f27f7.jpg","image":["https://qiniu.cdn.enticementchina.com/8bbdb53dcda659c7/74e5ad31c81421c3.jpg","https://qiniu.cdn.enticementchina.com/0da2e54b6608ef61/bbb034db96ff0227.jpg"],"content":"<p><img border=\"0\" src=\"https://app.enticementchina.com/upload/ee1441e933a4e426/17cc3a314c4e6060.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/b8d6cae476bb63b1/1491ab7ab5fe41ce.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/7cd48a5a26e93f86/ef04c6468faf6378.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/d332d8b2c5d9521c/9acb9203b1211696.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/01fc19473c71b5b6/1f8c0c73728182a1.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/bb216bf9dca9f458/d4b35279979516df.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/d878d783ca79e3c6/4e7c559d46d1b835.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/19c11f62884deab9/47d74eadb2cbf797.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/ac615597c9ee7603/1d808be6daa6c02e.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/07e266eeb14749b5/759d7a9a84d61eb1.jpg\" style=\"max-width:100%\" title=\"image\" /><\/p>\r\n","points":400,"number_sales":0,"number_stock":100,"status":1},{"id":6876312220,"title":"温柔高保湿洁面乳 ","logo":"https://app.enticementchina.com/upload/8ca2601ca1df3af0/d6ffd4578c46f0ab.jpg","image":["https://app.enticementchina.com/upload/8ca2601ca1df3af0/d6ffd4578c46f0ab.jpg"],"content":"<p><img border=\"0\" src=\"https://app.enticementchina.com/upload/2470764f41f71c61/f632d612d83dec5f.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/eb227ad2bbdf9f44/8980e210195908b1.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/a9b78164f083343d/2395b31d03f2ebb9.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/17b9bccb43ab2bbb/aab4852d3c041ae5.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/fc5bd858633a388e/7edb102676cc8524.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/e341b4ab6d935e11/b30a1ff8feb351e8.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/fbacaa26d4a5a109/80d236201d55ced7.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/a4004eb6f3662007/caa4b5d3ff5570bc.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/3f77e9c534a167a0/fd7cf2581a0f81f4.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://app.enticementchina.com/upload/95e90993fb00e44e/cfbfa1486e136e87.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/07e266eeb14749b5/759d7a9a84d61eb1.jpg\" style=\"max-width:100%\" title=\"image\" /><\/p>\r\n","points":1500,"number_sales":0,"number_stock":1001,"status":1}]
         * page : {"limit":20,"total":2,"pages":1,"current":1}
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

        public static class ListBean {
            /**
             * id : 6876310413
             * title : 黑金蕾丝面膜-vip装
             * logo : https://qiniu.cdn.enticementchina.com/e1aea22e48fee4b0/ca69e17e3c5f27f7.jpg
             * image : ["https://qiniu.cdn.enticementchina.com/8bbdb53dcda659c7/74e5ad31c81421c3.jpg","https://qiniu.cdn.enticementchina.com/0da2e54b6608ef61/bbb034db96ff0227.jpg"]
             * content : <p><img border="0" src="https://app.enticementchina.com/upload/ee1441e933a4e426/17cc3a314c4e6060.jpg" style="max-width:100%" title="image" /><img border="0" src="https://app.enticementchina.com/upload/b8d6cae476bb63b1/1491ab7ab5fe41ce.jpg" style="max-width:100%" title="image" /><img border="0" src="https://qiniu.cdn.enticementchina.com/7cd48a5a26e93f86/ef04c6468faf6378.jpg" style="max-width:100%" title="image" /><img border="0" src="https://app.enticementchina.com/upload/d332d8b2c5d9521c/9acb9203b1211696.jpg" style="max-width:100%" title="image" /><img border="0" src="https://app.enticementchina.com/upload/01fc19473c71b5b6/1f8c0c73728182a1.jpg" style="max-width:100%" title="image" /><img border="0" src="https://app.enticementchina.com/upload/bb216bf9dca9f458/d4b35279979516df.jpg" style="max-width:100%" title="image" /><img border="0" src="https://app.enticementchina.com/upload/d878d783ca79e3c6/4e7c559d46d1b835.jpg" style="max-width:100%" title="image" /><img border="0" src="https://app.enticementchina.com/upload/19c11f62884deab9/47d74eadb2cbf797.jpg" style="max-width:100%" title="image" /><img border="0" src="https://app.enticementchina.com/upload/ac615597c9ee7603/1d808be6daa6c02e.jpg" style="max-width:100%" title="image" /><img border="0" src="https://qiniu.cdn.enticementchina.com/07e266eeb14749b5/759d7a9a84d61eb1.jpg" style="max-width:100%" title="image" /></p>
             * points : 400
             * number_sales : 0
             * number_stock : 100
             * status : 1
             */

            private long id;
            private String title;
            private String logo;
            private String content;
            private int points;
            private int number_sales;
            private int number_stock;
            private int status;
            private List<String> image;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            public int getNumber_sales() {
                return number_sales;
            }

            public void setNumber_sales(int number_sales) {
                this.number_sales = number_sales;
            }

            public int getNumber_stock() {
                return number_stock;
            }

            public void setNumber_stock(int number_stock) {
                this.number_stock = number_stock;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<String> getImage() {
                return image;
            }

            public void setImage(List<String> image) {
                this.image = image;
            }
        }
    }
}

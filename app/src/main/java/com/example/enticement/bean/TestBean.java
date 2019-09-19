package com.example.enticement.bean;

import java.util.List;

public class TestBean {


    /**
     * page : {"limit":12,"total":0,"pages":0,"current":1}
     * list : []
     * total : 0.0
     */

    private PageBean page;
    private double total;
    private List<?> list;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public static class PageBean {
        /**
         * limit : 12.0
         * total : 0.0
         * pages : 0.0
         * current : 1.0
         */

        private double limit;
        private double total;
        private double pages;
        private double current;

        public double getLimit() {
            return limit;
        }

        public void setLimit(double limit) {
            this.limit = limit;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public double getPages() {
            return pages;
        }

        public void setPages(double pages) {
            this.pages = pages;
        }

        public double getCurrent() {
            return current;
        }

        public void setCurrent(double current) {
            this.current = current;
        }
    }
}

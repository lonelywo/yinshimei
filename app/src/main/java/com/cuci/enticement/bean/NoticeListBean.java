package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class NoticeListBean implements Parcelable {

    /**
     * code : 1
     * info : 获取公告列表成功！
     * data : {"page":{"limit":6,"total":7,"pages":2,"current":1},"list":[{"id":11,"title":"中国红","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红...","create_at":"2020-01-08","catetitle":"商城公告"},{"id":10,"title":"中国红围巾","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中...","create_at":"2020-01-08","catetitle":"商城公告"},{"id":9,"title":"满送","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"满送满送满送满送","create_at":"2020-01-08","catetitle":"商城公告"},{"id":8,"title":"10号活动","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"10号活动10号活动10号活动10号活动10号活动10号活动10号活动10号活动10号活动1...","create_at":"2020-01-08","catetitle":"商城公告"},{"id":7,"title":"fever","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"feverfeverfeverfeverfeverfeverfeverfeverfeverf...","create_at":"2020-01-08","catetitle":"商城公告"},{"id":6,"title":"测试","logo":"https://qiniu.cdn.enticementchina.com/54d62c463b924b36/11213f4d901a5384.png","brief":"简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介...","create_at":"2020-01-06","catetitle":"商城公告"}]}
     */

    private int code;
    private String info;
    private DataBean data;

    protected NoticeListBean(Parcel in) {
        code = in.readInt();
        info = in.readString();
    }

    public static final Creator<NoticeListBean> CREATOR = new Creator<NoticeListBean>() {
        @Override
        public NoticeListBean createFromParcel(Parcel in) {
            return new NoticeListBean(in);
        }

        @Override
        public NoticeListBean[] newArray(int size) {
            return new NoticeListBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(info);
    }

    public static class DataBean {
        /**
         * page : {"limit":6,"total":7,"pages":2,"current":1}
         * list : [{"id":11,"title":"中国红","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红...","create_at":"2020-01-08","catetitle":"商城公告"},{"id":10,"title":"中国红围巾","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中国红围巾中...","create_at":"2020-01-08","catetitle":"商城公告"},{"id":9,"title":"满送","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"满送满送满送满送","create_at":"2020-01-08","catetitle":"商城公告"},{"id":8,"title":"10号活动","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"10号活动10号活动10号活动10号活动10号活动10号活动10号活动10号活动10号活动1...","create_at":"2020-01-08","catetitle":"商城公告"},{"id":7,"title":"fever","logo":"https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png","brief":"feverfeverfeverfeverfeverfeverfeverfeverfeverf...","create_at":"2020-01-08","catetitle":"商城公告"},{"id":6,"title":"测试","logo":"https://qiniu.cdn.enticementchina.com/54d62c463b924b36/11213f4d901a5384.png","brief":"简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介...","create_at":"2020-01-06","catetitle":"商城公告"}]
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
             * limit : 6
             * total : 7
             * pages : 2
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

        public static class ListBean implements Parcelable{
            /**
             * id : 11
             * title : 中国红
             * logo : https://qiniu.cdn.enticementchina.com/e3ab7fa78e02b395/2b103ca79a2ebb31.png
             * brief : 中国红中国红中国红中国红中国红中国红中国红中国红中国红中国红...
             * create_at : 2020-01-08
             * catetitle : 商城公告
             */

            private int id;
            private String title;
            private String logo;
            private String brief;
            private String create_at;
            private String catetitle;

            protected ListBean(Parcel in) {
                id = in.readInt();
                title = in.readString();
                logo = in.readString();
                brief = in.readString();
                create_at = in.readString();
                catetitle = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };

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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getCatetitle() {
                return catetitle;
            }

            public void setCatetitle(String catetitle) {
                this.catetitle = catetitle;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(id);
                parcel.writeString(title);
                parcel.writeString(logo);
                parcel.writeString(brief);
                parcel.writeString(create_at);
                parcel.writeString(catetitle);
            }
        }
    }
}

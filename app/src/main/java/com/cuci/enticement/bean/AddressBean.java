package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AddressBean {


    /**
     * code : 1
     * info : 获取会员收货地址成功！
     * data : {"list":[{"id":2,"mid":3,"name":"夏不了雨","phone":"13631178195","province":"浙江省","city":"杭州市","area":"上城区","address":"这是一间无敌豪华的砖瓦屋","is_default":1,"create_at":"2019-02-27 16:28:26"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * id : 2
             * mid : 3
             * name : 夏不了雨
             * phone : 13631178195
             * province : 浙江省
             * city : 杭州市
             * area : 上城区
             * address : 这是一间无敌豪华的砖瓦屋
             * is_default : 1
             * create_at : 2019-02-27 16:28:26
             */

            private int id;
            private int mid;
            private String name;
            private String phone;
            private String province;
            private String city;
            private String area;
            private String address;
            private int is_default;
            private String create_at;

            protected ListBean(Parcel in) {
                id = in.readInt();
                mid = in.readInt();
                name = in.readString();
                phone = in.readString();
                province = in.readString();
                city = in.readString();
                area = in.readString();
                address = in.readString();
                is_default = in.readInt();
                create_at = in.readString();
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

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getIs_default() {
                return is_default;
            }

            public void setIs_default(int is_default) {
                this.is_default = is_default;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeInt(mid);
                dest.writeString(name);
                dest.writeString(phone);
                dest.writeString(province);
                dest.writeString(city);
                dest.writeString(area);
                dest.writeString(address);
                dest.writeInt(is_default);
                dest.writeString(create_at);
            }
        }
    }
}

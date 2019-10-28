package com.cuci.enticement.bean;

import java.io.Serializable;

public class YeJiYueFanBean implements Serializable {


    /**
     * code : 1
     * info : 查询成功
     * data : {"amount":"5300.00","gift_name":"价值600元产品","express_company_title":"","express_code":"","express_no":"","status":1,"explain":"","address":{"province":"北京市","city":"北京市","area":"东城区","address":"技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术","name":"测试","phone":"18574477777"}}
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
         * amount : 5300.00
         * gift_name : 价值600元产品
         * express_company_title :
         * express_code :
         * express_no :
         * status : 1
         * explain :
         * address : {"province":"北京市","city":"北京市","area":"东城区","address":"技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术","name":"测试","phone":"18574477777"}
         */

        private String amount;
        private String gift_name;
        private String express_company_title;
        private String express_code;
        private String express_no;
        private String status;
        private String explain;
        private AddressBean address;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getGift_name() {
            return gift_name;
        }

        public void setGift_name(String gift_name) {
            this.gift_name = gift_name;
        }

        public String getExpress_company_title() {
            return express_company_title;
        }

        public void setExpress_company_title(String express_company_title) {
            this.express_company_title = express_company_title;
        }

        public String getExpress_code() {
            return express_code;
        }

        public void setExpress_code(String express_code) {
            this.express_code = express_code;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public static class AddressBean implements Serializable{
            /**
             * province : 北京市
             * city : 北京市
             * area : 东城区
             * address : 技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术测试技术
             * name : 测试
             * phone : 18574477777
             */

            private String province;
            private String city;
            private String area;
            private String address;
            private String name;
            private String phone;

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
        }
    }
}

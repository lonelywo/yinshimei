package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class QianDaoBean implements Serializable {


    /**
     * code : 1
     * info : 成功
     * data : {"even_day":1,"today_integral":0,"user_integral":247,"integral_next":[1,2,3,4,5,6,7],"signin_task":[{"type":1,"is_finish":0,"img":"http://qiniu.cdn.enticementchina.com/moments.png","title":"分享海报至朋友圈","get_integral":"5"},{"type":2,"is_finish":0,"img":"http://qiniu.cdn.enticementchina.com/expenditure.png","title":"商城下单消费赚积分","get_integral":"N"}],"share_info":{"qrcode":"https://qiniu.cdn.enticementchina.com/6fa1826c366ede04/b75b132271d29306.jpg","poster":"https://qiniu.cdn.enticementchina.com/picturesign.jpg","mini_share_pic":"https://qiniu.cdn.enticementchina.com/logo.png"}}
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

    public static class DataBean implements Serializable {
        /**
         * even_day : 1
         * today_integral : 0
         * user_integral : 247
         * integral_next : [1,2,3,4,5,6,7]
         * signin_task : [{"type":1,"is_finish":0,"img":"http://qiniu.cdn.enticementchina.com/moments.png","title":"分享海报至朋友圈","get_integral":"5"},{"type":2,"is_finish":0,"img":"http://qiniu.cdn.enticementchina.com/expenditure.png","title":"商城下单消费赚积分","get_integral":"N"}]
         * share_info : {"qrcode":"https://qiniu.cdn.enticementchina.com/6fa1826c366ede04/b75b132271d29306.jpg","poster":"https://qiniu.cdn.enticementchina.com/picturesign.jpg","mini_share_pic":"https://qiniu.cdn.enticementchina.com/logo.png"}
         */

        private int even_day;
        private int today_integral;
        private int user_integral;
        private ShareInfoBean share_info;
        private List<Integer> integral_next;
        private List<SigninTaskBean> signin_task;

        public int getEven_day() {
            return even_day;
        }

        public void setEven_day(int even_day) {
            this.even_day = even_day;
        }

        public int getToday_integral() {
            return today_integral;
        }

        public void setToday_integral(int today_integral) {
            this.today_integral = today_integral;
        }

        public int getUser_integral() {
            return user_integral;
        }

        public void setUser_integral(int user_integral) {
            this.user_integral = user_integral;
        }

        public ShareInfoBean getShare_info() {
            return share_info;
        }

        public void setShare_info(ShareInfoBean share_info) {
            this.share_info = share_info;
        }

        public List<Integer> getIntegral_next() {
            return integral_next;
        }

        public void setIntegral_next(List<Integer> integral_next) {
            this.integral_next = integral_next;
        }

        public List<SigninTaskBean> getSignin_task() {
            return signin_task;
        }

        public void setSignin_task(List<SigninTaskBean> signin_task) {
            this.signin_task = signin_task;
        }

        public static class ShareInfoBean implements Serializable{
            /**
             * qrcode : https://qiniu.cdn.enticementchina.com/6fa1826c366ede04/b75b132271d29306.jpg
             * poster : https://qiniu.cdn.enticementchina.com/picturesign.jpg
             * mini_share_pic : https://qiniu.cdn.enticementchina.com/logo.png
             */

            private String qrcode;
            private String poster;
            private String mini_share_pic;

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public String getMini_share_pic() {
                return mini_share_pic;
            }

            public void setMini_share_pic(String mini_share_pic) {
                this.mini_share_pic = mini_share_pic;
            }
        }

        public static class SigninTaskBean implements Serializable{
            /**
             * type : 1
             * is_finish : 0
             * img : http://qiniu.cdn.enticementchina.com/moments.png
             * title : 分享海报至朋友圈
             * get_integral : 5
             */

            private int type;
            private int is_finish;
            private String img;
            private String title;
            private String get_integral;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getIs_finish() {
                return is_finish;
            }

            public void setIs_finish(int is_finish) {
                this.is_finish = is_finish;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getGet_integral() {
                return get_integral;
            }

            public void setGet_integral(String get_integral) {
                this.get_integral = get_integral;
            }
        }
    }
}

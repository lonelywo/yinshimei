package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class EssayBean implements Parcelable {


    /**
     * code : 1
     * info : 获取信息成功！
     * data : {"content":"<p>黑金魅惑蕾丝面膜 5片/盒（规格：黑金魅惑蕾丝面膜5片/盒）<\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n\r\n<p><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/032a6821191323f7/959acf0e6b401640.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/b11b040d8c1cac8c/e2d1ee2bc3afdaeb.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/5bfa0f17ba732912/a5949f854fb0e4b6.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/a6e0d961413b5ac6/0adc367282af2747.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/d72491a3eec6513e/04204f30351e31f3.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/cd5e4a13144c5ebe/4fcfd82b6ef3920e.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/700ea207c924bcb3/1a181b60abc22ff7.jpg\" style=\"max-width:100%\" title=\"image\" /><img border=\"0\" src=\"https://qiniu.cdn.enticementchina.com/1876a6933038c447/733aceee19ad1a1b.jpg\" style=\"max-width:100%\" title=\"image\" /><\/p>\r\n"}
     */

    private int code;
    private String info;
    private DataBean data;

    protected EssayBean(Parcel in) {
        code = in.readInt();
        info = in.readString();
    }

    public static final Creator<EssayBean> CREATOR = new Creator<EssayBean>() {
        @Override
        public EssayBean createFromParcel(Parcel in) {
            return new EssayBean(in);
        }

        @Override
        public EssayBean[] newArray(int size) {
            return new EssayBean[size];
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
         * content : <p>黑金魅惑蕾丝面膜 5片/盒（规格：黑金魅惑蕾丝面膜5片/盒）</p>

         <p>&nbsp;</p>

         <p><img border="0" src="https://qiniu.cdn.enticementchina.com/032a6821191323f7/959acf0e6b401640.jpg" style="max-width:100%" title="image" /><img border="0" src="https://qiniu.cdn.enticementchina.com/b11b040d8c1cac8c/e2d1ee2bc3afdaeb.jpg" style="max-width:100%" title="image" /><img border="0" src="https://qiniu.cdn.enticementchina.com/5bfa0f17ba732912/a5949f854fb0e4b6.jpg" style="max-width:100%" title="image" /><img border="0" src="https://qiniu.cdn.enticementchina.com/a6e0d961413b5ac6/0adc367282af2747.jpg" style="max-width:100%" title="image" /><img border="0" src="https://qiniu.cdn.enticementchina.com/d72491a3eec6513e/04204f30351e31f3.jpg" style="max-width:100%" title="image" /><img border="0" src="https://qiniu.cdn.enticementchina.com/cd5e4a13144c5ebe/4fcfd82b6ef3920e.jpg" style="max-width:100%" title="image" /><img border="0" src="https://qiniu.cdn.enticementchina.com/700ea207c924bcb3/1a181b60abc22ff7.jpg" style="max-width:100%" title="image" /><img border="0" src="https://qiniu.cdn.enticementchina.com/1876a6933038c447/733aceee19ad1a1b.jpg" style="max-width:100%" title="image" /></p>

         */

        private String content;


        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        private String brief;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

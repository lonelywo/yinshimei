package com.cuci.enticement.bean;

import java.io.Serializable;

public class ZFBBean implements Serializable {
    /**
     * code : 1
     * info : 获取订单支付参数成功！
     * data : app_id=2019081966304642&charset=utf-8&format=JSON&version=1.0&sign_type=RSA2&timestamp=2019-09-24+01%3A37%3A48&notify_url=https%3A%2F%2Ftest.enticementchina.com%2Fstore%2Fapi.notify%2Falipayapp.html&method=alipay.trade.app.pay&biz_content=%7B%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%223660.00%22%2C%22body%22%3A%22%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A%22%E5%9B%A0%E8%AF%97%E7%BE%8E%E5%95%86%E5%93%81%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%22669260266536%22%2C%22notify_url%22%3A%22https%3A%5C%2F%5C%2Ftest.enticementchina.com%5C%2Fstore%5C%2Fapi.notify%5C%2Falipayapp%22%2C%22spbill_create_ip%22%3A%2258.63.132.245%22%7D&sign=jpObI5jpEuOhb%2FGPIHe4tTgU3dmh2rBAyzPTZm9jiBeq4E06mKLn23pJQIaRZBrxCfGHQ5%2F4cSNGWxnTL4r9QWNzRqWR0Ngn%2BnL3oIrKhyslydRR6cvJXRtqz8LneqInfY%2Bq69xqPMJ7LsDGq1GDdyMOp3E6pnkki%2F7y%2Bg6FvpH7ZlZh02FBnhDQV7Czp1CeKdfUTT3OyXEK8%2FXqgoSxqPKXuXs2eL83tEkjbINXYLSt%2Bg6o8Z6nMZn9aDSVQniKW7eiGzgB2vSVc1c2qT6T1%2FuWffp4bA2E5qtkhTE27I4ZDcDJmWdZ6BTirXnedhkZvAkbgDmhevs%2F3ts7hxwaNQ%3D%3D
     */

    private int code;
    private String info;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

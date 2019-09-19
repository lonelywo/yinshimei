package com.example.enticement.bean;

import com.google.gson.annotations.SerializedName;

public class WxError {

    /**
     * errcode : 40029
     * errmsg : invalid code
     */
    @SerializedName("errcode")
    private int errCode;
    @SerializedName("errmsg")
    private String errMsg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WxError{" +
                "errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}

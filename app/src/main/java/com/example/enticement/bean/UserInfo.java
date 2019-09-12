package com.example.enticement.bean;



import java.io.Serializable;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 7932979132841790371L;
    private String phone;
    private String sellerId;
    private String inviteCode;
    private String token;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    @Override
    public String toString() {
        return "UserInfo{" +
                "phone='" + phone + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}

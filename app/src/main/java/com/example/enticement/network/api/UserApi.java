package com.example.enticement.network.api;




public interface UserApi {

  /*  *//**
     * 微信授权登录
     *//*
    @FormUrlEncoded
    @POST("login/api_check_wxauth")
    Call<Base<UserInfo>> checkUserInfo(@Field("unionid") String unionId,
                                       @Field("sex") String sex,
                                       @Field("headimgurl") String headImgUrl,
                                       @Field("nickname") String nickName);

    *//**
     * 发送短信验证码
     *
     * @param operate 只支持“login”（微信授权绑定代理账号接口使用），“register（用户注册接口使用）”，“reset（重置密码接口使用）
     *//*
    @FormUrlEncoded
    @POST("sms/api_send_sms")
    Call<Base> getSmsCode(@Field("operate") String operate,
                          @Field("phone") String phone,
                          @Field("invitecode") String inviteCode,
                          @Field("sign") String sign);

    *//**
     * 微信授权绑定代理账号
     *//*
    @FormUrlEncoded
    @POST("login/api_wxauth_bind_phone")
    Call<Base<UserInfo>> wxBindPhone(@Field("unionid") String unionId,
                                     @Field("phone") String phone,
                                     @Field("smscode") String smsCode);

    *//**
     * 绑定邀请码
     *//*
    @FormUrlEncoded
    @POST("login/api_bind_inviter")
    Call<Base<UserInfo>> bindInviteCode(@Field("invitecode") String inviteCode,
                                        @Field("token") String token,
                                        @Field("phone") String phone);

    *//**
     * 账号密码登录
     *//*
    @FormUrlEncoded
    @POST("login/api_phone_login")
    Call<Base<UserInfo>> login(@Field("phone") String phone,
                               @Field("password") String password,
                               @Field("smscode") String smsCode);

    *//**
     * 极光闪验登录
     *//*
    @FormUrlEncoded
    @POST("login/api_login_token_verify")
    Call<Base<UserInfo>> jiguangLogin(@Field("token") String token);

    *//**
     * 注册
     *//*
    @FormUrlEncoded
    @POST("register/add_user")
    Call<Base> register(@Field("phone") String phone,
                        @Field("password") String password,
                        @Field("smscode") String smsCode,
                        @Field("invitecode") String inviteCode);

    *//**
     * 重置密码
     *//*
    @FormUrlEncoded
    @POST("reset/api_reset_password")
    Call<Base> resetPassword(@Field("phone") String phone,
                             @Field("password") String password,
                             @Field("repeat") String repeat,
                             @Field("smscode") String smsCode);

    *//**
     * 获取邀请码信息
     *//*
    @FormUrlEncoded
    @POST("login/get_invite_info")
    Call<Base<InviteCodeInfo>> getInviteCodeInfo(@Field("invitecode") String InviteCode);

*/
}


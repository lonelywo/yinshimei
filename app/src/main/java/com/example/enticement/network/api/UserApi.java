package com.example.enticement.network.api;


import com.example.enticement.bean.Base;
import com.example.enticement.bean.UserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("store/api.user/reg")
    Call<Base> register(@Field("code") String code,
                        @Field("phone") String phone,
                        @Field("agent_phone") String agent_phone
                                                          );
    /**
     * 获取注册验证码
     */
    @FormUrlEncoded
    @POST("store/api.user/regsendsms")
    Call<Base> getSmsCode(@Field("phone") String phone,
                        @Field("secure") String secure,
                        @Field("region") String region
    );
    /**
     * 获取登录验证码
     */
    @FormUrlEncoded
    @POST("store/api.user/setsendsms")
    Call<Base> getSmsCodelogin(@Field("phone") String phone,
                          @Field("secure") String secure,
                          @Field("region") String region
    );
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("store/api.user/login")
    Call<Base<UserInfo>> login(@Field("phone") String phone,
                               @Field("code") String code

    );
}


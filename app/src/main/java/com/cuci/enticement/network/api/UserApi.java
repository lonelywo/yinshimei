package com.cuci.enticement.network.api;


import com.cuci.enticement.bean.AdressBean;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.UserInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("store/api.user/reg")
    Call<Base> register(@Field("code") String code,
                        @Field("phone") String phone,
                        @Field("agent_phone") String agent_phone,
                        @Field("sign") String sign
                                                          );
    /**
     * 获取注册验证码
     */
    @FormUrlEncoded
    @POST("/store/api.user/needsms")
    Call<Base> getSmsCode(@Field("phone") String phone,
                        @Field("secure") String secure,
                        @Field("region") String region,
                        @Field("type") String type,
                        @Field("sign") String sign
    );
    /**
     * 获取登录验证码
     */
    @FormUrlEncoded
    @POST("/store/api.user/needsms")
    Call<Base> getSmsCodelogin(@Field("phone") String phone,
                               @Field("secure") String secure,
                               @Field("region") String region,
                               @Field("type") String type,
                               @Field("sign") String sign
    );
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("store/api.user/login")
    Call<Base<UserInfo>> login(
                               @Field("data") String data,
                               @Field("sign") String sign

    );
    /**
     * 退出登录
     */
    @FormUrlEncoded
    @POST("store/api.member.center/logout")
    Call<Base> loginOut(@Field("from_type") String from_type,
                     @Field("token") String token,
                     @Field("mid") String mid,
                     @Field("sign") String sign

    );



    /**
     * 获取收货地址
     */
    @FormUrlEncoded
    @POST("store/api.member.address/gets")
    Call<AdressBean> getAdressList(@Field("from_type") String fromType,@Field("token") String token,
                                   @Field("mid") String mid,
                                   @Field("sign") String sign);



    /**
     * 添加收货地址
     */
    @FormUrlEncoded
    @POST("store/api.member.address/set")
    Call<ResponseBody> address(@Field("from_type") String fromType, @Field("token") String token,
                               @Field("mid") String mid,
                               @Field("name") String name,
                               @Field("phone") String phone,
                               @Field("province") String province,
                               @Field("city") String city,
                               @Field("area") String area,
                               @Field("address") String address,
                               @Field("is_default") String is_default,
                               @Field("sign") String sign);


    /**
     * 微信登录
     */
    @FormUrlEncoded
    @POST("store/api.User/wxlogin")
    Call<Base<UserInfo>> checkUserInfo(
                                       @Field("unionId") String unionId,
                                       @Field("openId") String openId,
                                       @Field("avatarUrl") String avatarUrl,
                                       @Field("nickname") String nickname,
                                       @Field("from_type") String from_type,
                                       @Field("gender") String gender,
                                       @Field("sign") String sign
    );
    /**
     * 获取微信授权token
     */
    @GET("https://api.weixin.qq.com/sns/oauth2/access_token")
    Call<ResponseBody> getWxToken(@Query("appid") String appId,
                                  @Query("secret") String secret,
                                  @Query("code") String code,
                                  @Query("grant_type") String grantType);
    /**
     * 获取微信账号信息
     */
    @GET("https://api.weixin.qq.com/sns/userinfo")
    Call<ResponseBody> getWxInfo(@Query("access_token") String accessToken,
                                 @Query("openid") String openId);


    /**
     * 微信绑定手机
     */
    @FormUrlEncoded
    @POST("store/api.user/checkphone")
    Call<Base> wxBindPhone(
                                     @Field("phone") String phone,
                                     @Field("sign") String sign
                                   );


}


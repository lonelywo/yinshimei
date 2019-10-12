package com.cuci.enticement.network.api;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.POST;


public interface MineApi {



    /**
     * 获取会员佣金统计
     */
    @FormUrlEncoded
    @POST("store/api.member.order/profit")
    Call<ResponseBody> hqcommissiontj(@Field("token") String token,
                                      @Field("mid") String mid,
                                      @Field("from_type") String from_type,
                                      @Field("sign") String sign
    );
    /**
     * 获取会员佣金记录
     */
    @FormUrlEncoded
    @POST("store/api.member.order/profitList")
    Call<ResponseBody> hqcommissionjl(@Field("token") String token,
                              @Field("mid") String mid,
                              @Field("from_type") String from_type,
                              @Field("date") String date,
                              @Field("sign") String sign
    );
    /**
     * 提交佣金提现申请
     */
    @FormUrlEncoded
    @POST("store/api.member.order/used")
    Call<ResponseBody> txcommissionsq(@Field("token") String token,
                              @Field("mid") String mid,
                              @Field("from_type") String from_type,
                              @Field("price") String price,
                              @Field("sign") String sign
    );
    /**
     * 获取佣金提现记录
     */
    @FormUrlEncoded
    @POST("store/api.member.order/usedList")
    Call<ResponseBody> txcommissionjl(@Field("token") String token,
                              @Field("mid") String mid,
                              @Field("from_type") String from_type,
                              @Field("sign") String sign
    );

    /**
     * 获取团队统计列表
     */
    @FormUrlEncoded
    @POST("store/api.member.team/getps")
    Call<ResponseBody> hqteamtj(@Field("token") String token,
                                @Field("mid") String mid,
                                @Field("from_type") String from_type,
                                @Field("nickname") String nickname,
                                @Field("page") String page,
                                @Field("sign") String sign
    );
    /**
     * 获取隔代团队统计列表
     */
    @FormUrlEncoded
    @POST("store/api.member.team/getps")
    Call<ResponseBody> hqteamtj2(@Field("token") String token,
                                @Field("mid") String mid,
                                @Field("from_type") String from_type,
                                @Field("pid") String pid,
                                @Field("nickname") String nickname,
                                @Field("page") String page,
                                @Field("sign") String sign
    );
    /**
     * 获取团队数量统计
     */
    @FormUrlEncoded
    @POST("store/api.member.team/count")
    Call<ResponseBody> hqteamsl(@Field("token") String token,
                              @Field("mid") String mid,
                              @Field("from_type") String from_type,
                              @Field("sign") String sign
    );
    /**
     * 业绩月返
     */
    @FormUrlEncoded
    @POST("store/api.member.month/get")
    Call<ResponseBody> achievement(@Field("token") String token,
                        @Field("mid") String mid,
                        @Field("from_type") String from_type,
                        @Field("sign") String sign
    );

    /**
     * 环信注册
     */
    @FormUrlEncoded
    @POST("store/api.member.easemob/easemobReg")
    Call<ResponseBody> hxreg(
            @Field("phone") String phone,
            @Field("from_type") String from_type,
            @Field("token") String token,
            @Field("mid") String mid,
            @Field("sign") String sign
    );
    /**
     * PK排行榜1
     */
    @FormUrlEncoded
    @POST("store/api.member.Ranking/amountList")
    Call<ResponseBody> pk1(
            @Field("token") String token,
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("page") String page,
            @Field("sign") String sign
    );
    /**
     * PK排行榜2
     */
    @FormUrlEncoded
    @POST("store/api.member.Ranking/amountDailyList")
    Call<ResponseBody> pk2(
            @Field("token") String token,
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("page") String page,
            @Field("sign") String sign
    );
    /**
     * PK排行榜3
     */
    @FormUrlEncoded
    @POST("store/api.member.Ranking/teamList")
    Call<ResponseBody> pk3(
            @Field("token") String token,
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("page") String page,
            @Field("sign") String sign
    );
    /**
     * 499礼包
     */
    @FormUrlEncoded
    @POST("store/api.member.center/ispay")
    Call<ResponseBody> bag499(
            @Field("token") String token,
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("sign") String sign
    );
}


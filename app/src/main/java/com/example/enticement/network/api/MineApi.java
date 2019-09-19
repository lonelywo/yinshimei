package com.example.enticement.network.api;


import com.example.enticement.bean.Base;


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
    Call<Base> hqcommissiontj(@Field("token") String token,
                           @Field("mid") String mid,
                           @Field("from_type") String from_type,
                           @Field("sign") String sign
    );
    /**
     * 获取会员佣金记录
     */
    @FormUrlEncoded
    @POST("store/api.member.order/profitList")
    Call<Base> hqcommissionjl(@Field("token") String token,
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
    Call<Base> txcommissionsq(@Field("token") String token,
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
    Call<Base> txcommissionjl(@Field("token") String token,
                              @Field("mid") String mid,
                              @Field("from_type") String from_type,
                              @Field("date") String date,
                              @Field("sign") String sign
    );

    /**
     * 获取团队统计列表
     */
    @FormUrlEncoded
    @POST("store/api.member.team/getps")
    Call<Base> hqteamtj(@Field("token") String token,
                              @Field("mid") String mid,
                              @Field("from_type") String from_type,
                              @Field("nickname") String nickname,
                              @Field("page") String page,
                              @Field("sign") String sign
    );
    /**
     * 获取团队数量统计
     */
    @FormUrlEncoded
    @POST("store/api.member.team/count")
    Call<Base> hqteamsl(@Field("token") String token,
                              @Field("mid") String mid,
                              @Field("from_type") String from_type,
                              @Field("sign") String sign
    );
    /**
     * 业绩月返
     */
    @FormUrlEncoded
    @POST("store/api.member.month/get")
    Call<Base> achievement(@Field("token") String token,
                        @Field("mid") String mid,
                        @Field("from_type") String from_type,
                        @Field("sign") String sign
    );
}


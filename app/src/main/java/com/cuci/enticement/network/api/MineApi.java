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
                              @Field("page") String page,
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
     * 绑定微信
     */
    @FormUrlEncoded
    @POST("store/api.member.center/wxBingingInfo")
    Call<ResponseBody> bindwx(
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("token") String token,
            @Field("unionId") String unionId,
            @Field("openId") String openId,
            @Field("avatarUrl") String avatarUrl,
            @Field("nickname") String nickname,
            @Field("sign") String sign
    );
    /**
     * 解绑微信
     */
    @FormUrlEncoded
    @POST("store/api.member.center/wxUntied")
    Call<ResponseBody> jiebindwx(
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("token") String token,
            @Field("sign") String sign
    );
    /**
     * 关于因诗美
     */
    @FormUrlEncoded
    @POST("store/api.page/about")
    Call<ResponseBody> ysm(
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("token") String token,
            @Field("sign") String sign
    );
    /**
     * 当前用户信息
     */
    @FormUrlEncoded
    @POST("store/api.member.center/member")
    Call<ResponseBody> dataUserinfo(
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("token") String token,
            @Field("sign") String sign
    );
    /**
     * 消费月返提交地址
     */
    @FormUrlEncoded
    @POST("store/api.member.month/monthaddress")
    Call<ResponseBody> monbackdizi(
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("token") String token,
            @Field("address_id") String address_id,
            @Field("sign") String sign
    );
    /**
     * 个推
     */
    @FormUrlEncoded
    @POST("store/api.push/getClientID")
    Call<ResponseBody> getui(
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("token") String token,
            @Field("aims") String aims,
            @Field("cid") String cid,
            @Field("sign") String sign
    );
    /**
     * 用户条款
     */
    @FormUrlEncoded
    @POST("store/api.page/protocol")
    Call<ResponseBody> clause(
            @Field("from_type") String from_type,
            @Field("sign") String sign
    );
    /**
     * 绑定推荐人
     */
    @FormUrlEncoded
    @POST("store/api.member.center/agent")
    Call<ResponseBody> bindTuijian(@Field("token") String token,
                                      @Field("mid") String mid,
                                      @Field("from_type") String from_type,
                                      @Field("phone") String phone,
                                      @Field("sign") String sign
    );
    /**
     * 领取列表
     */
    @FormUrlEncoded
    @POST("store/api.activity.gift")
    Call<ResponseBody> ReceiveQueue(@Field("token") String token,
                                   @Field("mid") String mid,
                                   @Field("from_type") String from_type,
                                   @Field("get_state") String get_state,
                                   @Field("page") String page,
                                   @Field("sign") String sign
    );
    /**
     * 领取提交
     */
    @FormUrlEncoded
    @POST("store/api.activity.getAllgift")
    Call<ResponseBody> ReceiveCommit(@Field("token") String token,
                                   @Field("mid") String mid,
                                   @Field("from_type") String from_type,
                                   @Field("address_id") String address_id,
                                   @Field("sign") String sign
    );
}


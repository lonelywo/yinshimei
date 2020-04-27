package com.cuci.enticement.network.api;

import com.cuci.enticement.bean.HomeDetailsBean;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface MineApi {

    /**
     * 获取积分订单详情
     */
    @FormUrlEncoded
    @POST("store/api.member.points/order")
    Call<ResponseBody> jifenxiangqing(@Field("from_type") String from_type,
                                   @Field("token") String token,
                                   @Field("mid") String mid,
                                   @Field("order_no") String order_no,
                                   @Field("new_version") String new_version,
                                   @Field("sign") String sign
    );


    /**
     * 积分订单列表
     */
    @FormUrlEncoded
    @POST("store/api.member.points/orderList")
    Call<ResponseBody> JiFenOrderList(
            @Field("token") String token,
            @Field("mid") String mid,
            @Field("from_type") String from_type,
            @Field("status") String status,
            @Field("page") String page,
            @Field("page_size") String page_size,
            @Field("new_version") String new_version,
            @Field("sign") String sign
    );

    /**
     * 分享海报得积分
     */
    @FormUrlEncoded
    @POST("store/api.member.signin/signingetintegral")
    Call<ResponseBody> sharehaibao(@Field("from_type") String from_type,
                               @Field("token") String token,
                               @Field("mid") String mid,
                               @Field("new_version") String new_version,
                               @Field("sign") String sign
    );
    /**
     * 签到
     */
    @FormUrlEncoded
    @POST("store/api.member.signin/registering")
    Call<ResponseBody> qiandao(@Field("from_type") String from_type,
                                     @Field("token") String token,
                                     @Field("mid") String mid,
                                     @Field("new_version") String new_version,
                                     @Field("sign") String sign
    );

    /**
     * 获取商品详情
     */
    @FormUrlEncoded
    @POST("store/api.member.points/goods")
    Call<ResponseBody> getJiFenDetails(@Field("from_type") String from_type,
                                         @Field("mid") String mid,
                                         @Field("token") String token,
                                         @Field("goods_id") String goods_id,
                                         @Field("new_version") String new_version,
                                         @Field("sign") String sign

    );
    /**
     * 积分商城
     */
    @FormUrlEncoded
    @POST("store/api.member.points/goodsList")
    Call<ResponseBody> JiFenShangChengList(
            @Field("token") String token,
            @Field("mid") String mid,
            @Field("from_type") String from_type,
            @Field("page") String page,
            @Field("page_size") String page_size,
            @Field("new_version") String new_version,
            @Field("sign") String sign
    );
    /**
     * 积分明细
     */
    @FormUrlEncoded
    @POST("store/api.member.points/record")
    Call<ResponseBody> JiFenMingXiList(
                                  @Field("token") String token,
                                  @Field("mid") String mid,
                                  @Field("from_type") String from_type,
                                  @Field("page") String page,
                                  @Field("page_size") String page_size,
                                  @Field("new_version") String new_version,
                                  @Field("sign") String sign
    );


    /**
     * 图片上传
     */
    @Multipart
    @POST("store/api.page/upload")
    Call<ResponseBody> upload(
                              @Part List<MultipartBody.Part> list
           );
    /**
     * 申请退款
     */
    @FormUrlEncoded
    @POST("store/api.member.refund/apply")
    Call<ResponseBody> SQtuikuan(
            @Field("token") String token,
            @Field("mid") String mid,
            @Field("from_type") String from_type,
            @Field("order_no") String order_no,
            @Field("item_id") String item_id,
            @Field("type") String type,
            @Field("goods_status") String goods_status,
            @Field("reason") String reason,
            @Field("desc") String desc,
            @Field("image") String image,
            @Field("new_version") String new_version,
            @Field("sign") String sign
    );

    /**
     * 会员优惠券列表
     */
    @FormUrlEncoded
    @POST("store/api.member.coupon/get")
    Call<ResponseBody> kaquanlist(
                                  @Field("token") String token,
                                  @Field("mid") String mid,
                                  @Field("from_type") String from_type,
                                  @Field("page") String page,
                                  @Field("page_size") String page_size,
                                  @Field("status") String status,
                                  @Field("new_version") String new_version,
                                  @Field("sign") String sign
    );

    /**
     * 公告详情
     */
    @FormUrlEncoded
    @POST("store/api.placard/get")
    Call<ResponseBody> Noticecontent(@Field("from_type") String from_type,
                                  @Field("new_version") String new_version,
                                  @Field("nid") String nid,
                                  @Field("sign") String sign
    );
    /**
     * 公告列表
     */
    @FormUrlEncoded
    @POST("store/api.Placard/announce")
    Call<ResponseBody> Noticelist(@Field("from_type") String from_type,
                                      @Field("page") String page,
                                  @Field("new_version") String new_version,
                                      @Field("sign") String sign
    );

    /**
     * 获取会员佣金统计
     */
    @FormUrlEncoded
    @POST("store/api.member.order/profit")
    Call<ResponseBody> hqcommissiontj(@Field("token") String token,
                                      @Field("mid") String mid,
                                      @Field("from_type") String from_type,
                                      @Field("new_version") String new_version,
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
                                      @Field("new_version") String new_version,
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
                                      @Field("new_version") String new_version,
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
                                      @Field("new_version") String new_version,
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
                                @Field("new_version") String new_version,
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
                                 @Field("new_version") String new_version,
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
                                @Field("new_version") String new_version,
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
                                   @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
            @Field("sign") String sign
    );
    /**
     * 是否有优惠卷可以领
     */
    @FormUrlEncoded
    @POST("store/api.page/acceptCoupon")
    Call<ResponseBody> isyhjdailing(
            @Field("from_type") String from_type,
            @Field("mid") String mid,
            @Field("token") String token,
            @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
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
            @Field("new_version") String new_version,
            @Field("sign") String sign
    );
    /**
     * 用户条款
     */
    @FormUrlEncoded
    @POST("store/api.page/protocol")
    Call<ResponseBody> clause(
            @Field("from_type") String from_type,
            @Field("new_version") String new_version,
            @Field("sign") String sign
    );
    /**
     * 开屏广告
     */
    @FormUrlEncoded
    @POST("store/api.page/adshow")
    Call<ResponseBody> openScreen(
            @Field("from_type") String from_type,
            @Field("new_version") String new_version,
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
                                   @Field("new_version") String new_version,
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
                                    @Field("new_version") String new_version,
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
                                     @Field("new_version") String new_version,
                                   @Field("sign") String sign
    );
}


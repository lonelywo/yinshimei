package com.cuci.enticement.network.api;

import com.cuci.enticement.bean.OrderStatistics;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderApi {



    /**
     * 获取订单列表
     */
    @FormUrlEncoded
    @POST("store/api.member.order/gets")
    Call<ResponseBody> getOrderList(@Field("from_type") String fromType,@Field("token") String token,
                                 @Field("mid") String mid,
                                 @Field("page") String page,
                                 @Field("status") String status,
                                 @Field("order_no") String orderNum,
                                    @Field("new_version") String new_version,
                                 @Field("sign") String signs);


    /**
     * 确认收货
     */
    @FormUrlEncoded
    @POST("store/api.member.order/confirm")
    Call<ResponseBody> confirmOrder(@Field("from_type") String fromType,@Field("token") String token,
                                 @Field("mid") String mid,
                                 @Field("order_no") String orderNum,
                                    @Field("new_version") String new_version,
                                   @Field("sign") String signs);


    /**
     * 取消订单
     */
    @FormUrlEncoded
    @POST("store/api.member.order/cancel")
    Call<ResponseBody> cancelOrder(@Field("from_type") String fromType,@Field("token") String token,
                                   @Field("mid") String mid,
                                   @Field("order_no") String orderNum,
                                   @Field("new_version") String new_version,
                                  @Field("sign") String signs);



    /**
     * 获取会员订单统计
     */
    @FormUrlEncoded
    @POST("store/api.member.order/total")
    Call<OrderStatistics> getTotalOrder(@Field("from_type") String fromType,@Field("token") String token,
                                      @Field("mid") String mid,
                                        @Field("new_version") String new_version,
                                        @Field("sign") String signs);

    /**
     * 快递信息
     * //快递单号   快递公司编号
     */
    @FormUrlEncoded
    @POST("store/api.express/query")
    Call<ResponseBody> getExpressInfo(@Field("express_no") String num,
                                 @Field("express_code") String code,
                                      @Field("new_version") String new_version,
                                     @Field("sign") String signs);




    /**
     * 查询订单所需邮费
     */
    @FormUrlEncoded
    @POST("store/api.member.order/express")
    Call<ResponseBody> getExpressCost(@Field("from_type") String fromType,@Field("token") String token,
                                         @Field("mid") String mid,
                                        // @Field("order_no") String orderNum,
                                         @Field("number") String number,
                                         @Field("priceGoods") String priceGoods,
                                         @Field("address_id") String addressId,
                                      @Field("new_version") String new_version,
                                          @Field("sign") String signs);


    /**
     * 获取订单支付参数
     */
    @FormUrlEncoded
    @POST("store/api.member.order/pay")
    Call<ResponseBody> getOrderPay(@Field("from_type") String fromType,@Field("token") String token,
                               @Field("mid") String mid,
                               @Field("order_no") String orderNum,
                               @Field("pay_type") String payType,
                                   @Field("new_version") String new_version,
                               @Field("sign") String signs);



    /**
     * 点支付后调用这个接口，成功返回后再调取支付参数接口
     * 商城订单补全确认
     * 补全或者修改地址确认
     */
    @FormUrlEncoded
    @POST("store/api.member.order/set")
    Call<ResponseBody> udpateAdress(@Field("from_type") String fromType, @Field("token") String token,
                                    @Field("mid") String mid,
                                    @Field("m_coupon_id") String m_coupon_id,
                                    @Field("rule") String rule,
                                    @Field("address_id") String addressId,
                                    @Field("new_version") String new_version,
                                    @Field("sign") String signs);





}

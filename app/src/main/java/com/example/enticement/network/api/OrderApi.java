package com.example.enticement.network.api;


import com.example.enticement.bean.CartListBean;
import com.example.enticement.bean.ExpressInfo;
import com.example.enticement.bean.OrderExpressCost;
import com.example.enticement.bean.OrderList;
import com.example.enticement.bean.OrderPay;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.OrderStatistics;

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
    Call<OrderList> getOrderList(@Field("openid") String openId,
                                 @Field("mid") String mid,
                                 @Field("page") String page,
                                 @Field("status") String status,
                                 @Field("order_no") String orderNum,
                                 @Field("sign") String signs);


    /**
     * 立即支付，确认支付
     */
    @FormUrlEncoded
    @POST("store/api.member.order/confirm")
    Call<OrderResult> confirmOrder(@Field("openid") String openId,
                                 @Field("mid") String mid,
                                 @Field("order_no") String orderNum,
                                   @Field("sign") String signs);


    /**
     * 取消订单
     */
    @FormUrlEncoded
    @POST("store/api.member.order/cancel")
    Call<OrderResult> cancelOrder(@Field("openid") String openId,
                                   @Field("mid") String mid,
                                   @Field("order_no") String orderNum,
                                  @Field("sign") String signs);



    /**
     * 获取会员订单统计
     */
    @FormUrlEncoded
    @POST("/store/api.member.order/total")
    Call<OrderStatistics> getTotalOrder(@Field("openid") String openId,
                                      @Field("mid") String mid,
                                        @Field("sign") String signs);

    /**
     * 快递信息
     * //快递单号   快递公司编号
     */
    @FormUrlEncoded
    @POST("/store/api.member.order/total")
    Call<ExpressInfo> getExpressInfo(@Field("express_no") String num,
                                 @Field("express_code") String code,
                                     @Field("sign") String signs);




    /**
     * 查询订单所需邮费
     */
    @FormUrlEncoded
    @POST("/store/api.member.order/express")
    Call<OrderExpressCost> getExpressCost(@Field("openid") String openId,
                                         @Field("mid") String mid,
                                         @Field("order_no") String orderNum,
                                         @Field("address_id") String addressId,
                                          @Field("sign") String signs);


    /**
     * 获取订单支付参数
     */
    @FormUrlEncoded
    @POST("store/api.member.order/pay")
    Call<OrderPay> getOrderPay(@Field("openid") String openId,
                               @Field("mid") String mid,
                               @Field("order_no") String orderNum,
                               @Field("pay_type") String payType,
                               @Field("sign") String signs);



    /**
     * 商城订单补全确认
     * 补全或者修改地址确认
     */
    @FormUrlEncoded
    @POST("store/api.member.order/perfect")
    Call<OrderPay> udpateAdress(@Field("openid") String openId,
                       @Field("mid") String mid,
                       @Field("order_no") String orderNum,
                       @Field("address_id") String addressId,
                                @Field("sign") String signs);





}

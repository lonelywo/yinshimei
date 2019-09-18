package com.example.enticement.network.api;


import com.example.enticement.bean.Base;
import com.example.enticement.bean.CartListBean;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.Version;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CartApi {



    /**
     * 获取购物车列表
     */
    @FormUrlEncoded
    @POST("store/api.member.Cart/cartList")
    Call<CartListBean> getCartList(@Field("openid") String openId,
                                   @Field("mid") String mid,
                                   @Field("page") String page,  @Field("sign") String sign);


    /**
     * 提交购物车订单
     */
    @FormUrlEncoded
    @POST("store/api.member.order/set")
    Call<OrderResult> commitOrder(@Field("openid") String openId,
                                  @Field("mid") String mid,
                                  @Field("rule") String rule,
                                  @Field("from_mid") String fromMid,  @Field("sign") String sign);





    /**
     * 购物车修改
     */
    @FormUrlEncoded
    @POST("store/api.member.Cart/dealBuy")
    Call<CartListBean> cartChange(@Field("token") String token,
                                  @Field("mid") String mid,
                                  @Field("goods_id") String goodsId,
                                  @Field("goods_spec") String goodsSpec,
                                  @Field("goods_num") String goodsNum,
                                  @Field("sign") String sign

    );

    /**
     * 购物车删除
     */
    @FormUrlEncoded
    @POST("store/api.member.Cart/del")
    Call<CartListBean> cartDelete(@Field("token") String openId,
                                 @Field("mid") String mid,
                                 @Field("cart_id") String cartId,  @Field("sign") String sign);

}

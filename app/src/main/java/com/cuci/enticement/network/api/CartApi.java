package com.cuci.enticement.network.api;


import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.CartDataBean;
import com.cuci.enticement.bean.CartListBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CartApi {



    /**
     * 获取购物车列表
     */
    @FormUrlEncoded
    @POST("store/api.member.Cart/cartList")
    Call<Base<CartDataBean>> getCartList(@Field("from_type") String fromType, @Field("token") String openId,
                                         @Field("mid") String mid,
                                         @Field("page") String page, @Field("sign") String sign);


    /**
     * 提交购物车订单  预定单
     */
    @FormUrlEncoded
    @POST("store/api.member.order/set")
    Call<ResponseBody> commitOrder(@Field("from_type") String fromType, @Field("token") String token,
                                   @Field("mid") String mid,
                                   @Field("rule") String rule,
                                   @Field("from_mid") String fromMid, @Field("sign") String sign);





    /**
     * 购物车修改
     */
    @FormUrlEncoded
    @POST("store/api.member.Cart/dealBuy")
    Call<ResponseBody> cartChange(@Field("from_type") String from_type,@Field("token") String token,
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
    Call<ResponseBody> cartDelete(@Field("from_type") String fromType,@Field("token") String token,
                                 @Field("mid") String mid,
                                 @Field("cart_id") String cartId,  @Field("sign") String sign);

    //
    /**
     * 购物车数量
     */
    @FormUrlEncoded
    @POST("store/api.member.Cart/cartNum")
    Call<ResponseBody> cartNum(@Field("from_type") String fromType,@Field("token") String token,
                                  @Field("mid") String mid, @Field("sign") String sign);
}

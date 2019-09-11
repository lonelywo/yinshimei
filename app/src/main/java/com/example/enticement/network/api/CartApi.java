package com.example.enticement.network.api;


import com.example.enticement.bean.Base;
import com.example.enticement.bean.CartListBean;
import com.example.enticement.bean.Version;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CartApi {



    /**
     * 获取版本更新信息
     */
    @FormUrlEncoded
    @POST("store/api.member.Cart/cartList")
    Call<CartListBean> getCartList(@Field("openid") String openId,
                                   @Field("mid") String mid,
                                   @Field("page") String page);


}

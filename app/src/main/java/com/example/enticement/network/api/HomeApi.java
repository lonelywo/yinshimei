package com.example.enticement.network.api;

import com.example.enticement.bean.BannerDataBean;

import com.example.enticement.bean.BaseList;
import com.example.enticement.bean.GeneralGoods;

import com.example.enticement.bean.HomeDetailsBean;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HomeApi {
    /**
     * 获取首页轮播图
     */
    @FormUrlEncoded
    @POST("store/api.page/slider")
    Call<BaseList<BannerDataBean>> getBanner(  @Field("sign") String sign);
    /**
     * 获取普通商品
     */
    @FormUrlEncoded
    @POST("store/api.goods/gets")
    Call<GeneralGoods> getGeneralGoods(@Field("sign") String sign);
    /**
     * 获取商品详情
     */
    @FormUrlEncoded
    @POST("store/api.goods/get")
    Call<HomeDetailsBean> getHomeDetails(@Field("goods_id") String goods_id,
                                         @Field("sign") String sign

    );

}

package com.cuci.enticement.network.api;

import com.cuci.enticement.bean.BannerDataBean;

import com.cuci.enticement.bean.BaseList;
import com.cuci.enticement.bean.GeneralGoods;

import com.cuci.enticement.bean.HomeDetailsBean;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    Call<GeneralGoods> getGeneralGoods(@Field("from_type") String from_type,
                                       @Field("mid") String mid,
                                       @Field("token") String token,
                                       @Field("sign") String sign

    );
    /**
     * 获取商品详情
     */
    @FormUrlEncoded
    @POST("store/api.goods/get")
    Call<HomeDetailsBean> getHomeDetails(@Field("from_type") String from_type,
                                         @Field("mid") String mid,
                                         @Field("token") String token,
                                         @Field("goods_id") String goods_id,
                                         @Field("sign") String sign

    );
    /**
     * 海报
     */
    @FormUrlEncoded
    @POST("store/api.member.center/sharePic")
    Call<ResponseBody> shareimg(@Field("from_type") String from_type,
                                      @Field("mid") String mid,
                                      @Field("token") String token,
                                      @Field("goods_id") String goods_id,
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
}

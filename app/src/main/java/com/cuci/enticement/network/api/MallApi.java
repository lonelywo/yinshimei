package com.cuci.enticement.network.api;



import com.cuci.enticement.bean.MallSourceBean;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MallApi {

    /**
     * 获取商城素材
     */
    @FormUrlEncoded
    @POST("store/api.media/gets")
    Call<MallSourceBean> getSource(@Field("type") String type,
                                   @Field("page") String page,
                                   @Field("pagesize") String pagesize,
                                   @Field("sign") String sign

    );
    /**
     * 获取商城素材内容
     */
    @FormUrlEncoded
    @POST("store/api.media/gets")
    Call<MallSourceBean> getSource01(@Field("type") String type,
                                     @Field("page") String page,
                                     @Field("pagesize") String pagesize,
                                     @Field("sign") String sign
                                    );
}

package com.cuci.enticement.network.api;



import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.HomeDetailsBean;
import com.cuci.enticement.bean.Version;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CommonApi {




    /**
     * 获取版本更新信息
     */
    @FormUrlEncoded
    @POST("store/api.page/appVersion")
    Call<Base<Version>> getVersion1(@Field("is_force") boolean is_force,
                                         @Field("version") int version,
                                         @Field("versionName") String versionName,
                                         @Field("url") String url,
                                         @Field("content") String content

    );

    /**
     * 获取版本更新信息
     */
    @FormUrlEncoded
    @POST("store/api.page/appVersion")
    Call<Base<Version>> getVersion(


    );
}

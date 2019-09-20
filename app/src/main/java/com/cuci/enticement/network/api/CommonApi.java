package com.cuci.enticement.network.api;



import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.Version;

import retrofit2.Call;

import retrofit2.http.GET;

public interface CommonApi {



    /**
     * 获取版本更新信息
     */

    @GET ("Appsetting/check_version")
    Call<Base<Version>> getVersion();




}

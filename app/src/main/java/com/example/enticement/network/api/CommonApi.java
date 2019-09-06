package com.example.enticement.network.api;



import com.example.enticement.bean.Base;
import com.example.enticement.bean.Version;

import retrofit2.Call;

import retrofit2.http.GET;

public interface CommonApi {



    /**
     * 获取版本更新信息
     */

    @GET ("Appsetting/check_version")
    Call<Base<Version>> getVersion();


}

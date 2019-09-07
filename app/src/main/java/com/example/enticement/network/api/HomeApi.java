package com.example.enticement.network.api;

import com.example.enticement.bean.BannerDataBean;
import com.example.enticement.bean.Base;
import com.example.enticement.bean.BaseList;
import com.example.enticement.bean.ItemBanner;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface HomeApi {
    /**
     * 获取首页轮播图
     */

    @GET("store/api.page/slider")
    Call<BaseList<BannerDataBean>> getBanner();


}

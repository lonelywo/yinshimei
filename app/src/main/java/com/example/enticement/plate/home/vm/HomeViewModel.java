package com.example.enticement.plate.home.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.enticement.bean.BannerDataBean;
import com.example.enticement.bean.Base;
import com.example.enticement.bean.BaseList;


import com.example.enticement.bean.GeneralGoods;
import com.example.enticement.bean.Status;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.HomeApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public HomeViewModel() {
        mCreator = ServiceCreator.getInstance();
    }
    public MutableLiveData<Status<BaseList<BannerDataBean>>> getBanner() {

        final MutableLiveData<Status<BaseList<BannerDataBean>>> data = new MutableLiveData<>();

        mCreator.create(HomeApi.class)
                .getBanner()
                .enqueue(new Callback<BaseList<BannerDataBean>>() {

                    @Override
                    public void onResponse(Call<BaseList<BannerDataBean>> call, Response<BaseList<BannerDataBean>> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(Call<BaseList<BannerDataBean>> call, Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() ==
                                null ? "加载失败" : t.getMessage()));
                    }
                });
        return data;
    }
    public MutableLiveData<Status<GeneralGoods>> getGeneralGoods(int minId, int loadType) {

        final MutableLiveData<Status<GeneralGoods>> liveData = new MutableLiveData<>();

        mCreator.create(HomeApi.class)
                .getGeneralGoods()
                .enqueue(new Callback<GeneralGoods>() {

                    @Override
                    public void onResponse(Call<GeneralGoods> call, Response<GeneralGoods> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            liveData.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            liveData.setValue(Status.moreSuccess(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralGoods>call, Throwable t) {
                        if (loadType == Status.LOAD_REFRESH) {
                            liveData.setValue(Status.refreshError(null, t.getMessage() ==
                                    null ? "加载失败" : t.getMessage()));
                        } else {
                            liveData.setValue(Status.moreError(null, t.getMessage() ==
                                    null ? "加载失败" : t.getMessage()));
                        }
                    }
                });
        return liveData;
    }

}

package com.example.enticement.plate.home.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.enticement.bean.BannerDataBean;
import com.example.enticement.bean.Base;
import com.example.enticement.bean.BaseList;


import com.example.enticement.bean.CartListBean;
import com.example.enticement.bean.GeneralGoods;
import com.example.enticement.bean.HomeDetailsBean;
import com.example.enticement.bean.Status;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.CartApi;
import com.example.enticement.network.api.HomeApi;
import com.example.enticement.utils.EncryptUtils;
import com.example.enticement.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

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
        String sign = EncryptUtils.md5Encrypt("&key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        String signs = sign.toUpperCase();
        mCreator.create(HomeApi.class)
                .getBanner(signs)
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
        String sign = EncryptUtils.md5Encrypt("&key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        String signs = sign.toUpperCase();
        mCreator.create(HomeApi.class)
                .getGeneralGoods(signs)
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
    public MutableLiveData<Status<HomeDetailsBean>> getHomeDetails(String goods_id) {

        final MutableLiveData<Status<HomeDetailsBean>> data = new MutableLiveData<>();
        String  stringA = "goods_id="+goods_id;
        String sign = EncryptUtils.md5Encrypt(stringA+"&key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        String signs = sign.toUpperCase();
        mCreator.create(HomeApi.class)
                .getHomeDetails(goods_id,signs)
                .enqueue(new Callback<HomeDetailsBean>() {

                    @Override
                    public void onResponse(Call<HomeDetailsBean> call, Response<HomeDetailsBean> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(Call<HomeDetailsBean> call, Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() ==
                                null ? "加载失败" : t.getMessage()));
                    }
                });
        return data;
    }
    public MutableLiveData<Status<Base>> getCartChange(String token,String mid,String goods_id,String goods_spec,String goods_num) {

        final MutableLiveData<Status<Base>> data = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("goods_id",goods_id);
        params.put("goods_spec",goods_spec);
        params.put("goods_num",goods_num);
        params.put("from_type","2");
        String signs = SignUtils.signParam(params);
        mCreator.create(CartApi.class)
                .cartChange("2",token,mid,goods_id,goods_spec,goods_num,signs)
                .enqueue(new Callback<Base>() {

                    @Override
                    public void onResponse(Call<Base> call, Response<Base> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(Call<Base> call, Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() ==
                                null ? "加载失败" : t.getMessage()));
                    }
                });
        return data;
    }








}

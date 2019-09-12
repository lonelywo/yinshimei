package com.example.enticement.plate.cart.vm;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.enticement.bean.Base;
import com.example.enticement.bean.CartListBean;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.Status;
import com.example.enticement.bean.Version;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.CartApi;
import com.example.enticement.network.api.CommonApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public CartViewModel() {
        mCreator = ServiceCreator.getInstance();
    }



    public MutableLiveData<Status<CartListBean>> getCartList(String openId,String mid,String page,int loadType) {

        final MutableLiveData<Status<CartListBean>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        mCreator.create(CartApi.class)
                .getCartList(openId,mid,page)
                .enqueue(new Callback<CartListBean>() {
                    @Override
                    public void onResponse(@NonNull Call<CartListBean> call,
                                           @NonNull Response<CartListBean> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            data.setValue(Status.moreSuccess(response.body()));
                        }


                    }

                    @Override
                    public void onFailure(@NonNull Call<CartListBean> call,
                                          @NonNull Throwable t) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshError(null, t.getMessage() ==
                                    null ? "加载失败" : t.getMessage()));
                        } else {
                            data.setValue(Status.moreError(null, t.getMessage() ==
                                    null ? "加载失败" : t.getMessage()));
                        }
                    }
                });
        return data;
    }



    public MutableLiveData<Status<CartListBean>> cartChange(String openId,String mid,String goodsId,String goodsSpec,String goodsNum) {

        final MutableLiveData<Status<CartListBean>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        mCreator.create(CartApi.class)
                .cartChange(openId,mid,goodsId,goodsSpec,goodsNum)
                .enqueue(new Callback<CartListBean>() {
                    @Override
                    public void onResponse(@NonNull Call<CartListBean> call,
                                           @NonNull Response<CartListBean> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<CartListBean> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }


    public MutableLiveData<Status<CartListBean>> cartDelete(String openId,String mid,String cartId) {

        final MutableLiveData<Status<CartListBean>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        mCreator.create(CartApi.class)
                .cartDelete(openId,mid,cartId)
                .enqueue(new Callback<CartListBean>() {
                    @Override
                    public void onResponse(@NonNull Call<CartListBean> call,
                                           @NonNull Response<CartListBean> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<CartListBean> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }



    public MutableLiveData<Status<OrderResult>> commitOrder(String openId,String mid,String rule,String fromMid) {

        final MutableLiveData<Status<OrderResult>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        mCreator.create(CartApi.class)
                .commitOrder(openId,mid,rule,fromMid)
                .enqueue(new Callback<OrderResult>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderResult> call,
                                           @NonNull Response<OrderResult> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderResult> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }



}

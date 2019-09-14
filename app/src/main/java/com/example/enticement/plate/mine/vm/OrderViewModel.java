package com.example.enticement.plate.mine.vm;


import com.example.enticement.bean.CartListBean;
import com.example.enticement.bean.OrderList;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.Status;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.CartApi;
import com.example.enticement.network.api.OrderApi;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public OrderViewModel() {
        mCreator = ServiceCreator.getInstance();
    }



    public MutableLiveData<Status<OrderList>> getOrderList(String openId, String mid, String page,String status,String orderNum, int loadType) {

        final MutableLiveData<Status<OrderList>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        mCreator.create(OrderApi.class)
                .getOrderList(openId,mid,page,status,orderNum)
                .enqueue(new Callback<OrderList>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderList> call,
                                           @NonNull Response<OrderList> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            data.setValue(Status.moreSuccess(response.body()));
                        }


                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderList> call,
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



    /**
     * 确认订单
     * @param openId
     * @param mid
     * @param orderNum
     * @return
     */
    public MutableLiveData<Status<OrderResult>> orderConfirm(String openId,String mid,String orderNum) {

        final MutableLiveData<Status<OrderResult>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        mCreator.create(OrderApi.class)
                .confirmOrder(openId,mid,orderNum)
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


    /**
     * 取消订单
     * @param openId
     * @param mid
     * @param orderNum
     * @return
     */
    public MutableLiveData<Status<OrderResult>> orderCancel(String openId,String mid,String orderNum) {

        final MutableLiveData<Status<OrderResult>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        mCreator.create(OrderApi.class)
                .cancelOrder(openId,mid,orderNum)
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

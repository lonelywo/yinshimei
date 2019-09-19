package com.example.enticement.plate.mine.vm;


import com.example.enticement.bean.CartListBean;
import com.example.enticement.bean.ExpressInfo;
import com.example.enticement.bean.OrderExpressCost;
import com.example.enticement.bean.OrderList;
import com.example.enticement.bean.OrderPay;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.OrderStatistics;
import com.example.enticement.bean.Status;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.CartApi;
import com.example.enticement.network.api.OrderApi;
import com.example.enticement.utils.EncryptUtils;
import com.example.enticement.utils.SignUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public OrderViewModel() {
        mCreator = ServiceCreator.getInstance();
    }



    public MutableLiveData<Status<OrderList>> getOrderList(String token, String mid, String page,String status,String orderNum, int loadType) {

        final MutableLiveData<Status<OrderList>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("page",page);
        params.put("status",status);
        params.put("order_no",orderNum);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getOrderList(token,mid,page,status,orderNum,sign)
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
     * @param token
     * @param mid
     * @param orderNum
     * @return
     */
    public MutableLiveData<Status<OrderResult>> orderConfirm(String token,String mid,String orderNum) {

        final MutableLiveData<Status<OrderResult>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);

        params.put("order_no",orderNum);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .confirmOrder(token,mid,orderNum,sign)
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
     * @param token
     * @param mid
     * @param orderNum
     * @return
     */
    public MutableLiveData<Status<OrderResult>> orderCancel(String token,String mid,String orderNum) {

        final MutableLiveData<Status<OrderResult>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);

        params.put("order_no",orderNum);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .cancelOrder(token,mid,orderNum,sign)
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
     * 提交订单
     * @param token
     * @param mid
     * @param rule
     * @param fromMid
     * @return
     */
    public MutableLiveData<Status<OrderResult>> commitOrder(String token,String mid,String rule,String fromMid) {

        final MutableLiveData<Status<OrderResult>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);

        params.put("rule",rule);
        params.put("from_mid",fromMid);
        params.put("from_type","2");


        String sign = SignUtils.signParam(params);

        mCreator.create(CartApi.class)
                .commitOrder("2",token,mid,rule,fromMid,sign)
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
     * 统计订单状态
     * @return
     */
    public MutableLiveData<Status<OrderStatistics>> getStatisticsOrder(String token, String mid) {

        final MutableLiveData<Status<OrderStatistics>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getTotalOrder(token,mid,sign)
                .enqueue(new Callback<OrderStatistics>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderStatistics> call,
                                           @NonNull Response<OrderStatistics> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderStatistics> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }





    /**
     * 获取快递信息
     * @return
     */
    public MutableLiveData<Status<ExpressInfo>> getExpressInfo(String expressNo, String expressCode) {

        final MutableLiveData<Status<ExpressInfo>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("express_no",expressNo);
        params.put("express_code",expressCode);

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getExpressInfo(expressNo,expressCode,sign)
                .enqueue(new Callback<ExpressInfo>() {
                    @Override
                    public void onResponse(@NonNull Call<ExpressInfo> call,
                                           @NonNull Response<ExpressInfo> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<ExpressInfo> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }





    /**
     * 快递费用
     * @return
     */
    public MutableLiveData<Status<OrderExpressCost>> getExpressCost(String token,String mid,String orderNo,String adressId) {

        final MutableLiveData<Status<OrderExpressCost>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("orderNo",orderNo);
        params.put("adressId",adressId);

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getExpressCost(token,mid,orderNo,adressId,sign)
                .enqueue(new Callback<OrderExpressCost>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderExpressCost> call,
                                           @NonNull Response<OrderExpressCost> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderExpressCost> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }


    /**
     * 获取订单支付参数
     * @return
     */
    public MutableLiveData<Status<OrderPay>> getOrderPay(String token, String mid, String orderNo, String payType) {

        final MutableLiveData<Status<OrderPay>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("orderNo",orderNo);
        params.put("pay_type",payType);

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getOrderPay(token,mid,orderNo,payType,sign)
                .enqueue(new Callback<OrderPay>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderPay> call,
                                           @NonNull Response<OrderPay> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderPay> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }



    /**
     * 补全或修改地址确认
     * @return
     */
    public MutableLiveData<Status<OrderPay>> udpateAdress(String token, String mid, String orderNo, String adressId) {

        final MutableLiveData<Status<OrderPay>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("orderNo",orderNo);
        params.put("address_id",adressId);

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .udpateAdress(token,mid,orderNo,adressId,sign)
                .enqueue(new Callback<OrderPay>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderPay> call,
                                           @NonNull Response<OrderPay> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderPay> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }
}

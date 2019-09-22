package com.cuci.enticement.plate.mine.vm;


import android.text.TextUtils;

import com.cuci.enticement.bean.ExpressInfo;
import com.cuci.enticement.bean.OrderList;
import com.cuci.enticement.bean.OrderPay;
import com.cuci.enticement.bean.OrderResult;
import com.cuci.enticement.bean.OrderStatistics;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.CartApi;
import com.cuci.enticement.network.api.OrderApi;
import com.cuci.enticement.utils.SignUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public OrderViewModel() {
        mCreator = ServiceCreator.getInstance();
    }



    public MutableLiveData<Status<ResponseBody>> getOrderList(String token, String mid, String page,String status,String orderNum, int loadType) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("page",page);
        if(!TextUtils.isEmpty(status)){
            params.put("status",status);
        }

        if(!TextUtils.isEmpty(orderNum)){
            params.put("order_no",orderNum);
        }
        params.put("from_type","2");

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getOrderList("2",token,mid,page,status,orderNum,sign)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            data.setValue(Status.moreSuccess(response.body()));
                        }


                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
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
    public MutableLiveData<Status<ResponseBody>> orderCancel(String token,String mid,String orderNum) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("order_no",orderNum);
        params.put("from_type","2");
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .cancelOrder("2",token,mid,orderNum,sign)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
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
    public MutableLiveData<Status<ResponseBody>> commitOrder(String token,String mid,String rule,String fromMid) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
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
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
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
        params.put("from_type","2");

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getTotalOrder("2",token,mid,sign)
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
    public MutableLiveData<Status<ResponseBody>> getExpressCost(String token,String mid,String orderNo,String adressId) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("order_no",orderNo);
        params.put("address_id",adressId);
        params.put("from_type","2");

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getExpressCost("2",token,mid,orderNo,adressId,sign)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
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
    public MutableLiveData<Status<ResponseBody>> getOrderPay(String token, String mid, String orderNo, String payType) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("order_no",orderNo);
        params.put("pay_type",payType);
        params.put("from_type","2");

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getOrderPay("2",token,mid,orderNo,payType,sign)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
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
    public MutableLiveData<Status<ResponseBody>> udpateAdress(String token, String mid, String orderNo, String adressId) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("order_no",orderNo);
        params.put("address_id",adressId);
        params.put("from_type","2");

        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .udpateAdress("2",token,mid,orderNo,adressId,sign)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }
}

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

    /**
     * 退款详情
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> getJiFenDuiHuanTiJiao(String token,String mid,String rule,String address_id,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("from_type","2");
        params.put("rule",rule);
        params.put("address_id",address_id);
        params.put("new_version",new_version);
        String sign = SignUtils.signParamRemoveNull(params);
        mCreator.create(OrderApi.class)
                .getJiFenDuiHuanTiJiao("2",token,mid,rule,address_id,new_version,sign)
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
     * 退款详情
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> getTuiKuanXiangQing(String token,String mid,String refund_id,String item_id,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("from_type","2");
        params.put("refund_id",refund_id);
        params.put("item_id",item_id);
        params.put("new_version",new_version);
        String sign = SignUtils.signParamRemoveNull(params);
        mCreator.create(OrderApi.class)
                .getTuiKuanXiangQing("2",token,mid,refund_id,item_id,new_version,sign)
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
     * 撤销退款
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> getTuiKuanCancel(String token,String mid,String refund_id,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("from_type","2");
        params.put("refund_id",refund_id);
        params.put("new_version",new_version);
        String sign = SignUtils.signParamRemoveNull(params);
        mCreator.create(OrderApi.class)
                .getTuiKuanCancel("2",token,mid,refund_id,new_version,sign)
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
     * 退款订单
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> getTuiKuanList(String token, String mid, String from_type,String page,String page_size,String new_version, int loadType) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("page",page);
        params.put("page_size",page_size);
        params.put("from_type",from_type);
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getTuiKuanList("2",token,mid,page,page_size,new_version,sign)
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
     * 全部订单
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> getOrderList(String token, String mid, String page,String status,String orderNum,String new_version, int loadType) {

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
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getOrderList("2",token,mid,page,status,orderNum,new_version,sign)
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
    public MutableLiveData<Status<ResponseBody>> orderConfirm(String token,String mid,String orderNum,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);

        params.put("order_no",orderNum);
        params.put("from_type","2");
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .confirmOrder("2",token,mid,orderNum,new_version,sign)
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
     * 取消订单
     * @param token
     * @param mid
     * @param orderNum
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> orderCancel(String token,String mid,String orderNum,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("order_no",orderNum);
        params.put("from_type","2");
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .cancelOrder("2",token,mid,orderNum,new_version,sign)
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
    public MutableLiveData<Status<OrderStatistics>> getStatisticsOrder(String token, String mid,String new_version) {

        final MutableLiveData<Status<OrderStatistics>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("from_type","2");
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getTotalOrder("2",token,mid,new_version,sign)
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
    public MutableLiveData<Status<ResponseBody>> getExpressInfo(String expressNo, String expressCode,String new_version,int loadType) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("express_no",expressNo);
        params.put("express_code",expressCode);
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getExpressInfo(expressNo,expressCode,new_version,sign)
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
     * 快递费用
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> getExpressCost(String token,String mid,String number,String priceGoods,String adressId,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("number",number);
        params.put("priceGoods",priceGoods);
        params.put("address_id",adressId);
        params.put("from_type","2");
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getExpressCost("2",token,mid,number,priceGoods,adressId,new_version,sign)
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
    public MutableLiveData<Status<ResponseBody>> getOrderPay(String token, String mid, String orderNo, String payType,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("order_no",orderNo);
        params.put("pay_type",payType);
        params.put("from_type","2");
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(OrderApi.class)
                .getOrderPay("2",token,mid,orderNo,payType,new_version,sign)
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
    public MutableLiveData<Status<ResponseBody>> udpateAdress(String token, String mid,String m_coupon_id, String rule, String adressId,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("m_coupon_id",m_coupon_id);
        params.put("rule",rule);
        params.put("address_id",adressId);
        params.put("from_type","2");
        params.put("new_version",new_version);
        String sign = SignUtils.signParamRemoveNull(params);
        mCreator.create(OrderApi.class)
                .udpateAdress("2",token,mid,m_coupon_id,rule,adressId,new_version,sign)
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

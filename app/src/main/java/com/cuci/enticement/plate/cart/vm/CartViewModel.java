package com.cuci.enticement.plate.cart.vm;


import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.CartDataBean;
import com.cuci.enticement.bean.CartListBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.CartApi;
import com.cuci.enticement.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public CartViewModel() {
        mCreator = ServiceCreator.getInstance();
    }



    public MutableLiveData<Status<Base<CartDataBean>>> getCartList(String token, String mid, String page, int loadType) {

        final MutableLiveData<Status<Base<CartDataBean>>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("page",page);
        params.put("from_type","2");

        String signs = SignUtils.signParam(params);

        mCreator.create(CartApi.class)
                .getCartList("2",token,mid,page,signs)
                .enqueue(new Callback<Base<CartDataBean>>() {
                    @Override
                    public void onResponse(@NonNull Call<Base<CartDataBean>> call,
                                           @NonNull Response<Base<CartDataBean>> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            data.setValue(Status.moreSuccess(response.body()));
                        }


                    }

                    @Override
                    public void onFailure(@NonNull Call<Base<CartDataBean>> call,
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



    public MutableLiveData<Status<Base>> cartChange(String token,String mid,String goodsId,String goodsSpec,String goodsNum) {

        final MutableLiveData<Status<Base>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("goods_id",goodsId);
        params.put("goods_spec",goodsSpec);
        params.put("goods_num",goodsNum);
        params.put("from_type","2");
        String signs = SignUtils.signParam(params);
        mCreator.create(CartApi.class)
                .cartChange("2",token,mid,goodsId,goodsSpec,goodsNum,signs)
                .enqueue(new Callback<Base>() {
                    @Override
                    public void onResponse(@NonNull Call<Base> call,
                                           @NonNull Response<Base> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<Base> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }


    public MutableLiveData<Status<ResponseBody>> cartDelete(String token,String mid,String cartId) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("cart_id",cartId);
        params.put("from_type","2");
        String signs = SignUtils.signParam(params);
        mCreator.create(CartApi.class)
                .cartDelete("2",token,mid,cartId,signs)
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



    public MutableLiveData<Status<ResponseBody>> commitOrder(String token, String mid, String rule, String fromMid) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("rule",rule);

        params.put("from_type","2");
        if(!TextUtils.isEmpty(fromMid)){
            params.put("from_mid",fromMid);
        }
        String signs = SignUtils.signParam(params);

        mCreator.create(CartApi.class)
                .commitOrder("2",token,mid,rule,fromMid,signs)
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





    public MutableLiveData<Status<ResponseBody>> cartNum(String token,String mid) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("from_type","2");
        String signs = SignUtils.signParam(params);
        mCreator.create(CartApi.class)
                .cartNum("2",token,mid,signs)
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
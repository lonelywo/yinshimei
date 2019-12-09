package com.cuci.enticement.plate.home.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cuci.enticement.bean.BannerDataBean;
import com.cuci.enticement.bean.BaseList;


import com.cuci.enticement.bean.GeneralGoods;
import com.cuci.enticement.bean.HomeDetailsBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.HomeApi;
import com.cuci.enticement.network.api.MineApi;
import com.cuci.enticement.utils.EncryptUtils;
import com.cuci.enticement.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeViewModel extends ViewModel {

    private ServiceCreator mCreator;
    private String sign;

    public HomeViewModel() {
        mCreator = ServiceCreator.getInstance();
    }
    public MutableLiveData<Status<BaseList<BannerDataBean>>> getBanner() {

        final MutableLiveData<Status<BaseList<BannerDataBean>>> data = new MutableLiveData<>();
        if(ServiceCreator.ConstantA==0){
            sign = EncryptUtils.md5Encrypt("&key=O65dGdgf5Hf5GK6aF9JDFl5I9skPkd");
        }else {
            sign = EncryptUtils.md5Encrypt("&key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        }
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
    public MutableLiveData<Status<GeneralGoods>> getGeneralGoods(String from_type, String mid, String token, int loadType) {

        final MutableLiveData<Status<GeneralGoods>> liveData = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParamRemoveNull(params);
        mCreator.create(HomeApi.class)
                .getGeneralGoods(from_type,  mid,  token,signs)
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
    public MutableLiveData<Status<HomeDetailsBean>> getHomeDetails(String from_type, String mid, String token,String goods_id) {

        final MutableLiveData<Status<HomeDetailsBean>> data = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("goods_id",goods_id);
        String signs = SignUtils.signParamRemoveNull(params);
        mCreator.create(HomeApi.class)
                .getHomeDetails(from_type, mid,token,goods_id,signs)
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
    /**
     * 分享海报
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> shareimg(String from_type, String mid, String token,String goods_id) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("goods_id",goods_id);
        String signs = SignUtils.signParam(params);
        mCreator.create(HomeApi.class)
                .shareimg(from_type,mid,token,goods_id,signs)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        liveData.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
                                          @NonNull Throwable t) {
                        liveData.setValue(Status.error(null, t.getMessage() == null ? "网络错误" : t.getMessage()));
                    }
                });
        return liveData;

    }








}

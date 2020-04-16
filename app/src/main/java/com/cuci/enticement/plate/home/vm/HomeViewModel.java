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

    /**
     * 首页分享
     * @param from_type
     * @param new_version
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> sharehaobao(String from_type,String token,String mid,String new_version) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("new_version",new_version);
        params.put("mid",mid);
        params.put("token",token);
        String signs = SignUtils.signParam(params);
        mCreator.create(HomeApi.class)
                .sharehaobao(from_type,token,mid,new_version,signs)
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



    public MutableLiveData<Status<BaseList<BannerDataBean>>> getBanner(String from_type,String new_version) {

        final MutableLiveData<Status<BaseList<BannerDataBean>>> data = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(HomeApi.class)
                .getBanner(from_type,new_version,signs)
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
    public MutableLiveData<Status<GeneralGoods>> getGeneralGoods(String from_type, String mid, String token,String new_version, int loadType) {

        final MutableLiveData<Status<GeneralGoods>> liveData = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("new_version",new_version);
        String signs = SignUtils.signParamRemoveNull(params);
        mCreator.create(HomeApi.class)
                .getGeneralGoods(from_type,  mid,  token,new_version,signs)
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
    public MutableLiveData<Status<ResponseBody>> getyhjandqiye(String from_type, String mid, String token,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("new_version",new_version);
        String signs = SignUtils.signParamRemoveNull(params);
        mCreator.create(HomeApi.class)
                .getyhjandqiye(from_type, mid,token,new_version,signs)
                .enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() ==
                                null ? "加载失败" : t.getMessage()));
                    }
                });
        return data;
    }
    public MutableLiveData<Status<ResponseBody>> getproyhq(String from_type, String mid, String token,String goods_id,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("goods_id",goods_id);
        params.put("new_version",new_version);
        String signs = SignUtils.signParamRemoveNull(params);
        mCreator.create(HomeApi.class)
                .getproyhq(from_type, mid,token,goods_id,new_version,signs)
                .enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() ==
                                null ? "加载失败" : t.getMessage()));
                    }
                });
        return data;
    }
    public MutableLiveData<Status<ResponseBody>> getprolingyhq(String from_type, String mid, String token,String coupon_id,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("coupon_id",coupon_id);
        params.put("new_version",new_version);
        String signs = SignUtils.signParamRemoveNull(params);
        mCreator.create(HomeApi.class)
                .getprolingyhq(from_type, mid,token,coupon_id,new_version,signs)
                .enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() ==
                                null ? "加载失败" : t.getMessage()));
                    }
                });
        return data;
    }

    public MutableLiveData<Status<HomeDetailsBean>> getHomeDetails(String from_type, String mid, String token,String goods_id,String new_version) {

        final MutableLiveData<Status<HomeDetailsBean>> data = new MutableLiveData<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("goods_id",goods_id);
        params.put("new_version",new_version);
        String signs = SignUtils.signParamRemoveNull(params);
        mCreator.create(HomeApi.class)
                .getHomeDetails(from_type, mid,token,goods_id,new_version,signs)
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
    public MutableLiveData<Status<ResponseBody>> shareimg(String from_type, String mid, String token,String goods_id,String new_version) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("goods_id",goods_id);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(HomeApi.class)
                .shareimg(from_type,mid,token,goods_id,new_version,signs)
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

    /**
     * 获取当前用户信息
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> dataUserinfo(String from_type, String mid, String token,String new_version) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(HomeApi.class)
                .dataUserinfo(from_type,mid,token,new_version,signs)
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

    /**
     * 文章内容
     * @param from_type
     * @param nid
     * @param new_version
     * @return
     */
    public MutableLiveData<Status<ResponseBody>>  essay(String nid,String from_type, String new_version) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("nid",nid);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(HomeApi.class)
                .essay(nid,from_type,new_version,signs)
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

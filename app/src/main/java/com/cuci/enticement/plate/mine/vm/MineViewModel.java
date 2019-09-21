package com.cuci.enticement.plate.mine.vm;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.MineApi;
import com.cuci.enticement.network.api.UserApi;
import com.cuci.enticement.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MineViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public MineViewModel() {
        mCreator = ServiceCreator.getInstance();
    }






    /**
     * 开关
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<Base>> loginOut(String from_type, String token, String mid) {

        final MutableLiveData<Status<Base>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParam(params);
        mCreator.create(UserApi.class)
                .loginOut(from_type,token,mid,signs)
                .enqueue(new Callback<Base>() {
                    @Override
                    public void onResponse(@NonNull Call<Base> call,
                                           @NonNull Response<Base> response) {
                        liveData.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<Base> call,
                                          @NonNull Throwable t) {
                        liveData.setValue(Status.error(null, t.getMessage() == null ? "网络错误" : t.getMessage()));
                    }
                });
        return liveData;

    }


    /**
     * 获取会员佣金统计
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> hqcommissiontj(String token, String mid, String from_type) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .hqcommissiontj(token,mid,from_type,signs)
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
     * 获取会员佣金记录
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> hqcommissiontj(String token, String mid, String from_type,String date,int loadType) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("date",date);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .hqcommissionjl(token,mid,from_type,date,signs)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            liveData.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            liveData.setValue(Status.moreSuccess(response.body()));
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
                                          @NonNull Throwable t) {
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

    /**
     * 提交佣金提现申请
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> txcommissionsq(String token, String mid, String from_type,String price) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("price",price);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .txcommissionsq(token,mid,from_type,price,signs)
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
     * 获取佣金提现记录
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> txcommissionjl(String token, String mid, String from_type,int loadType) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .txcommissionjl(token,mid,from_type,signs)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            liveData.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            liveData.setValue(Status.moreSuccess(response.body()));
                        }


                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
                                          @NonNull Throwable t) {
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
    /**
     * 获取团队统计列表
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> hqteamtj(String token, String mid, String from_type,String nickname,String page,int loadType) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("nickname",nickname);
        params.put("page",page);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .hqteamtj(token,mid,from_type,nickname,page,signs)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            liveData.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            liveData.setValue(Status.moreSuccess(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
                                          @NonNull Throwable t) {
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
    /**
     * 获取团队统计列表
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> hqteamtj2(String token, String mid, String from_type,String pid,String nickname,String page,int loadType) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("pid",pid);
        params.put("nickname",nickname);
        params.put("page",page);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .hqteamtj2(token,mid,from_type,pid,nickname,page,signs)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            liveData.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            liveData.setValue(Status.moreSuccess(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
                                          @NonNull Throwable t) {
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
    /**
     * 获取团队数量统计
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> hqteamsl(String token, String mid, String from_type) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .hqteamsl(token,mid,from_type,signs)
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
     * 业绩月返
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> achievement(String token, String mid, String from_type) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .achievement(token,mid,from_type,signs)
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

package com.cuci.enticement.plate.mine.vm;


import android.text.TextUtils;

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
        if(!TextUtils.isEmpty(nickname)){
            params.put("nickname",nickname);
        }
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
        if(!TextUtils.isEmpty(nickname)){
            params.put("nickname",nickname);
        }
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

    /**
     * 环信注册
     * @param phone
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> hxreg(String phone, String from_type, String token,String mid) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
       // liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone",phone);
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .hxreg(phone,from_type,token,mid,signs)
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
     * 获取PK排行榜1
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> pk1(String token, String mid, String from_type,String page,int loadType) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("page",page);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .pk1(token,from_type,mid,page,signs)
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
     * 获取PK排行榜2
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> pk2(String token, String mid, String from_type,String page,int loadType) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("page",page);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .pk2(token,from_type,mid,page,signs)
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
     * 获取PK排行榜3
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> pk3(String token, String mid, String from_type,String page,int loadType) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("page",page);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .pk3(token,from_type,mid,page,signs)
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
     * 绑定微信
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> bindwx(String from_type, String mid, String token, String unionId, String openId, String avatarUrl, String nickname) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("unionId",unionId);
        params.put("openId",openId);
        params.put("avatarUrl",avatarUrl);
        params.put("nickname",nickname);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .bindwx(from_type,mid,token,unionId,openId,avatarUrl,nickname,signs)
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
     * 解绑微信
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> jiebindwx(String from_type, String mid, String token) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .jiebindwx(from_type,mid,token,signs)
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
    public MutableLiveData<Status<ResponseBody>> getWxToken(String appId, String secret, String code, String grantType) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        mCreator.create(UserApi.class)
                .getWxToken(appId, secret, code, grantType)
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
    public MutableLiveData<Status<ResponseBody>> getWxInfo(String accessToken, String openId) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));

        mCreator.create(UserApi.class)
                .getWxInfo(accessToken, openId)
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
     * 关于因诗美
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> ysm(String from_type, String mid, String token) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .ysm(from_type,mid,token,signs)
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
    public MutableLiveData<Status<ResponseBody>> dataUserinfo(String from_type, String mid, String token) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .dataUserinfo(from_type,mid,token,signs)
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
     * 消费月返提交收货地址
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> monbackdizi(String from_type, String mid, String token, String address_id) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("address_id",address_id);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .monbackdizi(from_type,mid,token,address_id,signs)
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
     * 绑定推荐人
     * @param from_type
     * @param token
     * @param mid
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> bindTuijian(String token, String mid, String from_type,String phone) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("token",token);
        params.put("mid",mid);
        params.put("phone",phone);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .bindTuijian(token,mid,from_type,phone,signs)
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

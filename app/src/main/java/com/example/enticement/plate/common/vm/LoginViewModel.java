package com.example.enticement.plate.common.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.enticement.bean.Base;
import com.example.enticement.bean.LoginBean;
import com.example.enticement.bean.Status;
import com.example.enticement.bean.UserInfo;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.UserApi;
import com.example.enticement.utils.EncryptUtils;
import com.example.enticement.utils.SignUtils;
import com.google.gson.Gson;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public LoginViewModel() {
        mCreator = ServiceCreator.getInstance();
    }



  /*  public MutableLiveData<Status<Base<UserInfo>>> checkUserInfo(String unionId, String sex,
                                                                 String headImgUrl, String nickName) {

        final MutableLiveData<Status<Base<UserInfo>>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));

        mCreator.create(UserApi.class)
                .checkUserInfo(unionId, sex, headImgUrl, nickName)
                .enqueue(new Callback<Base<UserInfo>>() {
                    @Override
                    public void onResponse(@NonNull Call<Base<UserInfo>> call,
                                           @NonNull Response<Base<UserInfo>> response) {
                        liveData.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<Base<UserInfo>> call,
                                          @NonNull Throwable t) {
                        liveData.setValue(Status.error(null, t.getMessage() == null ? "网络错误" : t.getMessage()));
                    }
                });

        return liveData;
    }*/

    /*public MutableLiveData<Status<ResponseBody>> getWxToken(String appId, String secret, String code, String grantType) {

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
    }*/

   /* public MutableLiveData<Status<ResponseBody>> getWxInfo(String accessToken, String openId) {

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
    }*/

    public MutableLiveData<Status<Base<UserInfo>>> login(String data) {

        final MutableLiveData<Status<Base<UserInfo>>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        String stringA ="data="+data;
        String sign = EncryptUtils.md5Encrypt(stringA+"&key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        String signs = sign.toUpperCase();
        mCreator.create(UserApi.class)
                .login(data,signs)
                .enqueue(new Callback<Base<UserInfo>>() {
                    @Override
                    public void onResponse(@NonNull Call<Base<UserInfo>> call,
                                           @NonNull Response<Base<UserInfo>> response) {
                        liveData.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<Base<UserInfo>> call,
                                          @NonNull Throwable t) {
                        liveData.setValue(Status.error(null, t.getMessage() == null ? "网络错误" : t.getMessage()));
                    }
                });
        return liveData;

    }





    public MutableLiveData<Status<Base>> getSmsCodelogin(String phone, String secure, String region) {

        final MutableLiveData<Status<Base>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        String  stringA = "phone="+phone+"&region="+region+"&secure="+secure;
        String sign = EncryptUtils.md5Encrypt(stringA+"&key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        String signs = sign.toUpperCase();

        mCreator.create(UserApi.class)
                .getSmsCodelogin(phone, secure, region,signs)
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

}

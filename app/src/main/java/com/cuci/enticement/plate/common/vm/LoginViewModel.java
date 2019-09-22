package com.cuci.enticement.plate.common.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.UserApi;
import com.cuci.enticement.utils.EncryptUtils;
import com.cuci.enticement.utils.SignUtils;


import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public LoginViewModel() {
        mCreator = ServiceCreator.getInstance();
    }



    public MutableLiveData<Status<Base<UserInfo>>> checkUserInfo(String unionId, String openId,
                                                                   String avatarUrl, String nickname, String from_type, String gender) {

        final MutableLiveData<Status<Base<UserInfo>>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("unionId",unionId);
        params.put("openId",openId);
        params.put("avatarUrl",avatarUrl);
        params.put("nickname",nickname);
        params.put("from_type",from_type);
        params.put("gender",gender);
        String signs = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .checkUserInfo(unionId, openId, avatarUrl, nickname,from_type,gender,signs)
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
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone",phone);
        params.put("secure",secure);
        params.put("region",region);
        params.put("type","3");
        String signs = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .getSmsCodelogin(phone, secure, region,"3",signs)
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

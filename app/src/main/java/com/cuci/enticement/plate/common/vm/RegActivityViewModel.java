package com.cuci.enticement.plate.common.vm;

import android.text.TextUtils;

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


public class RegActivityViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public RegActivityViewModel() {
        mCreator = ServiceCreator.getInstance();
    }

    public MutableLiveData<Status<Base<UserInfo>>> register(String code, String phone, String agent_phone, String unionId, String openId, String avatarUrl, String nickname, String gender) {

        final MutableLiveData<Status<Base<UserInfo>>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("code",code);
        params.put("phone",phone);
        params.put("agent_phone",agent_phone);
        if(!TextUtils.isEmpty(unionId)){
            params.put("unionId",unionId);
        }
        if(!TextUtils.isEmpty(openId)){
            params.put("openId",openId);
        }
        if(!TextUtils.isEmpty(avatarUrl)){
            params.put("avatarUrl",avatarUrl);
        }
        if(!TextUtils.isEmpty(nickname)){
            params.put("nickname",nickname);
        }
        if(!TextUtils.isEmpty(gender)){
            params.put("gender",gender);
        }

        String signs = SignUtils.signParam(params);
        mCreator.create(UserApi.class)
                .register(code, phone, agent_phone,unionId,openId,avatarUrl,nickname,gender,signs)
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

   public MutableLiveData<Status<Base>> getSmsCode(String phone, String secure, String region,String type) {

        final MutableLiveData<Status<Base>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
       Map<String, String> params = new HashMap<String, String>();
       params.put("phone",phone);
       params.put("secure",secure);
       params.put("region",region);
       params.put("type",type);
       String signs = SignUtils.signParam(params);

       mCreator.create(UserApi.class)
                .getSmsCode(phone, secure, region,type,signs)
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
    public MutableLiveData<Status<Base<UserInfo>>> wxBindPhone(String phone, String unionId, String openId, String avatarUrl, String nickname, String from_type, String gender) {

        final MutableLiveData<Status<Base<UserInfo>>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone",phone);
        params.put("unionId",unionId);
        params.put("openId",openId);
        params.put("avatarUrl",avatarUrl);
        params.put("nickname",nickname);
        params.put("from_type",from_type);
        params.put("gender",gender);
        String signs = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .wxBindPhone(phone,unionId,openId,avatarUrl,nickname,from_type,gender,signs)
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
    public MutableLiveData<Status<ResponseBody>> wxCheckBindPhone(String phone) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone",phone);
        String signs = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .wxCheckBindPhone(phone,signs)
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

    public MutableLiveData<Status<ResponseBody>> huanBindPhone(String from_type, String mid, String token,String phone,String code) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("mid",mid);
        params.put("token",token);
        params.put("phone",phone);
        params.put("code",code);
        String signs = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .huanBindPhone(from_type, mid, token,phone,code,signs)
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

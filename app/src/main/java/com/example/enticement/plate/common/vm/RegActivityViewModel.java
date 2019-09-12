package com.example.enticement.plate.common.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.enticement.bean.Base;
import com.example.enticement.bean.Status;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.UserApi;
import com.example.enticement.utils.EncryptUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegActivityViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public RegActivityViewModel() {
        mCreator = ServiceCreator.getInstance();
    }

    public MutableLiveData<Status<Base>> register(String code, String phone, String agent_phone) {

        final MutableLiveData<Status<Base>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));

        mCreator.create(UserApi.class)
                .register(code, phone, agent_phone)
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

   public MutableLiveData<Status<Base>> getSmsCode(String phone, String secure, String required) {

        final MutableLiveData<Status<Base>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        String sign = EncryptUtils.md5Encrypt("fdsh666" + phone + "fdsh168");

        mCreator.create(UserApi.class)
                .getSmsCode(phone, secure, required)
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

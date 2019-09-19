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

public class BindPhoneViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public BindPhoneViewModel() {
        mCreator = ServiceCreator.getInstance();
    }

    public MutableLiveData<Status<Base>> wxBindPhone(String phone) {

        final MutableLiveData<Status<Base>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        String  stringA = "phone="+phone;
        String sign = EncryptUtils.md5Encrypt(stringA+"&key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        String signs = sign.toUpperCase();

        mCreator.create(UserApi.class)
                .wxBindPhone(phone,signs)
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

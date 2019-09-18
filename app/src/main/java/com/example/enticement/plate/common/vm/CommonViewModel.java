package com.example.enticement.plate.common.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.enticement.bean.AdressBean;
import com.example.enticement.bean.Base;
import com.example.enticement.bean.Status;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.CommonApi;
import com.example.enticement.network.api.UserApi;
import com.example.enticement.utils.EncryptUtils;
import com.example.enticement.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonViewModel extends ViewModel {


    private ServiceCreator mCreator;

    public CommonViewModel() {
        mCreator = ServiceCreator.getInstance();
    }


    public MutableLiveData<Status<AdressBean>> getAdressList(String token, String mid,int loadType) {

        final MutableLiveData<Status<AdressBean>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        String sign = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .getAdressList(token,mid,sign)
                .enqueue(new Callback<AdressBean>() {
                    @Override
                    public void onResponse(@NonNull Call<AdressBean> call,
                                           @NonNull Response<AdressBean> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            data.setValue(Status.moreSuccess(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdressBean> call,
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


}

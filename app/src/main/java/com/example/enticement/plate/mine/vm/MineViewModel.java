package com.example.enticement.plate.mine.vm;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.enticement.bean.Base;
import com.example.enticement.bean.OrderList;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.Status;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.CartApi;
import com.example.enticement.network.api.OrderApi;
import com.example.enticement.network.api.UserApi;
import com.example.enticement.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

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









}

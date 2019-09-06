package com.example.enticement.plate.common.vm;



import com.example.enticement.bean.Base;
import com.example.enticement.bean.Status;
import com.example.enticement.bean.Version;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.CommonApi;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public MainViewModel() {
        mCreator = ServiceCreator.getInstance();
    }



    public MutableLiveData<Status<Base<Version>>> getVersion() {

        final MutableLiveData<Status<Base<Version>>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        mCreator.create(CommonApi.class)
                .getVersion()
                .enqueue(new Callback<Base<Version>>() {
                    @Override
                    public void onResponse(@NonNull Call<Base<Version>> call,
                                           @NonNull Response<Base<Version>> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<Base<Version>> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }




}

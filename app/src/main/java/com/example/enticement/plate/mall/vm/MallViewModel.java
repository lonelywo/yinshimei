package com.example.enticement.plate.mall.vm;



import com.example.enticement.bean.MallSourceBean;
import com.example.enticement.bean.Status;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.MallApi;
import com.example.enticement.utils.SignUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MallViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public MallViewModel() {
        mCreator = ServiceCreator.getInstance();
    }

    public MutableLiveData<Status<MallSourceBean>> getSource(String type,String page,String pagesize) {

        final MutableLiveData<Status<MallSourceBean>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("type",type);
        params.put("page",page);
        params.put("pagesize",pagesize);
        String signs = SignUtils.signParam(params);
        mCreator.create(MallApi.class)
                .getSource(type,page,pagesize,signs)
                .enqueue(new Callback<MallSourceBean>() {
                    @Override
                    public void onResponse(@NonNull Call<MallSourceBean> call,
                                           @NonNull Response<MallSourceBean> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<MallSourceBean> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }
    public MutableLiveData<Status<MallSourceBean>> getSource01(String type,String page,String pagesize, int loadType) {

        final MutableLiveData<Status<MallSourceBean>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("type",type);
        params.put("page",page);
        params.put("pagesize",pagesize);
        String signs = SignUtils.signParam(params);
        mCreator.create(MallApi.class)
                .getSource01(type,page,pagesize,signs)
                .enqueue(new Callback<MallSourceBean>() {
                    @Override
                    public void onResponse(@NonNull Call<MallSourceBean> call,
                                           @NonNull Response<MallSourceBean> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            data.setValue(Status.moreSuccess(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MallSourceBean> call,
                                          @NonNull Throwable t) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshError(null, t.getMessage() == null ? "加载失败" : t.getMessage()));
                        } else {
                            data.setValue(Status.moreError(null, t.getMessage() == null ? "加载失败" : t.getMessage()));
                        }
                    }
                });
        return data;
    }

}

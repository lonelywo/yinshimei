package com.cuci.enticement.plate.mall.vm;



import android.text.TextUtils;

import com.cuci.enticement.bean.MallSourceBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.MallApi;
import com.cuci.enticement.utils.EncryptUtils;
import com.cuci.enticement.utils.SignUtils;

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

    public MutableLiveData<Status<MallSourceBean>> getSource(String type,String page,String pagesize,String new_version) {

        final MutableLiveData<Status<MallSourceBean>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("type",type);
        params.put("page",page);
        params.put("pagesize",pagesize);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(MallApi.class)
                .getSource(type,page,pagesize,new_version,signs)
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
    public MutableLiveData<Status<MallSourceBean>> getSource01(String type,String page,String pagesize,String new_version, int loadType) {

        final MutableLiveData<Status<MallSourceBean>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        if(!TextUtils.isEmpty(type)){
            params.put("type",type);
        }
        params.put("page",page);
        params.put("pagesize",pagesize);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(MallApi.class)
                .getSource01(type,page,pagesize,new_version,signs)
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
    public MutableLiveData<Status<MallSourceBean>> getSource02(String type,String page,String pagesize,String new_version, int loadType) {

        final MutableLiveData<Status<MallSourceBean>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        if(!TextUtils.isEmpty(type)){
            params.put("type",type);
        }
        params.put("page",page);
        params.put("pagesize",pagesize);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(MallApi.class)
                .getSource02(type,page,pagesize,new_version,signs)
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

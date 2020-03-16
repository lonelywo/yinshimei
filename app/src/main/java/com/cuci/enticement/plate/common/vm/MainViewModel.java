package com.cuci.enticement.plate.common.vm;



import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.Version;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.CommonApi;
import com.cuci.enticement.network.api.MineApi;
import com.cuci.enticement.network.api.UserApi;
import com.cuci.enticement.utils.EncryptUtils;
import com.cuci.enticement.utils.SignUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public MainViewModel() {
        mCreator = ServiceCreator.getInstance();
    }



    public MutableLiveData<Status<ResponseBody>> getVersion(String from_type ,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(CommonApi.class)
                .getVersion(from_type,new_version,signs)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        data.setValue(Status.success(response.body()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
                                          @NonNull Throwable t) {
                        data.setValue(Status.error(null, t.getMessage() == null ? "获取失败" : t.getMessage()));
                    }
                });
        return data;
    }

    public MutableLiveData<Status<ResponseBody>> getGuoJiaCode(String from_type,String new_version) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .getGuoJiaCode(from_type,new_version, signs)
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

    /**
     * 用户条款
     * @param from_type
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> clause(String from_type,String new_version ) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .clause(from_type,new_version,signs)
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
    /**
     * 开屏广告
     * @param from_type
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> openScreen(String from_type,String new_version) {

        final MutableLiveData<Status<ResponseBody>> liveData = new MutableLiveData<>();
        liveData.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_type",from_type);
        params.put("new_version",new_version);
        String signs = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .openScreen(from_type,new_version,signs)
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

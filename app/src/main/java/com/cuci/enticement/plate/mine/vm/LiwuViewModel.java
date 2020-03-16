package com.cuci.enticement.plate.mine.vm;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.cuci.enticement.bean.OrderStatistics;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.CartApi;
import com.cuci.enticement.network.api.MineApi;
import com.cuci.enticement.network.api.OrderApi;
import com.cuci.enticement.utils.SignUtils;
import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LiwuViewModel extends ViewModel {

    private ServiceCreator mCreator;

    public LiwuViewModel() {
        mCreator = ServiceCreator.getInstance();
    }

    /**
     * 领取列表
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> ReceiveQueue(String token, String mid, String page,String get_state,String new_version, int loadType) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("page",page);
        params.put("get_state",get_state);
        params.put("from_type","2");
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .ReceiveQueue(token,mid,"2",get_state,page,new_version,sign)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            data.setValue(Status.moreSuccess(response.body()));
                        }


                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
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
    /**
     * 领取提交
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> ReceiveCommit(String token, String mid, String address_id,String new_version) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("from_type","2");
        params.put("address_id",address_id);
        params.put("new_version",new_version);
        String sign = SignUtils.signParam(params);
        mCreator.create(MineApi.class)
                .ReceiveCommit(token,mid,"2",address_id,new_version,sign)
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
}

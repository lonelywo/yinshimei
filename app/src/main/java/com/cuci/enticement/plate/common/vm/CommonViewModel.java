package com.cuci.enticement.plate.common.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cuci.enticement.bean.AdressBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.UserApi;
import com.cuci.enticement.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
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

    public MutableLiveData<Status<ResponseBody>> addAdress(String token, String mid,String name,String phone,String province,String city,
                                                           String area,String adress,String isDeafult) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("name",name);
        params.put("phone",phone);
        params.put("province",province);
        params.put("city",city);
        params.put("area",area);
        params.put("adress",adress);
        params.put("is_default",isDeafult);
        params.put("from_type","2");
        String sign = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .addAdress("2",token,mid,sign,phone,province,city,area,adress,isDeafult,sign)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call,
                                           @NonNull Response<ResponseBody> response) {

                            data.setValue(Status.success(response.body()));

                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,
                                          @NonNull Throwable t) {

                            data.setValue(Status.error(null, t.getMessage() ==
                                    null ? "加载失败" : t.getMessage()));

                    }
                });
        return data;
    }
}

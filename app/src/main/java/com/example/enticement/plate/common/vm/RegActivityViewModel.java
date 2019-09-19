package com.example.enticement.plate.common.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.enticement.bean.Base;
import com.example.enticement.bean.Status;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.network.api.UserApi;
import com.example.enticement.utils.EncryptUtils;
import com.example.enticement.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

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
        String  stringA = "agent_phone="+agent_phone+"&code="+code+"&phone="+phone;
        String sign = EncryptUtils.md5Encrypt(stringA+"&key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        String signs = sign.toUpperCase();
        mCreator.create(UserApi.class)
                .register(code, phone, agent_phone,signs)
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

   public MutableLiveData<Status<Base>> getSmsCode(String phone, String secure, String region) {

        final MutableLiveData<Status<Base>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));
       Map<String, String> params = new HashMap<String, String>();
       params.put("phone",phone);
       params.put("secure",secure);
       params.put("region",region);
       params.put("type","1");
       String signs = SignUtils.signParam(params);

       /* String  stringA = "phone="+phone+"&region="+region+"&secure="+secure;
        String sign = EncryptUtils.md5Encrypt(stringA+"&key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        String signs = sign.toUpperCase();*/
       mCreator.create(UserApi.class)
                .getSmsCode(phone, secure, region,"1",signs)
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
   /* public MutableLiveData<Status<Base>> bindPhone(String code, String phone) {

        final MutableLiveData<Status<Base>> liveData = new MutableLiveData<>();

        liveData.setValue(Status.loading(null));

        mCreator.create(UserApi.class)
                .bindPhone(code, phone)
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
    }*/
}

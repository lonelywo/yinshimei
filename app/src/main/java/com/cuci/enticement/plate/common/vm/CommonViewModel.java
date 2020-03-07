package com.cuci.enticement.plate.common.vm;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cuci.enticement.bean.AddressBean;
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


    public MutableLiveData<Status<AddressBean>> getAdressList(String token, String mid, int loadType) {

        final MutableLiveData<Status<AddressBean>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("from_type","2");
        String sign = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .getAdressList("2",token,mid,sign)
                .enqueue(new Callback<AddressBean>() {
                    @Override
                    public void onResponse(@NonNull Call<AddressBean> call,
                                           @NonNull Response<AddressBean> response) {
                        if (loadType == Status.LOAD_REFRESH) {
                            data.setValue(Status.refreshSuccess(response.body()));
                        } else {
                            data.setValue(Status.moreSuccess(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AddressBean> call,
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
                                                           String area,String address,String isDeafult,String addressId) {

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
        params.put("address",address);
        params.put("is_default",isDeafult);
        params.put("from_type","2");
        if(!TextUtils.isEmpty(addressId)){
            params.put("id",addressId);
        }
        String sign = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .address("2",token,mid,name,phone,province,city,area,address,isDeafult,addressId,sign)
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


    public MutableLiveData<Status<ResponseBody>> deleteAddress(String token, String mid,String addressId) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();
        params.put("token",token);
        params.put("mid",mid);
        params.put("address_id",addressId);
        params.put("from_type","2");



        String sign = SignUtils.signParam(params);

        mCreator.create(UserApi.class)
                .deleteAddress("2",token,mid,addressId,sign)
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

    public MutableLiveData<Status<ResponseBody>> modifyInfo(String token,String mid,String openid,String headimg,String headimgapp,String nickname,
                                                            String sex,String unionid,String province,String city,String area) {

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();

        data.setValue(Status.loading(null));

        Map<String, String> params = new HashMap<String, String>();

        params.put("token",token);
        params.put("mid",mid);
        params.put("nickname",nickname);
        params.put("openid",openid);
        params.put("headimg",headimg);
        params.put("headimgapp",headimgapp);
        params.put("sex",sex);
        params.put("unionid",unionid);
        params.put("province",province);
        params.put("city",city);
        params.put("area",area);
        params.put("from_type","2");

        String sign = SignUtils.signParamRemoveNull(params);

        mCreator.create(UserApi.class)
                .modifyInfo("2",token,mid,openid,headimg,headimgapp,sex,nickname,unionid,province,city,area,sign)
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

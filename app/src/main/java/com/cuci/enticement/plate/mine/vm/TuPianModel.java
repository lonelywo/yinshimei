package com.cuci.enticement.plate.mine.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cuci.enticement.bean.Status;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.network.api.MineApi;
import com.cuci.enticement.utils.SignUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TuPianModel extends ViewModel {

    private ServiceCreator mCreator;

    public TuPianModel() {
        mCreator = ServiceCreator.getInstance();
    }

    /**
     * 提交
     * @return
     */
    public MutableLiveData<Status<ResponseBody>> SCtupian(String image ) {


        // 创建 RequestBody，用于封装构建RequestBody
        File file = new File(image);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"),file);

        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("fill","tupian", requestFile);

//funName
        RequestBody funName = RequestBody.create(null, image);

        final MutableLiveData<Status<ResponseBody>> data = new MutableLiveData<>();
        data.setValue(Status.loading(null));
        mCreator.create(MineApi.class)
                .upload(funName,body)
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

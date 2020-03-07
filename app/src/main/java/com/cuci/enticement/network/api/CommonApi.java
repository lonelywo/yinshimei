package com.cuci.enticement.network.api;



import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.Version;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CommonApi {

    /**
     * 获取版本更新信息
     */
    @FormUrlEncoded
    @POST("store/api.page/appVersion")
    Call<ResponseBody> getVersion(
            @Field("from_type") String fromType,
            @Field("sign") String sign

    );
}

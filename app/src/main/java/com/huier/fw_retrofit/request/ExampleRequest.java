package com.huier.fw_retrofit.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 */
public interface ExampleRequest {

    @GET("{api}")
    Call<ResponseBody> getRxJava(@Path("api") String api);
}

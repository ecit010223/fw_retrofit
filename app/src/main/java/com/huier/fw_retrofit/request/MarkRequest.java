package com.huier.fw_retrofit.request;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 */
public interface MarkRequest {
    /**
     * 表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
     * @param name
     * @param age
     * @return
     */
    @POST("/form")
    @FormUrlEncoded
    Call<ResponseBody> formUrlEncoded(@Field("username") String name, @Field("age") int age);

    @POST("/form")
    @Multipart
    Call<ResponseBody> fileUpload(@Part("name")RequestBody name, @Part("age") RequestBody age,
                                  @Part MultipartBody.Part file);
}

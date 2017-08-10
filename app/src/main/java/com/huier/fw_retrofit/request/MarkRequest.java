package com.huier.fw_retrofit.request;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 */
public interface MarkRequest {
    /**
     * 表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
     * @param name  将该值作为@Field("username")中username的值
     * @param age
     * @return
     */
    @POST("/form")
    @FormUrlEncoded
    Call<ResponseBody> formUrlEncoded(@Field("username") String name, @Field("age") int age);

    /**
     * @param map Map的key作为表单的键
     * @return
     */
    @POST("/form")
    @FormUrlEncoded
    Call<ResponseBody> formUrlEncoded2(@FieldMap Map<String, Object> map);

    /**
     * @Part() 后面支持三种类型：RequestBody、MultipartBody.Part和任意类型，除MultipartBody.Part外，
     * 其它类型都必须带上表单字段，如@Part("name")中的name，因MultipartBody.Part中已经包含了表单字段的信息。
     * @param name
     * @param age
     * @param file
     * @return
     */
    @POST("/form")
    @Multipart
    Call<ResponseBody> fileUpload(@Part("name")RequestBody name, @Part("age") RequestBody age,
                                  @Part MultipartBody.Part file);

    /**
     * @PartMap 注解支持一个Map作为参数,支持RequestBody类型,如果有其它的类型，会被retrofit2.Converter转换.
     *         MultipartBody.Part不能被转换，所以文件只能用@Part MultipartBody.Part
     * @param args
     * @param file
     * @return
     */
    @POST("/form")
    @Multipart
    Call<ResponseBody> fileUpload2(@PartMap Map<String, RequestBody> args, @Part MultipartBody.Part file);

    @POST("/form")
    @Multipart
    Call<ResponseBody> fileUpload3(@PartMap Map<String, RequestBody> args);
}

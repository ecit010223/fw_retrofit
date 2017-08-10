package com.huier.fw_retrofit.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 * URL地址的缺省值
 * 如：访问的API是：https://api.github.com/users/{user}/repos
 * 在发起请求时， {user} 会被替换为方法的第一个参数 user（被@Path注解作用）
 */

public interface PathRequest {
    @GET("users/{user}/repos")
    Call<ResponseBody> getBlog(@Path("user") String user);
}

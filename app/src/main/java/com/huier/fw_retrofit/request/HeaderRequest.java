package com.huier.fw_retrofit.request;

import com.huier.fw_retrofit.beans.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 * 添加请求头与添加不固定的请求头
 */

public interface HeaderRequest {
    /**
     * 添加不固定的请求头
     * 作用于方法的参数
     * @param authorization
     * @return
     */
    @GET("user")
    Call<User> getUser(@Header("Authorization") String authorization);

    /**
     * 添加固定的请求头
     * 作用于方法
     * @return
     */
    @Headers("Authorization: authorization")
    @GET("user")
    Call<User> getUser();

    // 以上的效果是一致的。
}

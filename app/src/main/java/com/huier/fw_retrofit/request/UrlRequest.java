package com.huier.fw_retrofit.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 * 当有URL注解时，@GET传入的URL就可以省略
 * 当GET、POST...HTTP等方法中没有设置Url时，则必须使用 @Url提供
 */

public interface UrlRequest {
    @GET
    Call<ResponseBody> urlAndQuery(@Url String url, @Query("showAll") boolean showAll);
}

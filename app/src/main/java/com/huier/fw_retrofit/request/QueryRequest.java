package com.huier.fw_retrofit.request;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 * 用于@GET方法的查询参数（Query = Url中‘?’后面的 key-value）
 * 如：url = http://www.println.net/?cate=android，其中，Query = cate
 */

public interface QueryRequest {

    @GET("/")
    Call<String> cate(@Query("cate") String cate);

    @GET("/")
    Call<String> cate2(@QueryMap Map<String,Object> map);
}

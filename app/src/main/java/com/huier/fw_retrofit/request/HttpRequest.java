package com.huier.fw_retrofit.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 * 可以用HTTP替换@GET、@POST、@PUT、@DELETE、@HEAD注解，并有更多功能拓展。
 * 具体使用：通过属性method、path、hasBody进行设置
 */

public interface HttpRequest {
    /**
     * method：网络请求的方法（区分大小写）
     * path：网络请求地址路径
     * hasBody：是否有请求体
     * @param id 表示是一个变量
     * @return
     */
    @HTTP(method = "GET",path = "blog/{id}",hasBody = false)
    Call<ResponseBody> getCall(@Path("id") int id);
}

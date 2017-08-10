package com.huier.fw_retrofit.request;

import com.huier.fw_retrofit.beans.RxJavaBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 * 用于描述网络请求。
 * Retrofit将Http请求抽象成Java接口：采用注解描述网络请求参数和配置网络请求参数：
 * 1）用动态代理动态将该接口的注解“翻译”成一个Http请求，最后再执行Http请求；
 * 2）接口中的每个方法的参数都需要使用注解标注，否则会报错。
 */

public interface GetRequest {
    /**
     * 接收网络请求数据的方法
     * @GET注解的作用:采用Get方法发送网络请求
     * @return RxJavaBean接收数据的类,如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
     * Retrofit把网络请求的URL分成了两部分设置，一部分在如下的方法注解中；另一部分在创建Retrofit实例时通过baseUrl设置：
     * Retrofit retrofit = new Retrofit.Builder()
     *         .baseUrl("http://fanyi.youdao.com/")  //设置网络请求的url地址
     *         .addConverterFactory(GsonConverterFactory.create())  //设置数据解析器
     *         .build();
     * 路径规则如下：
     * 1）path = 完整的url
     * url = "http://host:port/aa/apath",其中：
     * path = "http://host:port/aa/apath"
     * baseUrl = 不设置
     * 即：如接口里的url是一个完整的网址，那么在创建Retrofit实例时可以不设置URL）
     * 2）path = 绝对路径
     * url = "http://host:port/apath",其中：
     * path = "/apath"
     * baseUrl = "http://host:post/a/b"
     * 3）path= 相对路径，baseUrl = 目录形式
     * url = "http://host:port/a/b/apath", 其中：
     * path = "apath"
     * baseUrl = "http://host:port/a/b/"
     * 4）path = 相对路径，base = 文件形式
     * url = "http://host:port/a/apath"，其中：
     * path = "apath"
     * baseUrl = "http://host:port/a/b
     */
    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    Call<RxJavaBean> getCall();
}

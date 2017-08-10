package com.huier.fw_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Retrofit是基于OkHttp，所以还需要添加OkHttp库依赖。
 * 网络请求的工作本质上是OkHttp完成，而Retrofit仅负责网络请求接口的封装。
 * App应用程序通过Retrofit请求网络，实际上是使用Retrofit接口层封装请求参数、Header、Url等信息，之后由OkHttp完成后续的请求操作。
 * 在服务端返回数据之后，OkHttp将原始的结果交给Retrofit，Retrofit根据用户的需求对结果进行解析。
 */

/**
 * 注解类型:
 * 1)网络请求方法：所有方法对应Http中的网络请求方法：都接收一个网络地址URL（也可以不指定，通过@Http设置）
 * @GET
 * @POST
 * @PUT
 * @DELETE
 * @PATH
 * @HEAD
 * @OPTIONS
 * @HTTP  用于替换以上7个注解的作用及更多功能拓展
 * 2)标记类： 实例见MarkRequest.java
 * @FormUrlEncoded  表示请求体是一个form表单
 * @Multipart  表示请求体是一个支持文件上传的form表单，每个键值对需要用@Filed来注解键名，随后的对象需要提供值。
 * @Streaming  表示返回的数据以流的形式返回，适用于返回数据较大的场景（如果没用该注解，默认把数据全部载入内存，并从内存读）。
 *             每个键值对需要用@Part来注解键名，随后的对象需要提供值。
 * 3)网络请求参数
 * @Header
 * @headers
 * @URL
 * @Body
 * @Path
 * @Field
 * @FieldMap
 * @Part
 * @PartMap
 * @Query
 * @QueryMap
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

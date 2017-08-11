package com.huier.fw_retrofit;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huier.fw_retrofit.request.MarkRequest;
import com.huier.fw_retrofit.request.PathRequest;
import com.huier.fw_retrofit.request.QueryRequest;
import com.huier.fw_retrofit.request.UrlRequest;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
 * @Header  添加不固定值的Header
 * @headers  添加请求头
 * @URL  直接传入一个请求的 URL变量 用于URL设置
 * @Body  用于非表单请求体
 * @Path  URL地址的缺省值
 * @Field  向POST表单传入键值对
 * @FieldMap  同上
 * @Part  用于POST表单字段，比@Field携带的参数类型更加丰富，包括数据流，所以适用于有文件上传的情况
 * @PartMap  同上
 * @Query  GET,用于表单字段，功能同@Field与@FieldMap，区别在于该参数的数据体现在URL上，@Field与@FieldMap体现在请求体上，但生成的数据是一致的
 * @QueryMap  GET,同上
 */
public class DiscoverActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity mActivity;
    private Retrofit mRetrofit;
    private MarkRequest mMarkRequest;
    private Button btnFormUrlEncodedField;
    private Button btnFormUrlEncodedFieldMap;
    private Button btnMultipartPart;
    private Button btnMultipartPart2;
    private Button btnMultipartPartMap;
    private Button btnGETQuery;
    private Button btnGETQueryMap;
    /** 当前被点击的控件 **/
    private View mCurrentClickView;

    public static void entry(Context from){
        Intent intent = new Intent(from,DiscoverActivity.class);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        mActivity = this;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_YOUDAO)
                //设置数据解析器，此处采用com.squareup.retrofit2:converter-gson
                //Retrofit支持多种数据解析方式，使用时需要在Gradle添加依赖
                //此处的转换是不是转换返回的数据，而是在调用相应自定义接口时@Path等参数的转换
                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求适配器，此处采用com.squareup.retrofit2:adapter-rxjava
                //Retrofit支持多种网络请求适配器方式：guava、Java8和rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mMarkRequest = mRetrofit.create(MarkRequest.class);
        initView();
    }

    private void initView(){
        btnFormUrlEncodedField = (Button)findViewById(R.id.btn_FormUrlEncoded_Field);
        btnFormUrlEncodedField.setOnClickListener(this);
        btnFormUrlEncodedFieldMap = (Button)findViewById(R.id.btn_FormUrlEncoded_FieldMap);
        btnFormUrlEncodedFieldMap.setOnClickListener(this);
        btnMultipartPart = (Button)findViewById(R.id.btn_Multipart_Part);
        btnMultipartPart.setOnClickListener(this);
        btnMultipartPart2 = (Button)findViewById(R.id.btn_Multipart_Part2);
        btnMultipartPart2.setOnClickListener(this);
        btnMultipartPartMap = (Button)findViewById(R.id.btn_Multipart_PartMap);
        btnMultipartPartMap.setOnClickListener(this);
        btnGETQuery = (Button)findViewById(R.id.btn_GET_Query);
        btnGETQuery.setOnClickListener(this);
        btnGETQueryMap = (Button)findViewById(R.id.btn_GET_QueryMap);
        btnGETQueryMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(requestPermission()){
            switch (view.getId()){
                case R.id.btn_FormUrlEncoded_Field:
                    formUrlEncodedField();
                    break;
                case R.id.btn_FormUrlEncoded_FieldMap:
                    formUrlEncodedFieldMap();
                    break;
                case R.id.btn_Multipart_Part:
                    multipartPart();
                    break;
                case R.id.btn_Multipart_Part2:
                    multipartPart2();
                    break;
                case R.id.btn_Multipart_PartMap:
                    multipartPartMap();
                    break;
                case R.id.btn_GET_Query:
                    getQuery();
                    break;
                case R.id.btn_GET_QueryMap:
                    getQueryMap();
                    break;
            }
        }else{
            mCurrentClickView = view;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case Constants.INTERNET_REQUEST_CODE:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    onClick(mCurrentClickView);
                }
                break;
        }
    }

    /** 申请网络访问权限 **/
    private boolean requestPermission(){
        boolean hasPermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED;
        if(!hasPermission){
            ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.INTERNET},
                    Constants.INTERNET_REQUEST_CODE);
            ActivityCompat.shouldShowRequestPermissionRationale(mActivity,Manifest.permission.INTERNET);
        }
        return hasPermission;
    }

    /** \@FormUrlEncoded标记，@Field参数 **/
    private void formUrlEncodedField(){
        // @FormUrlEncoded以@Field定义
        Call<ResponseBody> formUrlEncodedCall = mMarkRequest.formUrlEncoded("Carson",24);
        //发送网络请求（异步）
        formUrlEncodedCall.enqueue(new Callback<ResponseBody>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求处理,输出结果
                try {
                    Log.d(Constants.TAG,"@FormUrlEncoded的@Field："+response.body().string());
                } catch (IOException e) {
                    Log.d(Constants.TAG,"@FormUrlEncoded的@Field IOException："+e.toString());
                }
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败");
            }
        });
    }

    /** \@FormUrlEncoded标记，@FieldMap参数 **/
    private void formUrlEncodedFieldMap(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username","Carson");
        map.put("age",24);
        //@FormUrlEncoded以@FieldMap定义参数
        Call<ResponseBody> formUrlEncodedCall2 = mMarkRequest.formUrlEncoded2(map);
        //发送网络请求（异步）
        formUrlEncodedCall2.enqueue(new Callback<ResponseBody>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求处理,输出结果
                try {
                    Log.d(Constants.TAG,"@FormUrlEncoded的@FieldMap："+response.body().string());
                } catch (IOException e) {
                    Log.d(Constants.TAG,"@FormUrlEncoded的@FieldMap IOException："+e.toString());
                }
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败");
            }
        });
    }

    /** \@Multipart标记，@Part参数 **/
    private void multipartPart(){
        RequestBody name =  RequestBody.create(Constants.MEDIA_TYPE_TEXT,"Carson");
        RequestBody age = RequestBody.create(Constants.MEDIA_TYPE_TEXT,"24");

        File file = new File("README.md");
        RequestBody fileRequestBody = RequestBody.create(Constants.MEDIA_TYPE_MARKDOWN,file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file","test.txt",fileRequestBody);
        // @Multipart以@Part参数定义
        Call<ResponseBody> fileUploadCall = mMarkRequest.fileUpload(name,age,filePart);
        //发送网络请求（异步）
        fileUploadCall.enqueue(new Callback<ResponseBody>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求处理,输出结果
                try {
                    Log.d(Constants.TAG,"@Multipart的@Part："+response.body().string());
                } catch (IOException e) {
                    Log.d(Constants.TAG,"@Multipart的@Part IOException："+e.toString());
                }
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败");
            }
        });
    }

    /** \@Multipart标记，@Part参数 **/
    private void multipartPart2(){
        RequestBody name = RequestBody.create(Constants.MEDIA_TYPE_TEXT, "Carson");
        RequestBody age = RequestBody.create(Constants.MEDIA_TYPE_TEXT, "24");
        RequestBody file = RequestBody.create(Constants.MEDIA_TYPE_STREAM, "这里是模拟文件的内容");

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.txt", file);
        // @Multipart以@Part参数定义
        Call<ResponseBody> fileUploadCall = mMarkRequest.fileUpload(name, age, filePart);
        //发送网络请求（异步）
        fileUploadCall.enqueue(new Callback<ResponseBody>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求处理,输出结果
                try {
                    Log.d(Constants.TAG,"@Multipart的@Part："+response.body().string());
                } catch (IOException e) {
                    Log.d(Constants.TAG,"@Multipart的@Part IOException："+e.toString());
                }
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败");
            }
        });
    }

    /** \@Multipart标记，@PartMap参数 **/
    private void multipartPartMap(){
        RequestBody name = RequestBody.create(Constants.MEDIA_TYPE_TEXT, "Carson");
        RequestBody age = RequestBody.create(Constants.MEDIA_TYPE_TEXT, "24");

        Map<String, RequestBody> fileUpload2Args = new HashMap<>();
        fileUpload2Args.put("name", name);
        fileUpload2Args.put("age", age);

        RequestBody file = RequestBody.create(Constants.MEDIA_TYPE_STREAM, "这里是模拟文件的内容");
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.txt", file);
        //这里并不会被当成文件，因为没有文件名(包含在Content-Disposition请求头中)，但上面的 filePart 有
        //fileUpload2Args.put("file", file);
        // @Multipart以@PartMap参数定义
        Call<ResponseBody> fileUploadCall2 = mMarkRequest.fileUpload2(fileUpload2Args, filePart); //单独处理文件
        //发送网络请求（异步）
        fileUploadCall2.enqueue(new Callback<ResponseBody>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求处理,输出结果
                try {
                    Log.d(Constants.TAG,"@Multipart的@PartMap："+response.body().string());
                } catch (IOException e) {
                    Log.d(Constants.TAG,"@Multipart的@PartMap IOException："+e.toString());
                }
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败");
            }
        });
    }

    /** \@GET标记，@Query参数 **/
    private void getQuery(){
        QueryRequest queryRequest = mRetrofit.create(QueryRequest.class);
        //@GET以@Query参数定义
        Call<String> cateCall = queryRequest.cate("android");
        //发送网络请求（异步）
        cateCall.enqueue(new Callback<String>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //请求处理,输出结果
                Log.d(Constants.TAG,"@GET的@Query："+response.toString());
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败");
            }
        });
    }

    /** \@GET标记，@QueryMap参数 **/
    private void getQueryMap(){
        QueryRequest queryRequest = mRetrofit.create(QueryRequest.class);
        Map<String,Object> map = new HashMap<>();
        map.put("cate","android");
        //@GET以@QueryMap参数定义
        Call<String> cateCall2 = queryRequest.cate2(map);
        //发送网络请求（异步）
        cateCall2.enqueue(new Callback<String>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //请求处理,输出结果
                Log.d(Constants.TAG,"@GET的@QueryMap："+response.toString());
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败");
            }
        });
    }

    /** \@GET标记，@Path参数 **/
    private void getPath(){
        PathRequest pathRequest = mRetrofit.create(PathRequest.class);
        Call<ResponseBody> getBlogCall = pathRequest.getBlog("android");
        //发送网络请求（异步）
        getBlogCall.enqueue(new Callback<ResponseBody>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求处理,输出结果
                try {
                    Log.d(Constants.TAG,"@GET@Path："+response.body().string());
                } catch (IOException e) {
                    Log.d(Constants.TAG,"@GET@Path IOException："+e.toString());
                }
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败");
            }
        });
    }

    /** \@GET标记，@Url参数 **/
    private void getUrl(){
        UrlRequest urlRequest = mRetrofit.create(UrlRequest.class);
        Call<ResponseBody> urlAndQueryCall = urlRequest.urlAndQuery(Constants.URL_RXJAVA,true);
        //发送网络请求（异步）
        urlAndQueryCall.enqueue(new Callback<ResponseBody>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求处理,输出结果
                try {
                    Log.d(Constants.TAG,"@GET@Url："+response.body().string());
                } catch (IOException e) {
                    Log.d(Constants.TAG,"@GET@Url IOException："+e.toString());
                }
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败");
            }
        });
    }
}

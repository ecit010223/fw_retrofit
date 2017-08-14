package com.huier.fw_retrofit.encapsulate;

import com.huier.fw_retrofit.Constants;
import com.huier.fw_retrofit.beans.YouDaoTranslation;
import com.huier.fw_retrofit.request.ExampleRequest;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：张玉辉
 * 时间：2017/8/14.
 */

public class HttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private ExampleRequest mExampleRequest;

    /** 构造方法私有 **/
    private HttpMethods(){
        //手动设置一个OkHttpClient并设置超时时间
        OkHttpClient.Builder okHttpClientBuild = new OkHttpClient.Builder();
        okHttpClientBuild.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder().client(okHttpClientBuild.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.URL_YOUDAO)
                .build();
        mExampleRequest = mRetrofit.create(ExampleRequest.class);
    }

    /** 创建单例 **/
    public static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    /** 获取单例 **/
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /** 有道词汇查询 **/
    public void askYouDao(Observer<YouDaoTranslation> observer,String srcVoc){
        mExampleRequest.askYouDao(srcVoc)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

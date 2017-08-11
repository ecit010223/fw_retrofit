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

import com.huier.fw_retrofit.beans.Translation;
import com.huier.fw_retrofit.beans.YouDaoTranslation;
import com.huier.fw_retrofit.request.ExampleRequest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExampleActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity mActivity;
    //实例演示
    private Retrofit mRetrofit;
    private Button btnBasicUse,btnICIBA,btnYouDao;

    /** 当前被单击的控件 **/
    private View mCurrentClickView;

    public static void entry(Context from){
        Intent intent = new Intent(from,ExampleActivity.class);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        mActivity = this;
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.URL_RXJAVA)
                .build();
        initView();
    }

    private void initView(){
        btnBasicUse = (Button)findViewById(R.id.btn_basic_use);
        btnBasicUse.setOnClickListener(this);
        btnICIBA = (Button)findViewById(R.id.btn_iciba);
        btnICIBA.setOnClickListener(this);
        btnYouDao = (Button)findViewById(R.id.btn_youdao);
        btnYouDao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(requestPermission()){
            switch (view.getId()) {
                case R.id.btn_basic_use:
                    basicUse();
                    break;
                case R.id.btn_iciba:
                    requestICIBA();
                    break;
                case R.id.btn_youdao:
                    requestYouDao();
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

    private boolean requestPermission(){
        boolean hasPermission = ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED;
        if(!hasPermission){
            ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.INTERNET},
                    Constants.INTERNET_REQUEST_CODE);
            ActivityCompat.shouldShowRequestPermissionRationale(mActivity,Manifest.permission.INTERNET);
        }
        return hasPermission;
    }

    /** 基本使用，访问了本机制node server上的rxjava.json数据 **/
    private void basicUse(){
        Log.d(Constants.TAG,"basicUse()");
        ExampleRequest exampleRequest = mRetrofit.create(ExampleRequest.class);
        Call<ResponseBody> rxJavaCall = exampleRequest.getRxJava("rxjava");
        //发送网络请求（异步）
        rxJavaCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求处理,输出结果
                try {
                    Log.d(Constants.TAG,"success"+response.body().string());
                } catch (IOException e) {
                    Log.d(Constants.TAG,"IOException："+e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constants.TAG, "请求失败"+t.toString());
            }
        });
    }

    /** 访问金山词霸上的数据 **/
    private void requestICIBA(){
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_CIBA)  //设置网络请求Url
                .addConverterFactory(GsonConverterFactory.create())  //设置使用Gson解析(记得加入依赖)
                .build();
        //创建网络请求接口的实例
        ExampleRequest exampleRequest = retrofit.create(ExampleRequest.class);
        //对发送请求进行封装
        Call<Translation> askICIBACall = exampleRequest.askCIBA();
        //发送网络请求(异步)
        askICIBACall.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                Log.d(Constants.TAG,response.body().show());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Log.d(Constants.TAG,"网络请求失败");
            }
        });
    }

    /** 有道词典查寻 **/
    private void requestYouDao(){
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_YOUDAO)
                .addConverterFactory(GsonConverterFactory.create())  //对请求的数据进行转换
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //对返回的数据进行转换
                .build();
        //创建网络请求接口的实例
        ExampleRequest exampleRequest = retrofit.create(ExampleRequest.class);
        //发送请求 进行封装(设置需要翻译的内容)
        exampleRequest.askYouDao("I love you")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<YouDaoTranslation>(){});
//        askYouDaoCall.enqueue(new Callback<YouDaoTranslation>() {
//            @Override
//            public void onResponse(Call<YouDaoTranslation> call, Response<YouDaoTranslation> response) {
//                Log.d(Constants.TAG,response.body().getTranslateResult().get(0).get(0).getTgt());
//            }
//
//            @Override
//            public void onFailure(Call<YouDaoTranslation> call, Throwable t) {
//                Log.d(Constants.TAG,"网络请求失败");
//            }
//        });

    }
}

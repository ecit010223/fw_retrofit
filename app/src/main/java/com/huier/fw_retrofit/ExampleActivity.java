package com.huier.fw_retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huier.fw_retrofit.request.MyRequest;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExampleActivity extends AppCompatActivity implements View.OnClickListener {
    //实例演示
    private Retrofit myRetrofit;
    private Button btnBasicUse;

    public static void entry(Context from){
        Intent intent = new Intent(from,ExampleActivity.class);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        myRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.URL_RXJAVA)
                .build();
        initView();
    }

    private void initView(){
        btnBasicUse = (Button)findViewById(R.id.btn_basic_use);
        btnBasicUse.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_basic_use:
                basicUse();
                break;
        }
    }

    private void basicUse(){
        Log.d(Constants.TAG,"basicUse()");
        MyRequest myRequest = myRetrofit.create(MyRequest.class);
        Call<ResponseBody> rxJavaCall = myRequest.getRxJava("rxjava");
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
}

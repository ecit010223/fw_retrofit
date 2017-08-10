package com.huier.fw_retrofit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huier.fw_retrofit.request.MarkRequest;
import com.huier.fw_retrofit.request.PathRequest;
import com.huier.fw_retrofit.request.QueryRequest;
import com.huier.fw_retrofit.request.UrlRequest;
import com.huier.fw_retrofit.request.MyRequest;

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
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity mActivity;
    private Button btnDiscover;
    private Button btnExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        initView();
    }

    private void initView(){
        btnDiscover = (Button)findViewById(R.id.btn_discover);
        btnDiscover.setOnClickListener(this);
        btnExample = (Button)findViewById(R.id.btn_example);
        btnExample.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_discover:
                DiscoverActivity.entry(mActivity);
                break;
            case R.id.btn_example:
                ExampleActivity.entry(mActivity);
                break;
        }
    }
}

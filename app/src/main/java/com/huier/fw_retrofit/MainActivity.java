package com.huier.fw_retrofit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


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

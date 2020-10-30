package com.example.networklibraryexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.networklibraryexample.NetworkAPI_Library.AndroidAsyncHttpExample;
import com.example.networklibraryexample.NetworkAPI_Library.FastAndroidNetworkingExample;
import com.example.networklibraryexample.NetworkAPI_Library.OKHttpExample;
import com.example.networklibraryexample.NetworkAPI_Library.RetrofitExamples.RetrofitExample;
import com.example.networklibraryexample.NetworkAPI_Library.VolleyExample;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button okHttp3, retrofit2, volley, android_async_http, fast_android_networking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        okHttp3.setOnClickListener(this);
        retrofit2.setOnClickListener(this);
        volley.setOnClickListener(this);
        android_async_http.setOnClickListener(this);
        fast_android_networking.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(view==okHttp3)
            startActivity(new Intent(this, OKHttpExample.class));
        else if(view == retrofit2)
            startActivity(new Intent(this, RetrofitExample.class));
        else if(view==volley)
            startActivity(new Intent(this, VolleyExample.class));
        else if(view==android_async_http)
            startActivity(new Intent(this, AndroidAsyncHttpExample.class));
        else if(view==fast_android_networking)
            startActivity(new Intent(this, FastAndroidNetworkingExample.class));
    }
    public void initView(){
        okHttp3 = (Button)findViewById(R.id.bt_okHttp3);
        retrofit2 = (Button)findViewById(R.id.bt_retrofit2);
        volley = (Button)findViewById(R.id.bt_volley);
        android_async_http = (Button)findViewById(R.id.bt_android_async_http);
        fast_android_networking = (Button)findViewById(R.id.bt_fast_android_networking);
    }
}

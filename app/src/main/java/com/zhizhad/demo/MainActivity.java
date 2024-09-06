package com.zhizhad.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.zhizh.ad.tv.ZzAdSdk;
import com.zhizh.ad.tv.http.exception.OfferException;
import com.zhizh.ad.tv.http.model.AdOffer;
import com.zhizh.ad.tv.http.model.ZzNativeAd;
import com.zhizh.ad.tv.listener.OnLoadAdsListener;

import java.util.List;


/**
 * @author lihui
 * @date 2024/9/5
 * @desc
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置状态栏颜色（例如白色）
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.first_layout);
        findViewById(R.id.bt_open).setOnClickListener(v ->{
            startActivity(new Intent(this,AdListActivity.class));
        });

    }
}

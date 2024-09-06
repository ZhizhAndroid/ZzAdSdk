package com.zhizhad.demo;

import android.app.Application;

import com.zhizh.ad.tv.ZzAdSdk;

/**
 * @author lihui
 * @date 2024/9/6
 * @desc
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //在这里进行sdk的初始化.
        ZzAdSdk.getInstance().initialize(this, "abcdefg");
        //设置用户id，方便回调广告效果
        ZzAdSdk.getInstance().setUserId("100001");
    }
}

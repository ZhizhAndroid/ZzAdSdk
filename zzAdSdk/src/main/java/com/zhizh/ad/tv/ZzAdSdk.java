package com.zhizh.ad.tv;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.zhizh.ad.tv.utils.HttpUtils;
import com.zhizh.ad.tv.http.model.AdData;
import com.zhizh.ad.tv.http.model.AdOffer;
import com.zhizh.ad.tv.http.model.ZzNativeAd;
import com.zhizh.ad.tv.listener.OnLoadAdsListener;
import com.zhizh.ad.tv.utils.AdUtils;
import com.zhizh.ad.tv.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lihui
 * @date 2024/9/5
 * @desc
 */
public class ZzAdSdk {
    private static ZzAdSdk instance;
    private Context context;
    private String developerKey;
    private boolean isInitialized = false;
    private AdData adData;
    private List<ZzNativeAd> adList = new ArrayList<>();

    // 获取单例实例
    public static synchronized ZzAdSdk getInstance() {
        if (instance == null) {
            instance = new ZzAdSdk();
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    // 初始化方法，传入开发者key和上下文
    public void initialize(Context context, String developerKey) {
        if (isInitialized) {
            // 如果已经初始化，直接返回
            return;
        }
        AdUtils.initAdid(context);
        if (context == null || developerKey == null || developerKey.isEmpty()) {
            throw new IllegalArgumentException("Context and developer key must not be null or empty.");
        }
        this.context = context.getApplicationContext();
        this.developerKey = developerKey;
        this.isInitialized = true;

        // 在这里添加你需要的初始化逻辑
        // 比如：验证开发者密钥、加载广告配置等

        Log.d("AdSdkInitializer", "SDK initialized with developer key: " + developerKey);
    }

    /**
     * 设置用户id
     *
     * @param userid
     */
    public void setUserId(String userid) {
//        this.userid=userid;
        SPUtils.getInstance().put("USERID", userid);
    }

    private OnLoadAdsListener listener;

    /**
     * 获取广告数据
     *
     * @param listener
     */
    public void getListOfAds(OnLoadAdsListener listener) {
        this.listener = listener;
        HttpUtils.sendPostRequestAsync("https://apifoxmock.com/m2/5118730-4781838-default/212772387?appkey=" + developerKey + "&placementid=11", HttpUtils.buildJson(context), new HttpUtils.Callback() {
            @Override
            public void onSuccess(String response) {
                adData = new Gson().fromJson(response, AdData.class);
                adList.clear();
                for (AdOffer adOffer : adData.getOffers()) {
                    Bitmap qr_code=AdUtils.decodeBase64ToBitmap(adOffer.getNative1().getClick_qrcode());
                    adList.add(new ZzNativeAd(
                            adOffer.getNative1().getTitle(),
                            adOffer.getNative1().getAppid(),
                            adOffer.getNative1().getDesc(),
                            adOffer.getNative1().getIcon(),
                            adOffer.getNative1().getBanner(),
                            adOffer.getNative1().getRating(),
                            adOffer.getNative1().getClick_url(),
                            adOffer.getNative1().getOffer_id(),
                            qr_code));
                }

                listener.onLoadAdsSuccess(adList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onLoadAdsFail(e);
            }
        });
    }

    /**
     * 广告展示上报
     *
     * @param appid
     */
    public void setAdShow(String appid) {
        for (AdOffer adOffer : adData.getOffers()) {
            if (adOffer.getNative1().getAppid().equals(appid)) {
                for (String url : adOffer.getStat().getImp_callback()) {
                    HttpUtils.sendGetRequestWithRetry(url);
                }
            }
        }
    }

    /**
     * 广告点击上报
     *
     * @param appid
     */
    public void setAdClick(String appid) {
        for (AdOffer adOffer : adData.getOffers()) {
            if (adOffer.getNative1().getAppid().equals(appid)) {
                for (String url : adOffer.getStat().getClick_callback()) {
                    HttpUtils.sendGetRequestWithRetry(url);
                }
            }
        }
    }


}

package com.zhizh.ad.tv.listener;

import com.zhizh.ad.tv.http.exception.OfferException;
import com.zhizh.ad.tv.http.model.AdOffer;
import com.zhizh.ad.tv.http.model.ZzNativeAd;

import java.util.List;

/**
 * @author lihui
 * @date 2024/9/5
 * @desc
 */
public interface OnLoadAdsListener {
    void onLoadAdsSuccess(List<ZzNativeAd> offerList);
    void onLoadAdsFail(Exception e);
}

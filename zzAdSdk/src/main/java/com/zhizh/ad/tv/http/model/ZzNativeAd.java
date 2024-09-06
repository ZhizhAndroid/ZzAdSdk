package com.zhizh.ad.tv.http.model;

import android.graphics.Bitmap;

/**
 * @author lihui
 * @date 2024/9/6
 * @desc
 */
public class ZzNativeAd {
    private String title;
    private String appid;
    private String desc;
    private String icon;
    private String banner;
    private float rating;
    private String click_url;
    private String offer_id;

    private Bitmap qr_code;

    public Bitmap getQr_code() {
        return qr_code;
    }

    public void setQr_code(Bitmap qr_code) {
        this.qr_code = qr_code;
    }

    public ZzNativeAd(String title, String appid, String desc, String icon, String banner, float rating, String click_url, String offer_id, Bitmap qr_code) {
        this.title = title;
        this.appid = appid;
        this.desc = desc;
        this.icon = icon;
        this.banner = banner;
        this.rating = rating;
        this.click_url = click_url;
        this.offer_id = offer_id;
        this.qr_code=qr_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }



    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }



}

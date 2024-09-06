package com.zhizh.ad.tv.http.model;

import java.util.List;

/**
 * @author lihui
 * @date 2024/9/6
 * @desc
 */
public class AdOffer {
    private Native1 native1;
    private Stat stat;
    private Native1 price_info;

    public Native1 getNative1() {
        return native1;
    }

    public void setNative1(Native1 native1) {
        this.native1 = native1;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public Native1 getPrice_info() {
        return price_info;
    }

    public void setPrice_info(Native1 price_info) {
        this.price_info = price_info;
    }

    public static class Native1 {
        private String title;
        private String appid;
        private String desc;
        private String click_qrcode;

        public String getClick_qrcode() {
            return click_qrcode;
        }

        public void setClick_qrcode(String click_qrcode) {
            this.click_qrcode = click_qrcode;
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

        public String getCta() {
            return cta;
        }

        public void setCta(String cta) {
            this.cta = cta;
        }

        public String getDeeplink_url() {
            return deeplink_url;
        }

        public void setDeeplink_url(String deeplink_url) {
            this.deeplink_url = deeplink_url;
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

        public Object getExt() {
            return ext;
        }

        public void setExt(Object ext) {
            this.ext = ext;
        }

        public String getPreview_url() {
            return preview_url;
        }

        public void setPreview_url(String preview_url) {
            this.preview_url = preview_url;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        private String icon;
        private String banner;
        private float rating;
        private String cta;
        private String deeplink_url;
        private String click_url;
        private String offer_id;
        private Object ext;
        private String preview_url;
        private String notes;
    }

    public static class Stat {
        private List<String> deeplink_success_callback;
        private List<String> deeplink_fail_callback;
        private List<String> click_callback;
        private List<String> imp_callback;
        private List<String> install_callback;

        public List<String> getDeeplink_success_callback() {
            return deeplink_success_callback;
        }

        public void setDeeplink_success_callback(List<String> deeplink_success_callback) {
            this.deeplink_success_callback = deeplink_success_callback;
        }

        public List<String> getDeeplink_fail_callback() {
            return deeplink_fail_callback;
        }

        public void setDeeplink_fail_callback(List<String> deeplink_fail_callback) {
            this.deeplink_fail_callback = deeplink_fail_callback;
        }

        public List<String> getClick_callback() {
            return click_callback;
        }

        public void setClick_callback(List<String> click_callback) {
            this.click_callback = click_callback;
        }

        public List<String> getImp_callback() {
            return imp_callback;
        }

        public void setImp_callback(List<String> imp_callback) {
            this.imp_callback = imp_callback;
        }

        public List<String> getInstall_callback() {
            return install_callback;
        }

        public void setInstall_callback(List<String> install_callback) {
            this.install_callback = install_callback;
        }
    }
}

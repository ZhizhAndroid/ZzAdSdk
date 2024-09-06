package com.zhizh.ad.tv.utils;

import static android.content.Context.UI_MODE_SERVICE;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.util.UUID;
import java.util.concurrent.Executors;

/**
 * @author lihui
 * @date 2024/9/5
 * @desc
 */
public class AdUtils {

    /**
     * 判断当前设备是机顶盒还是手机
     *
     * @param context
     * @return
     */
    protected static boolean isTv(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService(UI_MODE_SERVICE);
        return uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION;
    }

    // 将 Base64 字符串转换为 Bitmap
    public static Bitmap decodeBase64ToBitmap(String base64String) {
        // 解码 Base64 字符串
        byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);

        // 将字节数组转换为 Bitmap
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    protected static String getUserAgent() {
        StringBuilder sb = new StringBuilder();
        String userAgent = System.getProperty("http.agent");
        if (userAgent != null) {
            for (int i = 0; i < userAgent.length(); i++) {
                char c = userAgent.charAt(i);
                if (c <= '\u001f' || c >= '\u007f') {
                    sb.append(String.format("\\u%04x", (int) c));
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static void initAdid(Context context){
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                String adid = AdvertisingIdClient.getGoogleAdId(context);
                String gaid = SPUtils.getInstance().getString("GAID");
                if (!TextUtils.isEmpty(adid) && !adid.equals("00000000-0000-0000-0000-000000000000")) {
                    if (TextUtils.isEmpty(gaid)) {
                        SPUtils.getInstance().put("GAID", adid);
                    }
                } else if (TextUtils.isEmpty(gaid)) {
                    SPUtils.getInstance().put("GAID", UUID.randomUUID().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Method to get the app version name
    public static String getAppVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName; // Version name
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to get the app version code
    public static int getAppVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode; // Version code
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }


}

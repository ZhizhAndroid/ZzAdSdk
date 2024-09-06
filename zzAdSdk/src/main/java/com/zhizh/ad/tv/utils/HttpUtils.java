package com.zhizh.ad.tv.utils;

/**
 * @author lihui
 * @date 2024/9/5
 * @desc
 */

import android.content.Context;
import android.os.Build;

import com.google.gson.Gson;
import com.zhizh.ad.tv.utils.AdUtils;
import com.zhizh.ad.tv.utils.SPUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HttpUtils {


    public static String buildJson(Context context) {
        // Creating the outermost Map
        Map<String, Object> data = new HashMap<>();
        String gaid = SPUtils.getInstance().getString("GAID");
        String userid = SPUtils.getInstance().getString("USERID");
        // Populating the "app" section
        Map<String, String> app = new HashMap<>();
        app.put("adv_id", gaid);
        app.put("appid", context.getPackageName());
        app.put("cname", AdUtils.getAppVersionName(context));
        app.put("cversion", String.valueOf(AdUtils.getAppVersionCode(context)));
        app.put("uid", userid);
        Locale locale = Locale.getDefault();
        // Get the language code (e.g., "en" for English)
        String languageCode = locale.getLanguage();
        // Get the country code (e.g., "US" for the United States)
        String countryCode = locale.getCountry();
        // Populating the "device" section
        Map<String, String> device = new HashMap<>();
        device.put("pversion", "1");
        device.put("os", "android");
        device.put("ua", AdUtils.getUserAgent());
        device.put("lang", languageCode);
        device.put("local", countryCode);
        device.put("mode", Build.MODEL);
        device.put("net_type", "WIFI");
        device.put("device_type", AdUtils.isTv(context) ? "ctv" : "phone");
        device.put("sys_code", String.valueOf(Build.VERSION.SDK_INT));
        device.put("sys_name", Build.VERSION.RELEASE);
        device.put("imei", "");
        device.put("imsi", "");
        device.put("operator", "");
        device.put("tz", "");
        device.put("adv_id", gaid);
        device.put("userid", userid);

        // Populating the "imp" section
        Map<String, Object> imp = new HashMap<>();
        Map<String, Integer> native1 = new HashMap<>();
        native1.put("h", 1200);
        native1.put("w", 627);
        imp.put("native1", native1);
        imp.put("num", 999);
        // Adding all sections to the main map
        data.put("app", app);
        data.put("device", device);
        data.put("imp", imp);
        Gson gson = new Gson();
        return gson.toJson(data);
    }


    // 回调接口
    public interface Callback {
        void onSuccess(String response);

        void onFailure(Exception e);
    }

    // 异步发起 HTTP POST 请求的方法
    public static void sendPostRequestAsync(final String urlString, final String jsonInputString, final Callback callback) {
        new Thread(() -> {
            try {
                String response = sendPostRequest(urlString, jsonInputString);
                // 在主线程中调用回调的 onSuccess 方法
                runOnUiThread(() -> callback.onSuccess(response));
            } catch (Exception e) {
                // 在主线程中调用回调的 onFailure 方法
                runOnUiThread(() -> callback.onFailure(e));
            }
        }).start();
    }

    // 发起 HTTP POST 请求的方法
    private static String sendPostRequest(String urlString, String jsonInputString) throws Exception {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            // 创建 URL 对象
            URL url = new URL(urlString);
            // 打开连接
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            // 发送 POST 请求
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 读取响应
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // 返回响应结果
            return response.toString();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    // 在主线程中运行代码的辅助方法
    private static void runOnUiThread(Runnable action) {
        // 这里使用 Handler 来在主线程中运行代码
        new android.os.Handler(android.os.Looper.getMainLooper()).post(action);
    }


    // 异步发起 HTTP GET 请求的方法，失败时重试最多 3 次
    public static void sendGetRequestWithRetry(final String urlString) {
        new Thread(() -> {
            int retryCount = 0;
            boolean success = false;
            while (retryCount < 3 && !success) {
                try {
                    sendGetRequest(urlString);
                    success = true; // 请求成功
                } catch (Exception e) {
                    retryCount++;
                    try {
                        Thread.sleep(5000); // 等待 1 秒后重试
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt(); // 处理线程中断
                    }
                }
            }
        }).start();
    }

    // 发起 HTTP GET 请求的方法
    private static String sendGetRequest(String urlString) throws Exception {
        HttpURLConnection connection = null;
        try {
            // 创建 URL 对象
            URL url = new URL(urlString);
            // 打开连接
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // 读取响应
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // 返回响应结果
            return response.toString();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}


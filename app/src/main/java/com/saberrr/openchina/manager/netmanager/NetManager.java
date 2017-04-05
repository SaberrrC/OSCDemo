package com.saberrr.openchina.manager.netmanager;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Saberrr on 2017-04-01.
 */

public class NetManager {
    private static NetManager sNetManager = new NetManager();

    private NetManager() {
    }

    public static NetManager getInstance() {
        return sNetManager;
    }

    public String getJson(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写的超时时间
                .build();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getJson(String url, Map<String, String> headMap) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写的超时时间
                .build();

        Request.Builder builder = new Request.Builder().url(url);
        if (headMap != null) {
            for (String key : headMap.keySet()) {
                String value = headMap.get(key);
                builder.addHeader(key, value);
            }
        }
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResponseBody getBody(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写的超时时间
                .build();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPostJson(String url, RequestBody body) {
        // 定义okhttp
        OkHttpClient okHttpClient_post_kv = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写的超时时间
                .build();
        // 定义请求体
        // 执行okhttp
        Request.Builder builder = new Request.Builder().url(url);
        if (body != null) {
            builder.post(body);
        }
        Request request = builder.build();
        Response response = null;
        try {
            response = okHttpClient_post_kv.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

package com.saberrr.openchina.manager.netmanager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Saberrr on 2017-03-25.
 */

public class NetMangager {
    private static NetMangager sNetMangager = new NetMangager();

    private NetMangager() {
    }

    public static synchronized NetMangager getInstance() {
        return sNetMangager;
    }

    public String getJson(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(1,TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(1,TimeUnit.SECONDS)//设置写的超时时间
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

    public ResponseBody getBody(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(5,TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(5,TimeUnit.SECONDS)//设置写的超时时间
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
}

package com.saberrr.openchina.manager.netmanager;

import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tt on 2017/3/4.
 */

public class RetrofitUtil {

    private static Retrofit retrofit;

    /**
     * 初始化Retrofit实例
     * @return retrofit 实例
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL)//设置基础地址
                    .addConverterFactory(GsonConverterFactory.create())//json转换
                    .build();
        }
        return retrofit;
    }


    /**
     * 实例化接口类
     * @return 接口实例
     */
    public static HttpServiceApi getHttpServiceInstance() {
        return getRetrofitInstance().create(HttpServiceApi.class);
    }
}

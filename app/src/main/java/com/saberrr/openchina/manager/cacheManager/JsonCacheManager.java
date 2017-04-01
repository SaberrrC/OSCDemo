package com.saberrr.openchina.manager.cacheManager;

import android.text.TextUtils;

import com.saberrr.openchina.utils.GsonTools;

import java.util.List;

/**
 * Created by Saberrr on 2017-03-25.
 */

public class JsonCacheManager {

    private static JsonCacheManager sJsonCacheManager = new JsonCacheManager();

    private JsonCacheManager() {
    }

    public static synchronized JsonCacheManager getInstance() {
        return sJsonCacheManager;
    }

    public<T> T getDataBean(String url, Class<T> bean) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetMangager.getInstance().getJson(url);
        if (TextUtils.isEmpty(json)) {
            json = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, json);
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToBean(json, bean);
        }
    }

    public <T> List<T> getDataList(String url, Class<T> t) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetMangager.getInstance().getJson(url);
        if (TextUtils.isEmpty(json)) {
            json = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, json);
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            //            return GsonUtil.fromJsonArray(json, t);
            return GsonTools.changeGsonToList(json, t);
        }
    }
}

package com.saberrr.openchina.manager.cacheManager;

import android.text.TextUtils;
import android.util.Log;

import com.saberrr.openchina.manager.netmanager.NetManager;
import com.saberrr.openchina.utils.GsonTools;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

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

    public <T> T getDataBean(String url, Class<T> bean) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetManager.getInstance().getJson(url);
        if (TextUtils.isEmpty(json)) {
            json = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, json);
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            Log.d("缓存==", "getDataBean: ======" + json);
            try {
                return GsonTools.changeGsonToBean(json, bean);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public <T> T getDataBean(String url, Map<String, String> headmap, Class<T> bean) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetManager.getInstance().getJson(url, headmap);
        if (TextUtils.isEmpty(json)) {
            json = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, json);
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return GsonTools.changeGsonToBean(json, bean);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public <T> T getDataBean(String url, RequestBody body, Class<T> bean) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetManager.getInstance().getPostJson(url, body);
        if (TextUtils.isEmpty(json)) {
            json = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, json);
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return GsonTools.changeGsonToBean(json, bean);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    public <T> List<T> getDataList(String url, Class<T> t) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetManager.getInstance().getJson(url);
        if (TextUtils.isEmpty(json)) {
            json = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, json);
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return GsonTools.changeGsonToList(json, t);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public <T> List<T> getDataList(String url, Map<String, String> headmap, Class<T> t) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetManager.getInstance().getJson(url, headmap);
        if (TextUtils.isEmpty(json)) {
            json = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, json);
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return GsonTools.changeGsonToList(json, t);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public <T> List<T> getDataList(String url, RequestBody body, Class<T> t) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetManager.getInstance().getPostJson(url, body);
        if (TextUtils.isEmpty(json)) {
            json = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, json);
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return GsonTools.changeGsonToList(json, t);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}

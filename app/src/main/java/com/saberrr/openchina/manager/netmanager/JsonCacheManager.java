package com.saberrr.openchina.manager.netmanager;

import android.text.TextUtils;

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
            return GsonTools.changeGsonToBean(json, bean);
        }
    }

    public <T> T getDataBean(String url, Map<String, String> headMap, Class<T> bean) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetManager.getInstance().getJson(url, headMap);
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
            return GsonTools.changeGsonToBean(json, bean);
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
            //            return GsonUtil.fromJsonArray(json, t);
            return GsonTools.changeGsonToList(json, t);
        }
    }

    public <T> List<T> getDataList(String url, Map<String, String> headMap, Class<T> t) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String json = NetManager.getInstance().getJson(url, headMap);
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
            //            return GsonUtil.fromJsonArray(json, t);
            return GsonTools.changeGsonToList(json, t);
        }
    }

    public String getXML(String url, Map<String, String> headMap) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String xml = NetManager.getInstance().getJson(url, headMap);
        if (TextUtils.isEmpty(xml)) {
            xml = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, xml);
        }
        if (TextUtils.isEmpty(xml)) {
            return null;
        } else {
            return xml;
        }
    }

    public String getXML(String url, RequestBody body) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String xml = NetManager.getInstance().getPostJson(url, body);
        if (TextUtils.isEmpty(xml)) {
            xml = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, xml);
        }
        if (TextUtils.isEmpty(xml)) {
            return null;
        } else {
            return xml;
        }
    }

    public String getXML(String url) {
        /**
         * 1. 去网络请求最新数据
         * 2. 如果没有数据去请求缓存数据
         */
        String xml = NetManager.getInstance().getJson(url);
        if (TextUtils.isEmpty(xml)) {
            xml = CacheManager.getInstance().getCacheData(url);
        } else {
            CacheManager.getInstance().saveCacheData(url, xml);
        }
        if (TextUtils.isEmpty(xml)) {
            return null;
        } else {
            return xml;
        }
    }
}

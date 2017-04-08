package com.saberrr.openchina.manager.netmanager;

import android.text.TextUtils;

import com.saberrr.openchina.utils.GsonTools;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by Saberrr on 2017-03-25.
 */

public class JsonCacheManager2 {
    private static JsonCacheManager2 sJsonCacheManager = new JsonCacheManager2();

    private JsonCacheManager2() {
    }

    public static synchronized JsonCacheManager2 getInstance() {
        return sJsonCacheManager;
    }

    public <T> T getDataBean(String url, Class<T> bean) {
        String json = NetManager.getInstance().getJson(url);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToBean(json, bean);
        }
    }

    public <T> T getDataBean(String url, Map<String, String> headMap, Class<T> bean) {
        String json = NetManager.getInstance().getJson(url, headMap);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToBean(json, bean);
        }
    }


    public <T> T getDataBean(String url, RequestBody body, Class<T> bean) {
        String json = NetManager.getInstance().getPostJson(url, body);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToBean(json, bean);
        }
    }

    public <T> List<T> getDataList(String url, Class<T> t) {
        String json = NetManager.getInstance().getJson(url);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToList(json, t);
        }
    }

    public <T> List<T> getDataList(String url, Map<String, String> headMap, Class<T> t) {
        String json = NetManager.getInstance().getJson(url, headMap);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToList(json, t);
        }
    }

    public <T> List<T> getDataList(String url, RequestBody body, Class<T> t) {
        String json = NetManager.getInstance().getPostJson(url, body);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToList(json, t);
        }
    }

    public String getXML(String url, Map<String, String> headMap) {
        String xml = NetManager.getInstance().getJson(url, headMap);
        if (TextUtils.isEmpty(xml)) {
            return null;
        } else {
            return xml;
        }
    }

    public String getXML(String url, Map<String, String> headMap, RequestBody body) {
        String xml = NetManager.getInstance().getJson(url, headMap, body);
        if (TextUtils.isEmpty(xml)) {
            return null;
        } else {
            return xml;
        }
    }

    public String getXML(String url, RequestBody body) {
        String xml = NetManager.getInstance().getPostJson(url, body);
        if (TextUtils.isEmpty(xml)) {
            return null;
        } else {
            return xml;
        }
    }
    public String getXML(String url) {
        String xml = NetManager.getInstance().getJson(url);
        if (TextUtils.isEmpty(xml)) {
            return null;
        } else {
            return xml;
        }
    }

    public <T> T getDataBean(String url, Map<String, String> headMap, RequestBody body, Class<T> bean) {
        String json = NetManager.getInstance().getJson(url, headMap, body);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToBean(json, bean);
        }
    }

    public <T> List<T> getDataList(String url, Map<String, String> headMap, RequestBody body, Class<T> t) {
        String json = NetManager.getInstance().getJson(url, headMap, body);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToList(json, t);
        }
    }

    public <T> T getCacheDataBean(String url, Class<T> bean) {
        String json = CacheManager.getInstance().getCacheData(url);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToBean(json, bean);
        }
    }

    public <T> List<T> getCacheDataList(String url, Class<T> t) {
        String json = CacheManager.getInstance().getCacheData(url);
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return GsonTools.changeGsonToList(json, t);
        }
    }

}

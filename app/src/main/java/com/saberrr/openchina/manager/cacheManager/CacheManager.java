package com.saberrr.openchina.manager.cacheManager;

import android.os.Environment;

import com.saberrr.gooleplay_my1.gloab.AppApplication;
import com.saberrr.gooleplay_my1.utils.MD5Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Saberrr on 2017-03-25.
 */

public class CacheManager {
    private static CacheManager sCacheManager = new CacheManager();
    private final String mPath;

    private CacheManager() {
        mPath = Environment.getExternalStorageDirectory().getPath() + File.separator + AppApplication.appContext.getPackageName();
        File pathDir = new File(mPath);
        if (!pathDir.exists()) {
            pathDir.mkdirs();
        }
    }

    public static synchronized CacheManager getInstance() {
        return sCacheManager;
    }

    public String getCacheData(String url) {
        StringBuffer sb = new StringBuffer();
        try {
            File file = new File(mPath, getFileName(url));
            FileInputStream is = new FileInputStream(file);
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len));
            }
            is.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void saveCacheData(String url, String json) {
        File file = new File(mPath, getFileName(url));
        try {
            FileOutputStream os = new FileOutputStream(file);
            os.write(json.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileName(String url) {
        return MD5Utils.getMd5(url);
    }
}

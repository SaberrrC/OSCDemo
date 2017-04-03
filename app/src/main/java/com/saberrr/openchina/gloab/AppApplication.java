package com.saberrr.openchina.gloab;

import android.app.Application;
import android.content.Context;

/**
 * 清单文件注册
 * android:name=".utils.ApplicationUtils"
 */

public class AppApplication extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

}

package com.saberrr.openchina.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 2017 on 2017/2/20.
 */

public class SpUtil {

    static SharedPreferences sp;

    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        boolean aBoolean = sp.getBoolean(key, defValue);

        return aBoolean;
    }

    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        String aBoolean = sp.getString(key, defValue);
        return aBoolean;
    }

}

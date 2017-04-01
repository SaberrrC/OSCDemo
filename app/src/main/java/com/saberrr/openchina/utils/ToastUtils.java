package com.saberrr.openchina.utils;

import android.widget.Toast;

import com.saberrr.gooleplay_my1.gloab.AppApplication;

public class ToastUtils {

    private static Toast toast;

    public static void showToast(final String text) {
        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(AppApplication.appContext, text, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(text);
                }
                toast.show();
            }
        });
    }
}

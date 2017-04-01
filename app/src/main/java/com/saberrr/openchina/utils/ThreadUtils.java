package com.saberrr.openchina.utils;


import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Saberrr on 2017-03-13.
 */

public class ThreadUtils {

    private static Handler  sHandler        = new Handler(Looper.getMainLooper());//获取主线程的Looper
    private static Executor sExecutor       = new ThreadPoolExecutor(
            1,
            5,
            20,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            ToastUtils.showToast("操作太频繁，请稍后再试");
        }
    });

    public static void runSub(Runnable runnable) {
        sExecutor.execute(runnable);
    }

    public static void runMain(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void runMainDelayed(Runnable runnable, long delay) {
        sHandler.postDelayed(runnable, delay);
    }
}

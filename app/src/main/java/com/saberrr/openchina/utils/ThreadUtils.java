package com.saberrr.openchina.utils;


import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Saberrr on 2017-03-13.
 */

public class ThreadUtils {

    private static Handler  sHandler        = new Handler(Looper.getMainLooper());//获取主线程的Looper
    private static Executor sExecutorCached = Executors.newCachedThreadPool();
    private static Executor sExecutor       = new ThreadPoolExecutor(
            3,//1  核心线程
            15,//3  最大线程
            5,
            TimeUnit.MINUTES,
            new SynchronousQueue<Runnable>(),//2  队列
            new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            ToastUtils.showToast("操作太频繁，请稍后再试");
        }
    });

    private static Executor sExecutorMore       = new ThreadPoolExecutor(
            5,
            9,
            5,
            TimeUnit.MINUTES,
            new SynchronousQueue<Runnable>(),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    ToastUtils.showToast("操作太频繁，请稍后再试");
                }
            });

    public static void runSub(Runnable runnable) {
        sExecutorCached.execute(runnable);
    }

    public static void runBigSub(Runnable runnable) {
        sExecutorMore.execute(runnable);
    }

    public static void runMain(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void runMainDelayed(Runnable runnable, long delay) {
        sHandler.postDelayed(runnable, delay);
    }
}

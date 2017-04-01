package com.saberrr.openchina.utils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.saberrr.gooleplay_my1.R;

/**
 * Created by Saberrr on 2017-03-29.
 */

public class ImageUtils {
    private static DisplayImageOptions options;
    private static DisplayImageOptions roundOptions;

    public static void setImage(String url, ImageView imageView) {
        if (options == null) {
            options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher) //显示图片加载中
                    .showImageForEmptyUri(R.mipmap.ic_launcher) //空的图片
                    .showImageOnFail(R.mipmap.ic_launcher) //错误的图片
                    .cacheInMemory(true) //内存缓存要不要
                    .cacheOnDisk(true) //sd卡缓存要不要
                    .considerExifParams(true)//会识别图片的方向信息
                    .displayer(new SimpleBitmapDisplayer()).build();//显示的效果
            //		.displayer(new RoundedBitmapDisplayer(36)).build();//显示圆图
        }
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    public static void setRoundImage(String url, ImageView imageView) {
        if (roundOptions == null) {
            roundOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher) //显示图片加载中
                    .showImageForEmptyUri(R.mipmap.ic_launcher) //空的图片
                    .showImageOnFail(R.mipmap.ic_launcher) //错误的图片
                    .cacheInMemory(true) //内存缓存要不要
                    .cacheOnDisk(true) //sd卡缓存要不要
                    .considerExifParams(true)//会识别图片的方向信息
                    //  .displayer(new FadeInBitmapDisplayer(500))//显示的效果
                    .displayer(new RoundedBitmapDisplayer(36))//显示圆图
                    .build();//显示圆图
        }
        ImageLoader.getInstance().displayImage(url, imageView, roundOptions);
    }
}

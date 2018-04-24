package com.saberrr.openchina.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.contact.Fiels;
import com.saberrr.openchina.faces.DisplayRules;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.ui.activity.ShowActivity;

import java.io.File;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private final static float SCALE    = 0.7f;
    private final static int   DURATION = 500;

    //得到字符串数组信息
    public static String[] getStringArray(int resId) {
        //getResources:R
        return getResources().getStringArray(resId);
    }

    //得到资源管理的类
    public static Resources getResources() {
        return AppApplication.appContext.getResources();
    }

    //在屏幕适配时候使用,让代码中使用dip属性w
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    //得到颜色
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 拿到一个随机颜色
     */
    public static int createRandomColor() {
        Random random = new Random();
        return random.nextInt(180);
    }

    // 创建一个随机的颜色
    public static int randomColor() {
        Random random = new Random();
        int red = random.nextInt(180);
        int blue = random.nextInt(180);
        int green = random.nextInt(180);
        return Color.rgb(red, green, blue);
        // return 0;
    }

    public static void startFragment(Class clss, Bundle bundle) {
        Intent intent = new Intent(AppApplication.appContext, ShowActivity.class);
        intent.putExtra(Fiels.DtailActivity.BUNDLE, bundle);
        intent.putExtra(Fiels.DtailActivity.CLASSNAME, clss);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppApplication.appContext.startActivity(intent);
    }

    public static void setiTemAnim(View view) {
        view.setScaleX(SCALE);
        view.setScaleY(SCALE);
        ViewCompat.animate(view).scaleX(1.0f).scaleY(1.0f).setDuration(DURATION).setInterpolator(new AnticipateOvershootInterpolator()).start();
    }

    public static int getCPUCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static void startInstallActivity(File openFile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(openFile), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppApplication.appContext.startActivity(intent);
    }

    public static int getScreenWith() {
        WindowManager wm = getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    private static WindowManager getWindowManager() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) AppApplication.appContext.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        return (WindowManager) AppApplication.appContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public static int getScreenHeight() {
        WindowManager wm = getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    public static void loadImage(String path, ImageView imageView) {
        Glide.with(AppApplication.appContext).load(path).into(imageView);
    }

    public static void loadImage(int id, ImageView imageView) {
        Glide.with(AppApplication.appContext).load(id).into(imageView);
    }

    public static View getLayoutView(int id) {
        return LayoutInflater.from(AppApplication.appContext).inflate(id, null, false);
    }

    //获取版本名
    public static String getVersionName(String packageName) {
        String versionName = "未知";
        try {
            //参数一：要查询的应用包信息对应包名,参数二：标记，想获取什么信息，就设置什么标记，（0：基本信息）
            PackageInfo packageInfo = AppApplication.appContext.getPackageManager().getPackageInfo(packageName, 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    //获取版本号
    public static int getVersionCode(String packageName) {
        int versionCode = 1;
        try {
            PackageInfo packageInfo = AppApplication.appContext.getPackageManager().getPackageInfo(packageName, 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    //显示表情
    public static Spannable displayEmoji(Resources res, CharSequence s) {
        return displayEmoji(res, new SpannableString(s));
    }

    public static Spannable displayEmoji(Resources res, Spannable spannable) {
        String str = spannable.toString();

        if (!str.contains(":") && !str.contains("[")) {
            return spannable;
        }

        Pattern pattern = Pattern.compile("(\\[[^\\[\\]:\\s\\n]+\\])|(:[^:\\[\\]\\s\\n]+:)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String emojiStr = matcher.group();
            if (TextUtils.isEmpty(emojiStr))
                continue;
            int resId = getEmojiResId(emojiStr);
            if (resId <= 0)
                continue;
            Drawable drawable = res.getDrawable(resId);
            if (drawable == null)
                continue;
            drawable.setBounds(0, 0, 20, 20);
            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
            spannable.setSpan(span, matcher.start(), matcher.end(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return spannable;
    }

    /**
     * 获取name对应的资源
     */
    public static int getEmojiResId(String name) {
        Integer res = DisplayRules.getMapAll().get(name);
        if (res != null) {
            return res;
        } else {
            return -1;
        }
    }


    public static SpannableString parseActiveAction(int objecttype, int objectcatalog, String objecttitle) {
        String title = "";
        int start = 0;
        int end = 0;
        if (objecttype == 32 && objectcatalog == 0) {
            title = "加入了开源中国";
        } else if (objecttype == 1 && objectcatalog == 0) {
            title = "添加了开源项目 " + objecttitle;
        } else if (objecttype == 2 && objectcatalog == 1) {
            title = "在讨论区提问：" + objecttitle;
        } else if (objecttype == 2 && objectcatalog == 2) {
            title = "发表了新话题：" + objecttitle;
        } else if (objecttype == 3 && objectcatalog == 0) {
            title = "发表了博客 " + objecttitle;
        } else if (objecttype == 4 && objectcatalog == 0) {
            title = "发表一篇新闻 " + objecttitle;
        } else if (objecttype == 5 && objectcatalog == 0) {
            title = "分享了一段代码 " + objecttitle;
        } else if (objecttype == 6 && objectcatalog == 0) {
            title = "发布了一个职位：" + objecttitle;
        } else if (objecttype == 16 && objectcatalog == 0) {
            title = "在新闻 " + objecttitle + " 发表评论";
        } else if (objecttype == 17 && objectcatalog == 1) {
            title = "回答了问题：" + objecttitle;
        } else if (objecttype == 17 && objectcatalog == 2) {
            title = "回复了话题：" + objecttitle;
        } else if (objecttype == 17 && objectcatalog == 3) {
            title = "在 " + objecttitle + " 对回帖发表评论";
        } else if (objecttype == 18 && objectcatalog == 0) {
            title = "在博客 " + objecttitle + " 发表评论";
        } else if (objecttype == 19 && objectcatalog == 0) {
            title = "在代码 " + objecttitle + " 发表评论";
        } else if (objecttype == 20 && objectcatalog == 0) {
            title = "在职位 " + objecttitle + " 发表评论";
        } else if (objecttype == 101 && objectcatalog == 0) {
            title = "回复了动态：" + objecttitle;
        } else if (objecttype == 100) {
            title = "更新了动态";
        }
        SpannableString sp = new SpannableString(title);
        // 设置标题字体大小、高亮
        if (!StringUtils.isEmpty(objecttitle)) {
            start = title.indexOf(objecttitle);
            if (objecttitle.length() > 0 && start > 0) {
                end = start + objecttitle.length();
                sp.setSpan(new AbsoluteSizeSpan(14, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                sp.setSpan(new ForegroundColorSpan(Color.parseColor("#0e5986")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return sp;
    }

    public static SpannableStringBuilder parseActiveReply(String name, String body) {
        Spanned span = Html.fromHtml(body.trim());
        SpannableStringBuilder sp = new SpannableStringBuilder(name + "：");
        sp.append(span);
        // 设置用户名字体加粗、高亮
        // sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
        // name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#008000")), 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sp;
    }

    /**
     * 设置沉浸式状态栏
     *
     * @param activity
     */

    public static void showColoredBars(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 这个属性4.4算是全透明（有的机子是过渡形式的透明），5.0就是半透明了 我的模拟器、真机都是半透明，
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4 全透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 5.0 全透明实现
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);// calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
    }
}

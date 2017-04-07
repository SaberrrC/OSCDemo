package com.saberrr.openchina.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.ui.activity.MainActivity;
import com.saberrr.openchina.ui.fragment.MyFragment;

/**
 * Created by 2017 on 2017/4/7.
 */

public class ScanMapDialog extends Dialog {


    private Window window;
    private final ImageView mImageView;

    public ScanMapDialog(Context context ) {
        super(context,R.style.ScanMapDialog);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_scanmap, null);
        mImageView = (ImageView) view.findViewById(R.id.im_map);
        setContentView(view);
        window = getWindow(); //得到对话框
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.dialogWindowAnim);


//        windowDeploy(100, 100);


    }

    public void setImaget(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }


    public void windowDeploy(int x, int y){
        window = getWindow(); //得到对话框
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
        //window.setBackgroundDrawableResource(R.color.vifrification); //设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        //根据x，y坐标设置窗口需要显示的位置
        wl.x = x; //x小于0左移，大于0右移
        wl.y = y; //y小于0上移，大于0下移
        wl.width=window.getWindowManager().getDefaultDisplay().getWidth();//设置dialog的款
//        wl.width= AppApplication.getApplication().getWallpaperDesiredMinimumWidth();
        wl.height=350;//设置dialog的高
//            wl.alpha = 0.6f; //设置透明度
//            wl.gravity = Gravity.BOTTOM; //设置重力
        window.setAttributes(wl);
    }



}

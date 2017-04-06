package com.saberrr.openchina.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 丁银晨 on 2017/4/5.
 */

public class WapRecyclerView extends RecyclerView {
    public WapRecyclerView(Context context) {
        this(context,null);
    }

    public WapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }
}

package com.saberrr.openchina.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by 丁银晨 on 2017/4/6.
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener1 != null) {
            mOnScrollChangedListener1.onScrollChanged(this, l, t, oldl, oldt);
        }
        View view = this.getChildAt(0);
        if (this.getHeight() + this.getScrollY() == view.getHeight()) {
            _calCount++;
            if (_calCount == 1) {
                if (_listener != null) {
                    _listener.srollToBottom();
                }
            } else {
                _calCount = 0;
            }
        }

    }

    public interface OnScrollChangedListener1 {
        void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    private OnScrollChangedListener1 mOnScrollChangedListener1;

    public void setOnScrollChangedListener1(OnScrollChangedListener1 onScrollChangedListener1) {
        mOnScrollChangedListener1 = onScrollChangedListener1;
    }

    private OnScrollBottomListener _listener;
    private int                    _calCount;

    public interface OnScrollBottomListener {
        void srollToBottom();
    }

    public void registerOnScrollViewScrollToBottom(OnScrollBottomListener l) {
        _listener = l;
    }

    public void unRegisterOnScrollViewScrollToBottom() {
        _listener = null;
    }
}

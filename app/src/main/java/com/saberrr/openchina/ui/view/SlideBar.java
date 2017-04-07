package com.saberrr.openchina.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.IContactAdapter;
import com.saberrr.openchina.utils.DensityUtil;
import com.saberrr.openchina.utils.StringUtils;

import java.util.List;

/**
 * Created by Saberrr on 2017-03-14.
 */

public class SlideBar extends View {

    private static final String[] SECTIONS = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int                 midX;
    private int                 avgY;
    private Paint               mPaint;
    private TextView            mTvFloat;
    private RecyclerView        mRecyclerView;
    private IContactAdapter     mAdapter;
    private List<String>        mData;
    private ViewGroup           mParent;
    private LinearLayoutManager mManager;

    public SlideBar(Context context) {
        this(context, null);
    }

    public SlideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public SlideBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs);
    }

    public SlideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置字体的属性
        mPaint.setColor(Color.parseColor("#9c9c9c"));
        mPaint.setTextSize(DensityUtil.dip2px(10));
        mPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        midX = measuredWidth / 2;
        avgY = measuredHeight / SECTIONS.length;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < SECTIONS.length; i++) {
            canvas.drawText(SECTIONS[i], midX, avgY * (1 + i), mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //设置背景
                setBackgroundResource(R.drawable.slide_bar_bg);
                //显示FloatView
                //定位RecyclerView
                show(event.getY());
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                mTvFloat.setVisibility(GONE);
                break;
            default:
                break;
        }
        return true;
    }

    private void show(float y) {
        if (mParent == null) {
            mParent = (ViewGroup) getParent();
        }
        if (mTvFloat == null) {
            mTvFloat = (TextView) mParent.findViewById(R.id.tv_float);
        }
        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) mParent.findViewById(R.id.recyclerView);
        }
        if (mAdapter == null) {
            mAdapter = (IContactAdapter) mRecyclerView.getAdapter();
        }
        mData = mAdapter.getData();
        if (mData == null || mData.size() == 0) {
            mRecyclerView.smoothScrollToPosition(0);
        }

        int index = (int) (y / avgY);
        //控制边界
        if (index < 0) {
            index = 0;
        } else if (index > SECTIONS.length - 1) {
            index = SECTIONS.length - 1;
        }
        mTvFloat.setText(SECTIONS[index]);
        mTvFloat.setVisibility(VISIBLE);
        for (int i = 0; i < mData.size(); i++) {
            if (StringUtils.getFirst(mData.get(i)).equalsIgnoreCase(SECTIONS[index])) {
                if (mManager == null) {
                    mManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                }
                mManager.scrollToPositionWithOffset(i, 0);
                break;
            }
        }
    }
}

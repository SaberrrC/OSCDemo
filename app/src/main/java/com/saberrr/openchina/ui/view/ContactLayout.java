package com.saberrr.openchina.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saberrr.openchina.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saberrr on 2017-03-14.
 */

public class ContactLayout extends RelativeLayout {

    @BindView(R.id.recyclerView)
    RecyclerView       mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_float)
    TextView           mTvFloat;
    private Handler mHandler = new Handler();

    public ContactLayout(Context context) {
        this(context, null);
    }

    public ContactLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public ContactLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public ContactLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //填充布局
        View view = LayoutInflater.from(context).inflate(R.layout.contact_layout, this, true);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    public void stopRefresh(final boolean idRefersh) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(idRefersh);
            }
        }, 500);
    }
}

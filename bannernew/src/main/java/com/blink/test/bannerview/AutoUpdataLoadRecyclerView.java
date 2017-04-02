package com.blink.test.bannerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Saberrr on 2017-04-02.
 */

public class AutoUpdataLoadRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout         mSwipeRefreshLayout;
    private RecyclerView               mRecyclerView;
    private FinalRecycleAdapter        mFinalRecycleAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public AutoUpdataLoadRecyclerView(Context context) {
        this(context, null);
    }

    public AutoUpdataLoadRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoUpdataLoadRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public AutoUpdataLoadRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs);
    }

    private void init() {
        mSwipeRefreshLayout = new SwipeRefreshLayout(getContext());
        mRecyclerView = new RecyclerView(getContext());
        mSwipeRefreshLayout.addView(mRecyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public void setAdapterAndLayoutManager(FinalRecycleAdapter finalRecycleAdapter, RecyclerView.LayoutManager layoutManager) {
        this.mFinalRecycleAdapter = finalRecycleAdapter;
        this.mLayoutManager = layoutManager;
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mFinalRecycleAdapter);
    }

    @Override
    public void onRefresh() {
        mOnRefreshListener.onRefreshing();
    }

    public interface OnRefreshListener {
        void onRefreshing();
    }

    private OnRefreshListener mOnRefreshListener;

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        mOnRefreshListener = onRefreshListener;
    }
}

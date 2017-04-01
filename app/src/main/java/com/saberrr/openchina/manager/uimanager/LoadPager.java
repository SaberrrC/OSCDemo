package com.saberrr.openchina.manager.uimanager;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.saberrr.openchina.R;
import com.saberrr.openchina.utils.ThreadUtils;

import java.util.List;


/**
 * Created by Saberrr on 2017-03-25.
 */

public abstract class LoadPager extends FrameLayout {

    public static final int                DELAY               = 0;//loading加载时间
    private            SwipeRefreshLayout mSwipeRefreshLayout = null;
    private View mErrorView;
    private View mLoadingView;
    private View mSuccessView;
    private LOADSTATE mLOADSTATE = LOADSTATE.LOADING;
    private boolean mAddRefresh;

    private enum LOADSTATE {
        LOADING, ERROR, SUCCESS
    }

    public LoadPager(Context context) {
        this(context, null);
    }

    public LoadPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadPager(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public LoadPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    private void init() {
        mAddRefresh = addRefresh();
        if (mAddRefresh) {
            //需要刷新
            mSwipeRefreshLayout = new SwipeRefreshLayout(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mSwipeRefreshLayout.setLayoutParams(layoutParams);
            addView(mSwipeRefreshLayout);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //根据联网获取的状态 自动切换
                    showViewDely(DELAY);
                }
            });
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    showViewDely(0);
                }
            });
            addAllView();
        } else {
            //不需要
            mSwipeRefreshLayout = null;
            addAllView();
        }
        //切换
        changView();
        //根据联网获取的状态 自动切换
        showViewDely(DELAY);
    }

    private void addAllView() {
        if (mErrorView == null) {
            mErrorView = LayoutInflater.from(getContext()).inflate(R.layout.page_error, null, false);
            Button bt = (Button) mErrorView.findViewById(R.id.btn_reload);
            bt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLOADSTATE = LOADSTATE.LOADING;
                    //切换
                    changView();
                    //根据联网获取的状态 自动切换
                    showViewDely(DELAY);
                }
            });
        }
        if (mLoadingView == null) {
            mLoadingView = LayoutInflater.from(getContext()).inflate(R.layout.page_loading, null, false);
        }
        if (mSuccessView == null) {
            mSuccessView = createSuccessView();
            if (mSuccessView == null) {
                throw new RuntimeException("必须传入布局");
            }
        }
        if (mSwipeRefreshLayout == null) {
            addView(mSuccessView);
        } else {
            mSwipeRefreshLayout.addView(mSuccessView);
            addView(mSwipeRefreshLayout);
        }
        addView(mErrorView);
        addView(mLoadingView);
    }

    protected abstract boolean addRefresh();

    public void showViewDely(final int time) {
        ThreadUtils.runSub(new Runnable() {
            @Override
            public void run() {
                Object object = getNetData();
                mLOADSTATE = checkObject(object);
                ThreadUtils.runMainDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changView();
                    }
                }, time);
            }
        });
    }

    private LOADSTATE checkObject(Object object) {
        if (object == null) {
            return LOADSTATE.ERROR;
        } else {
            if (object instanceof List) {
                List data = (List) object;
                if (data.size() > 0) {
                    return LOADSTATE.SUCCESS;
                } else {
                    return LOADSTATE.ERROR;
                }
            } else {
                //不是集合
                return LOADSTATE.SUCCESS;
            }
        }
    }

    public void changView() {
        mErrorView.setVisibility(View.INVISIBLE);
        mLoadingView.setVisibility(View.INVISIBLE);
        mSuccessView.setVisibility(View.INVISIBLE);
        switch (mLOADSTATE) {
            case LOADING:
                mLoadingView.setVisibility(View.VISIBLE);
                break;
            case ERROR:
                mErrorView.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:
                if (mSwipeRefreshLayout == null) {
                    mSuccessView.setVisibility(View.VISIBLE);
                } else {
                    mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public abstract View createSuccessView();

    public abstract Object getNetData();

}

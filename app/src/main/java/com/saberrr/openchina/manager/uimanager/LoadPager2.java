package com.saberrr.openchina.manager.uimanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.saberrr.openchina.R;
import com.saberrr.openchina.utils.ThreadUtils;

import java.util.List;

public abstract class LoadPager2 extends FrameLayout {

    public static final int DELAY = 0;//loading加载时间
    private View mErrorView;
    private View mLoadingView;
    private View mSuccessView;
    private LOADSTATE mLOADSTATE = LOADSTATE.LOADING;

    private enum LOADSTATE {
        LOADING, ERROR, SUCCESS
    }

    public LoadPager2(Context context) {
        this(context, null);
    }

    public LoadPager2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadPager2(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public LoadPager2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    private void init() {
        addAllView();
        //        changView();
        //根据联网获取的状态 自动切换
        //        showViewDelay(DELAY);
    }

    public interface OnClickListener {
        void onClick(View view);
    }

    private OnClickListener mOnClickListener;

    public void setOnErrorButtonClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    private void addAllView() {
        if (mErrorView == null) {
            mErrorView = LayoutInflater.from(getContext()).inflate(R.layout.page_error, null, false);
            Button bt = (Button) mErrorView.findViewById(R.id.btn_reload);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLOADSTATE = LOADSTATE.LOADING;
                    //切换
                    changView();
                    //根据联网获取的状态 自动切换
                    showViewDelay(DELAY);
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
        addView(mErrorView);
        addView(mLoadingView);
        addView(mSuccessView);
        changView();
        showViewDelay(DELAY);
    }

    public void showViewDelay(final int time) {
        ThreadUtils.runSub(new Runnable() {
            @Override
            public void run() {
                Object object = getDataOnSubThread();
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
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mSuccessView.setVisibility(View.GONE);
        switch (mLOADSTATE) {
            case LOADING:
                mLoadingView.setVisibility(View.VISIBLE);
                break;
            case ERROR:
                mErrorView.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:
                mSuccessView.setVisibility(View.VISIBLE);
                break;
        }
    }

    public abstract View createSuccessView();

    public abstract Object getDataOnSubThread();
}

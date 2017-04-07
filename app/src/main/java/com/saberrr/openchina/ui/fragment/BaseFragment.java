package com.saberrr.openchina.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saberrr.openchina.manager.uimanager.LoadPager;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.utils.ToastUtils;

import java.util.List;

/**
 * Created by Saberrr on 2017-03-25.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment {

    public  LoadPager    mLoadingPager;
    private boolean      mNeedRefresh;
    private ShowActivity mParentActivity;
    public static Fragment mCurrentFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Activity activity = getActivity();
        if (activity instanceof ShowActivity) {
            mParentActivity = (ShowActivity) activity;
        }
        if (mLoadingPager != null) {
            return mLoadingPager;
        }
        mLoadingPager = new LoadPager(getContext()) {
            @Override
            protected boolean addRefresh() {
                mNeedRefresh = needRefresh();
                return mNeedRefresh;
            }

            @Override
            public View createSuccessView() {
                View view = createView();
                return view;
            }

            @Override
            public Object getNetData() {
                try {
                    return getData();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };

        return mLoadingPager;
    }

    /**
     * 获取toolbar右边textview
     *
     * @return
     */
    public TextView getRightTextView() {
        return mParentActivity.getRightTextView();
    }

    /**
     * 左边返回箭头点击事件
     *
     * @param onBackIconClickListener
     */
    public void setOnBackIconClickListener(ShowActivity.OnBackIconClickListener onBackIconClickListener) {
        mParentActivity.setOnBackIconClickListener(onBackIconClickListener);
    }

    /**
     * 设置顶部Toolbar点击事件
     *
     * @param onClickListener 传入点击监听 处理对应操作
     */
    public void setToolbarIconOnClickListener(ShowActivity.OnClickListener onClickListener) {
        mParentActivity.setToolbarIconOnClickListener(onClickListener);
    }

    /**
     * Toolbar顶部SearchView搜索监听
     *
     * @param onQueryTextListener 传入监听器 处理形影操作
     */
    public void setOnQueryTextListener(ShowActivity.OnQueryTextListener onQueryTextListener) {
        mParentActivity.setOnQueryTextListener(onQueryTextListener);
    }

    /**
     * 设置右边toolbar文字
     *
     * @param text
     */
    public void setvRightToolbarText(String text) {
        mParentActivity.setvRightToolbarText(text);
    }

    /**
     * 根据传入的布局id生成view
     *
     * @param layoutId
     * @return
     */
    public View creatViewFromId(int layoutId) {
        return LayoutInflater.from(getContext()).inflate(layoutId, null, false);
    }

    public void setCommentCount(String count) {
        mParentActivity.setCommentCount(count);
    }

    public void setonOptionsItemSelected(ShowActivity.onOptionsItemSelected onOptionsItemSelected) {
        mParentActivity.setonOptionsItemSelected(onOptionsItemSelected);
    }

    public void setHintKeyboardexception() {
        mParentActivity.setHintKeyboardexception();
    }

    /**
     * 点击其他地方隐藏键盘
     *
     * @param touchHintKeyboard 默认FALSE  ->  点击其它地方不隐藏键盘
     */
    public void setHintKeyboard(boolean touchHintKeyboard) {
        mParentActivity.setHintKeyboard(touchHintKeyboard);
    }

    /**
     * 是否需要下拉刷新
     *
     * @return
     */
    protected abstract boolean needRefresh();

    public abstract View createView();

    //获取数据调用该方法发
    public abstract Object getData();

    public <T> List<T> chechRefresh(List<T> showItems, List<T> datas) {
        if (showItems.size() <= 0) {
            if (datas == null || datas.size() <= 0) {
                return showItems;
            } else {
                showItems.addAll(datas);
            }
        } else {
            if (datas == null || datas.size() <= 0) {
                ToastUtils.showToast("网络未知错误");
            } else {
                showItems.addAll(datas);
            }
        }
        return showItems;
    }

    /*public void starDetailActivity(Class Clas, Bundle bundle) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Fiels.DtailActivity.BUNDLE, bundle);
        intent.putExtra(Fiels.DtailActivity.CLASSNAME, Clas);
        startActivity(intent);
    }

    public void starDetailActivityWithTitle(Class Clas, Bundle bundle, String title) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Fiels.DtailActivity.BUNDLE, bundle);
        intent.putExtra(Fiels.DtailActivity.CLASSNAME, Clas);
        intent.putExtra(Fiels.DtailActivity.TITLE, title);
        startActivity(intent);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        mParentActivity = null;
    }
    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }
}

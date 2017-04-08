package com.saberrr.openchina.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saberrr.openchina.manager.uimanager.LoadPager2;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.utils.ThreadUtils;

import java.util.List;

/**
 * Created by Saberrr on 2017-03-25.
 */

public abstract class BaseFragment2 extends android.support.v4.app.Fragment {

    public  LoadPager2    mLoadingPager;
    private ShowActivity mParentActivity;

    {
        Activity activity = getActivity();
        if (activity instanceof ShowActivity) {
            mParentActivity = (ShowActivity) activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLoadingPager != null) {
            return mLoadingPager;
        }
        mLoadingPager = new LoadPager2(getContext()) {

            @Override
            public View createSuccessView() {
                return createView();
            }

            @Override
            public Object getDataOnSubThread() {
                return BaseFragment2.this.getDataOnSubThread();
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
     * 获取toolbar左边textview
     *
     * @return
     */
    public TextView getLeftTextView() {
        return mParentActivity.getLeftTextView();
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

    Object cacheObject = null;
    Object newObject   = null;

    //获取数据调用该方法发
    private Object getDataOnSubThread() {
        cacheObject = getCacheDataOnSub();
        if (cacheObject == null) {//没缓存
            //联网获取
            newObject = getNewDataOnSub();
            if (newObject == null) {
                return null;//返回错误界面
            } else {
                if (newObject instanceof List) {
                    List data = (List) newObject;
                    if (data.size() > 0) {
                        ThreadUtils.runMain(new Runnable() {
                            @Override
                            public void run() {
                                showViewOnMain(cacheObject, newObject);
                            }
                        });
                        return newObject;
                    } else {
                        ThreadUtils.runMain(new Runnable() {
                            @Override
                            public void run() {
                                showViewOnMain(cacheObject, newObject);
                            }
                        });
                        return newObject;
                    }
                } else {
                    //不是集合
                    ThreadUtils.runMain(new Runnable() {
                        @Override
                        public void run() {
                            showViewOnMain(cacheObject, newObject);
                        }
                    });
                    return newObject;
                }
            }
        } else {//有缓存
            if (cacheObject instanceof List) {
                List data = (List) cacheObject;
                if (data.size() > 0) {
                    ThreadUtils.runMain(new Runnable() {
                        @Override
                        public void run() {
                            showViewOnMain(cacheObject, newObject);
                        }
                    });
                } else {
                    //不是集合
                    ThreadUtils.runMain(new Runnable() {
                        @Override
                        public void run() {
                            showViewOnMain(cacheObject, newObject);
                        }
                    });
                }
            } else {
                //不是集合
                ThreadUtils.runMain(new Runnable() {
                    @Override
                    public void run() {
                        showViewOnMain(cacheObject, newObject);
                    }
                });
            }
            //联网获取
            newObject = getNewDataOnSub();
            if (newObject == null) {
                return cacheObject;//返回缓存进行判断
            } else {
                if (newObject instanceof List) {
                    List data = (List) newObject;
                    if (data.size() > 0) {
                        ThreadUtils.runMain(new Runnable() {
                            @Override
                            public void run() {
                                showViewOnMain(cacheObject, newObject);
                            }
                        });
                        return cacheObject;
                    } else {
                        ThreadUtils.runMain(new Runnable() {
                            @Override
                            public void run() {
                                showViewOnMain(cacheObject, newObject);
                            }
                        });
                        return cacheObject;
                    }
                } else {
                    //不是集合
                    ThreadUtils.runMain(new Runnable() {
                        @Override
                        public void run() {
                            showViewOnMain(cacheObject, newObject);
                        }
                    });
                    return cacheObject;
                }
            }
        }
    }

    public abstract View createView();

    public abstract Object getCacheDataOnSub();

    public abstract Object getNewDataOnSub();

    public abstract void showViewOnMain(Object cacheObject, Object newObject);

    @Override
    public void onDestroy() {
        super.onDestroy();
        mParentActivity = null;
    }
}

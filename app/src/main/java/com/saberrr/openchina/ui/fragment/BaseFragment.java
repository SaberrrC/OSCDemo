package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saberrr.openchina.manager.uimanager.LoadPager;
import com.saberrr.openchina.utils.ToastUtils;

import java.util.List;

/**
 * Created by Saberrr on 2017-03-25.
 */

public abstract class BaseFragment extends Fragment {

    private LoadPager mLoadingPager;
    private boolean needRefresh = false;
    private boolean mNeedRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
                return getData();
            }
        };
        return mLoadingPager;
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
}

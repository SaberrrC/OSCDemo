package com.saberrr.openchina.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.saberrr.openchina.R;

/**
 * Created by liuqi on 2017/4/1.
 */

public class FindFragment extends BaseFragment {
    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_find, null);
        init();
        return view;
    }

    private void init() {

    }

    @Override
    public Object getData() {
        return null;
    }
}

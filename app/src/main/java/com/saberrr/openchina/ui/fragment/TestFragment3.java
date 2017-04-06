package com.saberrr.openchina.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.saberrr.openchina.R;

/**
 * Created by liuqi on 2017/4/6.
 */

public class TestFragment3 extends BaseFragment {
    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.test1, null);
        return view;
    }

    @Override
    public Object getData() {
        return "";
    }
}

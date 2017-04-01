package com.saberrr.openchina.ui.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public  class InformationFragment extends BaseFragment {
    @Override
    protected boolean needRefresh() {
        return true;
    }

    @Override
    public View createView() {
        TextView textView = new TextView(getContext());
        textView.setText("我是资讯");
        return textView;
    }

    @Override
    public Object getData() {
        return "";
    }
}

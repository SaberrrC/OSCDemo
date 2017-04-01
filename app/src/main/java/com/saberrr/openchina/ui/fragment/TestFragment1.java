package com.saberrr.openchina.ui.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Saberrr on 2017-04-01.
 */

public class TestFragment1 extends BaseFragment {

    @Override
    protected boolean needRefresh() {
        return true;
    }

    @Override
    public View createView() {
        TextView textView = new TextView(getContext());
        textView.setText("test");
        return textView;
    }

    @Override
    public Object getData() {
        return "";
    }
}

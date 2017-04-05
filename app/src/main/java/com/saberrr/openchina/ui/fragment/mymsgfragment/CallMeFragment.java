package com.saberrr.openchina.ui.fragment.mymsgfragment;

import android.view.View;
import android.widget.TextView;

import com.saberrr.openchina.ui.fragment.BaseFragment;

/**
 * Created by 2017 on 2017/4/3.
 */

public class CallMeFragment extends BaseFragment {
    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        TextView textView = new TextView(getContext());
        textView.setText("@我");
        return textView;
    }

    @Override
    public Object getData() {
        return "";
    }
}

package com.saberrr.openchina.ui.fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.saberrr.openchina.ui.activity.ShowActivity;

/**
 * Created by Saberrr on 2017-04-01.
 */

public class TestFragment2 extends BaseFragment {
    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        TextView textView = new TextView(getContext());
        textView.setText(SystemClock.currentThreadTimeMillis() + "");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowActivity.startFragment(TestFragment1.class, null);
            }
        });
        return textView;
    }

    @Override
    public Object getData() {
        return "";
    }
}

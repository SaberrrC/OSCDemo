package com.saberrr.openchina.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.Utils;

/**
 * Created by Saberrr on 2017-04-06.
 */

public class AtFragment extends BaseFragment {
    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = Utils.getLayoutView(R.layout.fragment_at);
        TextView rightTextView = getRightTextView();
        rightTextView.setText("确定");
        setToolbarIconOnClickListener(new ShowActivity.OnClickListener() {
            @Override
            public void onClick() {
                ToastUtils.showToast("666");
            }
        });

        return view;
    }

    @Override
    public Object getData() {
        return "";
    }
}

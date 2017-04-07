package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.utils.ThreadUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuqi on 2017/4/6.
 */

public class RockSoftwareDetailFragment extends BaseFragment {
    @BindView(R.id.wv_lock)
    WebView mWvLock;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.rockdetail_layout, null);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {

    }

    @Override
    public Object getData() {
        Bundle bundle = getArguments();
        final String url = bundle.getString("url");
        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mWvLock.loadUrl(url);
            }
        });

        return "";
    }


}

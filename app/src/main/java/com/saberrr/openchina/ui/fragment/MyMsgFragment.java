package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saberrr.openchina.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 2017 on 2017/4/2.
 */

public class MyMsgFragment extends BaseFragment {
    private static String Title[] = {"@我", "评论", "私信", "粉丝", "赞过我"};
    @BindView(R.id.tl_title)
    TabLayout mTlTitle;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_msg, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {



    }

    @Override
    public Object getData() {
        return "";
    }


}

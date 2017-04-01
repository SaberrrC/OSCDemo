package com.saberrr.openchina.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.saberrr.openchina.R;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.utils.ToastUtils;

/**
 * Created by 2017 on 2017/4/1.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private View mMy_msg;
    private View mRl_top_offline;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frag_my, null);
        mMy_msg = view.findViewById(R.id.rl_my_msg);
        mRl_top_offline = view.findViewById(R.id.rl_offline);
        mRl_top_offline.setOnClickListener(this);
        mMy_msg.setOnClickListener(this);
        return view;
    }

    @Override
    public Object getData() {
        return "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_offline:
                ToastUtils.showToast("点击了登录");

                ShowActivity.startFragment(LoginFragment.class , null);
                break;

            case R.id.rl_my_msg:
                ToastUtils.showToast("点击了我的消息");
                break;
        }
    }
}

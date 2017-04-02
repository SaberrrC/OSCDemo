package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 2017 on 2017/4/2.
 */

public class MyInfoFragment extends BaseFragment {
    @BindView(R.id.iv_avtar_offline)
    ImageView mIvAvtarOffline;
    @BindView(R.id.tv_username_offline)
    TextView mTvUsernameOffline;
    @BindView(R.id.rl_offline)
    RelativeLayout mRlOffline;
    @BindView(R.id.tv_join_time)
    TextView mTvJoinTime;
    @BindView(R.id.tv_locaton)
    TextView mTvLocaton;
    @BindView(R.id.tv_platform)
    TextView mTvPlatform;
    @BindView(R.id.tv_strength)
    TextView mTvStrength;
    @BindView(R.id.btn_logout)
    Button mBtnLogout;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_info, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        Bundle bundle = getArguments();
//        bundle.getSerializable()

        // TODO: 2017/4/2 设置数据



    }

    @Override
    public Object getData() {
        return "";
    }



    @OnClick(R.id.btn_logout)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
                ToastUtils.showToast("登出");
                // TODO: 2017/4/2 清除本地登录相关数据

                getActivity().finish();
                break;
        }

    }
}

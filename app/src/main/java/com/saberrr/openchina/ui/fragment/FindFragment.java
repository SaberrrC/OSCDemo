package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.ui.activity.ShowActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuqi on 2017/4/1.
 */

public class FindFragment extends BaseFragment {
    @BindView(R.id.openSoftware)
    TextView mOpenSoftware;
    @BindView(R.id.findUser)
    TextView mFindUser;
    @BindView(R.id.scan)
    TextView mScan;
    @BindView(R.id.shake)
    TextView mShake;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_find, null);
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


    @OnClick({R.id.openSoftware, R.id.findUser, R.id.scan, R.id.shake})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openSoftware:
                Bundle bundle = new Bundle();
                bundle.putString("title", mOpenSoftware.getText().toString());
                ShowActivity.startFragment(OpenSoftwareFragment.class,bundle );
                break;
            case R.id.findUser:

                break;
            case R.id.scan:

                break;
            case R.id.shake:

                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}

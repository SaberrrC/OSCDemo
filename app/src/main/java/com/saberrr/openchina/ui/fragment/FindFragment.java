package com.saberrr.openchina.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.scan.zxing.android.CaptureActivity;
import com.saberrr.openchina.ui.activity.ShakeActivity;
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
                ShowActivity.startFragmentWithTitle(OpenSoftwareFragment.class,bundle,mOpenSoftware.getText().toString());
                break;
            case R.id.findUser:
                Bundle bundle1 = new Bundle();
                ShowActivity.startFragmentWithTitle(FindUserFragment.class,bundle1,mFindUser.getText().toString());

                break;
            case R.id.scan:
                Intent scan = new Intent(getActivity(), CaptureActivity.class);
                startActivity(scan);


                break;
            case R.id.shake:
                Intent intent = new Intent(getContext(), ShakeActivity.class);
                intent.putExtra("title",mShake.getText().toString());
                startActivity(intent);
                break;
        }
    }

}

package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saberrr.openchina.R;
import com.saberrr.openchina.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuqi on 2017/4/1.
 */

public class FindFragment extends BaseFragment {
    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_find, null);
        ButterKnife.bind(this,view);
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
                Toast.makeText(getContext(),"软件",Toast.LENGTH_SHORT).show();
                break;
            case R.id.findUser:

                break;
            case R.id.scan:

                break;
            case R.id.shake:

                break;
        }
    }
}

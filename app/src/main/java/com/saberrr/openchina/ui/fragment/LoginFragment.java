package com.saberrr.openchina.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.saberrr.openchina.R;

/**
 * Created by 2017 on 2017/4/1.
 */

public class LoginFragment extends BaseFragment {
    @Override
    protected boolean needRefresh() {


        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_login, null);
        return view;
    }

    @Override
    public Object getData() {
        return "";
    }
}

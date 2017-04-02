package com.saberrr.openchina.ui.fragment;

import android.view.View;

import com.saberrr.openchina.presenter.JumpPresenter;
import com.saberrr.openchina.presenter.JumpPresenterImpl;

import java.util.List;

/**
 * Created by Saberrr on 2017-04-02.
 */

public class JumpFragment extends BaseFragment implements JumpView {

    private JumpPresenter mJumpPresenter;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        mJumpPresenter = new JumpPresenterImpl(this);
        return null;
    }

    @Override
    public Object getData() {
        Object o = mJumpPresenter.init();
        return o;
    }

    //获取数据后的回调
    @Override
    public void onInit(List list) {

    }
}

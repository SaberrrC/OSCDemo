package com.saberrr.openchina.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.saberrr.openchina.R;
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
        TextView textView = new TextView(getContext());
        textView.setText("54f5asd4f564a6sdf45asdfas4dfas4df56af5");
        return textView;
    }
    @Override
    public Object getData() {
        creatViewFromId(R.layout.fragment_jumponejump);

        return "";
    }

    //获取数据后的回调
    @Override
    public void onInit(List list) {

    }
}

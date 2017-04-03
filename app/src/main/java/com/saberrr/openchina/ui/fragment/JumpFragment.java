package com.saberrr.openchina.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.presenter.JumpPresenter;
import com.saberrr.openchina.presenter.JumpPresenterImpl;
import com.saberrr.openchina.ui.view.FlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Saberrr on 2017-04-02.
 */

public class JumpFragment extends BaseFragment implements JumpView {

    @BindView(R.id.tv_content)
    TextView   mTvContent;
    @BindView(R.id.fl_images)
    FlowLayout mFlImages;
    @BindView(R.id.iv_pic)
    ImageView  mIvPic;
    @BindView(R.id.iv_at)
    ImageView  mIvAt;
    @BindView(R.id.iv_topic)
    ImageView  mIvTopic;
    @BindView(R.id.iv_face)
    ImageView  mIvFace;
    private JumpPresenter mJumpPresenter;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = creatViewFromId(R.layout.fragment_jumponejump);
        ButterKnife.bind(this, view);
        mJumpPresenter = new JumpPresenterImpl(this);
        return view;
    }

    @Override
    public Object getData() {

        return "";
    }

    //获取数据后的回调
    @Override
    public void onInit(List list) {

    }


    @OnClick({R.id.tv_content, R.id.fl_images, R.id.iv_pic, R.id.iv_at, R.id.iv_topic, R.id.iv_face})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_content:
                break;
            case R.id.fl_images:
                break;
            case R.id.iv_pic:
                break;
            case R.id.iv_at:
                break;
            case R.id.iv_topic:
                break;
            case R.id.iv_face:
                break;
        }
    }
}

package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.os.DropBoxManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.event.LoginBeanEvent;
import com.saberrr.openchina.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 2017 on 2017/4/1.
 */

public class LoginFragment extends BaseFragment {
    @BindView(R.id.et_username)
    EditText mEtUername;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.iv_weibo)
    ImageView mIvWeibo;
    private String mUsername;
    private String mPwd;

    @Override
    protected boolean needRefresh() {


        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public Object getData() {
        return "";
    }



    @OnClick({R.id.btn_login, R.id.iv_qq, R.id.iv_wechat, R.id.iv_weibo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mUsername = mEtUername.getText().toString().trim();
                mPwd = mEtPwd.getText().toString().trim();
                if (checkUsernameAndPwd(mUsername, mPwd)) {
                    // TODO: 2017/4/2 登录
                    ToastUtils.showToast("登录");
                    //将登录事件发送给我的界面
                    EventBus.getDefault().postSticky(new LoginBeanEvent());

                    getActivity().finish();


                } else {
                    return;
                }


                break;
            case R.id.iv_qq:
                ToastUtils.showToast("暂不支持第三方登录");
                break;
            case R.id.iv_wechat:
                ToastUtils.showToast("暂不支持第三方登录");
                break;
            case R.id.iv_weibo:
                ToastUtils.showToast("暂不支持第三方登录");
                break;
        }
    }

    private boolean checkUsernameAndPwd(String username, String pwd) {
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showToast("请填写用户名");
            mEtUername.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showToast("请填写密码");
            mEtPwd.requestFocus();
            return false;
        }
        return true;

    }
}

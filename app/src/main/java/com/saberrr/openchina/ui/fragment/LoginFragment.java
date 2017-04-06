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
import com.saberrr.openchina.bean.LoginBean;
import com.saberrr.openchina.event.LoginBeanEvent;
import com.saberrr.openchina.manager.netmanager.RetrofitUtil;
import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.XmlUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        mUsername = SpUtil.getString(getContext(), Constant.USERNAME, "");
        mEtUername.setText(mUsername);
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
                    HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
                    httpServiceApi.preLogin("1" , mUsername ,mPwd).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                try {

                                    String msg = response.body().string();
                                    LoginBean loginBean = XmlUtils.toBean(LoginBean.class, msg.getBytes());
                                    if (loginBean.getResult().getErrorCode().equals("1")) {
                                        LoginBeanEvent event = new LoginBeanEvent();
                                        event.mLoginBean = loginBean;

                                        Headers headers = response.headers();
//                                        for (int i = 0; i < headers.size(); i++) {
//                                            String name = headers.name(i);
//                                            System.out.println(name);
//                                        }
                                        String s = headers.get("Set-Cookie");
//                                        System.out.println(s);
                                        String[] cookies = s.split(";");
                                        for (int i = 0; i < cookies.length; i++) {
                                            if (cookies[i].startsWith("oscid=")) {
                                                SpUtil.saveString(getContext(), Constant.COOKIE, cookies[i]);
                                            }
                                        }

                                        ToastUtils.showToast("登录成功！");
//                                        System.out.println(split1[1]);

                                        SpUtil.saveString(getContext(), Constant.USERID, loginBean.getUser().getUid());
                                        SpUtil.saveString(getContext(), Constant.PWD, mPwd);
                                        SpUtil.saveString(getContext(), Constant.USERNAME, mUsername);

                                        EventBus.getDefault().postSticky(event);
                                        getActivity().finish();
                                    } else {

                                        ToastUtils.showToast(loginBean.getResult().getErrorMessage());
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    ToastUtils.showToast("用户名或密码错误！");
                                    e.printStackTrace();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            ToastUtils.showToast("登录失败");

                        }
                    });

                    //将登录事件发送给我的界面
//                    EventBus.getDefault().postSticky(new LoginBeanEvent());
//
//                    getActivity().finish();


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

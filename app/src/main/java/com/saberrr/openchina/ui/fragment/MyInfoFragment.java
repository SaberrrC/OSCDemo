package com.saberrr.openchina.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.LoginBean;
import com.saberrr.openchina.bean.UserInfo;
import com.saberrr.openchina.event.LoginBeanEvent;
import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.XmlUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    private String mUid;

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
        LoginBean userinfo = (LoginBean) bundle.getSerializable("USERINFO");
        mUid = userinfo.getUser().getUid();

        System.out.println(mUid);

        // TODO: 2017/4/2 设置数据

        mTvUsernameOffline.setText(userinfo.getUser().getName());
        Glide.with(getContext()).load(userinfo.getUser().getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvAvtarOffline) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                mIvAvtarOffline.setImageDrawable(circularBitmapDrawable);
            }
        });

        mTvLocaton.setText(userinfo.getUser().getLocation());
        mTvJoinTime.setText("2017-3-1");

        mTvPlatform.setText("<无>");
        mTvStrength.setText("<无>");




        //借口获取，参数有问
        /*HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
        httpServiceApi.getUserInfo(mUid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String string = response.body().string();
                        System.out.println(string);
                        UserInfo userInfo = XmlUtils.toBean(UserInfo.class, string.getBytes());

                    mTvUsernameOffline.setText(userInfo.getUser().getName());
                    mTvLocaton.setText(userInfo.getUser().getFrom());
                    mTvJoinTime.setText(userInfo.getUser().getJointime());

                    mTvPlatform.setText(userInfo.getUser().getDevplatform());
                    mTvStrength.setText(userInfo.getUser().getExpertise());
                    Glide.with(getContext()).load(userInfo.getUser().getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvAvtarOffline) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            mIvAvtarOffline.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ToastUtils.showToast("网络出错！");
            }
        });*/



    }

    @Override
    public Object getData() {

        //同步获取
//        HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
//        try {
//            Response<ResponseBody> response = httpServiceApi.getUserInfo(mUid).execute();
//
//            String string = response.body().string();
//
//            System.out.println(string);
           /* UserInfo userInfo = XmlUtils.toBean(UserInfo.class,  string.getBytes());
                    mTvUsernameOffline.setText(userInfo.getUser().getName());
                    mTvLocaton.setText(userInfo.getUser().getFrom());
                    mTvJoinTime.setText(userInfo.getUser().getJointime());

                    mTvPlatform.setText(userInfo.getUser().getDevplatform());
                    mTvStrength.setText(userInfo.getUser().getExpertise());
                    Glide.with(getContext()).load(userInfo.getUser().getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvAvtarOffline) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            mIvAvtarOffline.setImageDrawable(circularBitmapDrawable);
                        }
                    });
            return "";*/
//
//        } catch (IOException e) {
//            ToastUtils.showToast("获取数据失败！");
//            e.printStackTrace();
//        }




        return "";
    }



    @OnClick(R.id.btn_logout)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
                ToastUtils.showToast("登出");
                // TODO: 2017/4/2 清除本地登录相关数据
                LoginBeanEvent.cookie = null;
                EventBus.getDefault().postSticky(new LoginBeanEvent());

                getActivity().finish();
                break;
        }

    }
}

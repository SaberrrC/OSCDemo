package com.saberrr.openchina.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.LoginBean;
import com.saberrr.openchina.bean.UserInfo;
import com.saberrr.openchina.event.LoginBeanEvent;
import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.fragment.mymsgfragment.FansFragment;
import com.saberrr.openchina.ui.view.ScanMapDialog;
import com.saberrr.openchina.ui.view.SolarSystemView;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.XmlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 2017 on 2017/4/1.
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.iv_avtar_offline)
    ImageView mIvAvtarOffline;
    @BindView(R.id.rl_offline)
    RelativeLayout mRlOffline;
    @BindView(R.id.iv_avtar_online)
    ImageView mIvAvtarOnline;
    @BindView(R.id.iv_gender)
    ImageView mIvGender;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.iv_mark_online)
    ImageView mIvMarkOnline;
    @BindView(R.id.tv_score_count)
    TextView mTvScoreCount;
    @BindView(R.id.tv_score)
    TextView mTvScore;
    @BindView(R.id.ll_score)
    LinearLayout mLlScore;
    @BindView(R.id.tv_favorite_count)
    TextView mTvFavoriteCount;
    @BindView(R.id.tv_favorite)
    TextView mTvFavorite;
    @BindView(R.id.ll_favorite)
    LinearLayout mLlFavorite;
    @BindView(R.id.tv_followers_count)
    TextView mTvFollowersCount;
    @BindView(R.id.tv_followers)
    TextView mTvFollowers;
    @BindView(R.id.ll_followers)
    LinearLayout mLlFollowers;
    @BindView(R.id.tv_fans_count)
    TextView mTvFansCount;
    @BindView(R.id.tv_fans)
    TextView mTvFans;
    @BindView(R.id.ll_fans)
    LinearLayout mLlFans;
    @BindView(R.id.rl_online)
    LinearLayout mRlOnline;
    @BindView(R.id.fl_top)
    RelativeLayout mFlTop;
    @BindView(R.id.iv_my_msg)
    ImageView mIvMyMsg;
    @BindView(R.id.tv_my_msg)
    TextView mTvMyMsg;
    @BindView(R.id.rl_my_msg)
    RelativeLayout mRlMyMsg;
    @BindView(R.id.iv_my_blog)
    ImageView mIvMyBlog;
    @BindView(R.id.tv_my_blog)
    TextView mTvMyBlog;
    @BindView(R.id.rl_my_blog)
    RelativeLayout mRlMyBlog;
    @BindView(R.id.iv_my_team)
    ImageView mIvMyTeam;
    @BindView(R.id.tv_my_team)
    TextView mTvMyTeam;
    @BindView(R.id.rl_my_team)
    RelativeLayout mRlMyTeam;
    @BindView(R.id.iv_my_event)
    ImageView mIvMyEvent;
    @BindView(R.id.tv_my_event)
    TextView mTvMyEvent;
    @BindView(R.id.rl_my_event)
    RelativeLayout mRlMyEvent;
    @BindView(R.id.iv_my_setting)
    ImageView mIvMySetting;
    @BindView(R.id.tv_my_setting)
    TextView mTvMySetting;
    @BindView(R.id.rl_my_setting)
    RelativeLayout mRlMySetting;
    @BindView(R.id.view_solar_system)
    SolarSystemView mViewSolarSystem;
    @BindView(R.id.rl)
    RelativeLayout mRl;

    private boolean isOnline;
    private int[] genderRid = {0, R.mipmap.ic_male, R.mipmap.ic_female};
    private String mUserid;
    private LoginBean mLoginBean;


    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frag_my, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onLoginEvent(LoginBeanEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        new Thread(new Runnable() {
            @Override
            public void run() {
                checkLogin();


            }
        }).start();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public Object getData() {

        checkLogin();


        return "";
    }

    //子线程
    private void checkLogin() {
        String cookie = SpUtil.getString(getContext(), Constant.COOKIE, "");
        mUserid = SpUtil.getString(getContext(), Constant.USERID, "");
        System.out.println(cookie);


        if (checkUseridAndCookie(cookie, mUserid)) {
            HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
            try {
                Response<ResponseBody> response = httpServiceApi.getUserInfo(cookie, mUserid).execute();
                String result = response.body().string();
                System.out.println(result);
                UserInfo userInfo = XmlUtils.toBean(UserInfo.class, result.getBytes());

                setOnlineView(userInfo);

            } catch (Exception e) {
                e.printStackTrace();
                setOffLineView();
            }


        } else {
            setOffLineView();

        }

    }

    private void setOffLineView() {//设置默认的未登录状态
        // TODO: 2017/4/5
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mRlOffline.setVisibility(View.VISIBLE);
                mRlOnline.setVisibility(View.GONE);
                Glide.with(getContext()).load(R.mipmap.widget_dface).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvAvtarOffline) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        mIvAvtarOffline.setImageDrawable(circularBitmapDrawable);
                    }

                });
                isOnline = false;
            }
        });


    }

    private boolean checkUseridAndCookie(String cookie, String userid) {

        if (TextUtils.isEmpty(userid) || TextUtils.isEmpty(cookie)) {
            return false;
        }
        return true;

    }

    private void initSolar() {
        mRl.post(new Runnable() {
            @Override
            public void run() {

                int width = mRl.getWidth();
                int height = mRl.getHeight() + 50;

                float px = width >> 1;
                float py = height;
                int radius = (width >> 1) - 20;

                SolarSystemView.Planet planet1 = new SolarSystemView.Planet();
                planet1.setClockwise(false);
                planet1.setAngleRate(0.03F);
                planet1.setOriginAngle(270);
                planet1.setRadius(radius / 3);

                SolarSystemView.Planet planet2 = new SolarSystemView.Planet();
                planet2.setClockwise(true);
                planet2.setAngleRate(0.04F);
                planet1.setOriginAngle(180);
                planet2.setRadius(radius / 3 * 2);

                SolarSystemView.Planet planet3 = new SolarSystemView.Planet();
                planet3.setClockwise(false);
                planet1.setOriginAngle(270);
                planet3.setAngleRate(0.05F);
                planet3.setRadius(radius);

                mViewSolarSystem.addPlanets(planet1);
                mViewSolarSystem.addPlanets(planet2);
                mViewSolarSystem.addPlanets(planet3);
                mViewSolarSystem.setPivotPoint(px, py);
            }
        });
    }


    @OnClick({R.id.rl_offline, R.id.iv_avtar_online, R.id.iv_mark_online, R.id.ll_score, R.id.ll_favorite, R.id.ll_followers, R.id.ll_fans, R.id.rl_my_msg, R.id.rl_my_blog, R.id.rl_my_team, R.id.rl_my_event, R.id.rl_my_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_offline:
                ShowActivity.startFragmentWithTitle(LoginFragment.class, null, "登录");
                break;
            case R.id.iv_avtar_online:
                Bundle bundle = new Bundle();
                bundle.putSerializable("USERINFO", mLoginBean);
                ShowActivity.startFragmentWithTitle(MyInfoFragment.class, bundle, "我的资料");

                break;
            case R.id.iv_mark_online:
                ToastUtils.showToast("二维码");
                //二维码
                Bitmap prbitmap = generateBitmap("http://my.oschina.net/u/" +mUserid, 200, 200);
                Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                Bitmap bitmap = addLogo(prbitmap, logoBitmap);
                ScanMapDialog scanMapDialog = new ScanMapDialog(getContext());
                scanMapDialog.setImaget(bitmap);
                scanMapDialog.show();

                break;
            case R.id.ll_score:
                ToastUtils.showToast("积分");
                break;
            case R.id.ll_favorite:
                ToastUtils.showToast("收藏");
                break;
            case R.id.ll_followers:
                ToastUtils.showToast("关注");
                Bundle bu = new Bundle();
                ShowActivity.startFragmentWithTitle(FansFragment.class, bu, "关注");
                break;
            case R.id.ll_fans:
                ShowActivity.startFragmentWithTitle(FansFragment.class, null, "粉丝");
                ToastUtils.showToast("粉丝");

                break;
            case R.id.rl_my_msg:
                if (isOnline) {
                    ShowActivity.startFragmentWithTitle(MyMsgFragment.class, null, "消息中心");
                } else {
                    ShowActivity.startFragmentWithTitle(LoginFragment.class, null, "登录");
                }
                ToastUtils.showToast("我的消息");

                break;
            case R.id.rl_my_blog:
                if (isOnline) {
//                    ShowActivity.startFragmentWithTitle(MyMsgFragment.class, null, "消息中心");
                } else {
                    ShowActivity.startFragmentWithTitle(LoginFragment.class, null, "登录");
                }

                ToastUtils.showToast("我的微博");
                break;
            case R.id.rl_my_team:
                if (isOnline) {
//                    ShowActivity.startFragmentWithTitle(MyMsgFragment.class, null, "消息中心");
                } else {
                    ShowActivity.startFragmentWithTitle(LoginFragment.class, null, "登录");
                }
                ToastUtils.showToast("我的团队");
                break;
            case R.id.rl_my_event:
                if (isOnline) {
//                    ShowActivity.startFragmentWithTitle(MyMsgFragment.class, null, "消息中心");
                } else {
                    ShowActivity.startFragmentWithTitle(LoginFragment.class, null, "登录");
                }
                ToastUtils.showToast("我的活动");
                break;
            case R.id.rl_my_setting:
                ToastUtils.showToast("设置");
                break;
        }
    }

    public void setOnlineView(final UserInfo userInfo) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {


                try {
                    mRlOffline.setVisibility(View.GONE);
                    mRlOnline.setVisibility(View.VISIBLE);

                    mTvUsername.setText(userInfo.getUser().getName());
                    mTvScoreCount.setText(userInfo.getUser().getScore());
                    mTvFavoriteCount.setText(userInfo.getUser().getFavoritecount());
                    mTvFollowersCount.setText(userInfo.getUser().getFollowers());
                    mTvFansCount.setText(userInfo.getUser().getFans());

                    if (userInfo.getUser().getGender().equals("0")) {
                        mIvGender.setVisibility(View.GONE);

                    } else {
                        mIvGender.setVisibility(View.VISIBLE);
                        mIvGender.setImageResource(genderRid[Integer.parseInt(userInfo.getUser().getGender())]);
                    }

                    System.out.println(userInfo.getUser().getPortrait());
                    Glide.with(getContext()).load(userInfo.getUser().getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvAvtarOffline) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            mIvAvtarOnline.setImageDrawable(circularBitmapDrawable);
                        }
                    });

                    isOnline = true;
                    initSolar();
                } catch (Exception e) {
                    setOffLineView();
                }
            }
        });


    }

    private Bitmap addLogo(Bitmap qrBitmap, Bitmap logoBitmap) {
        int qrBitmapWidth = qrBitmap.getWidth();
        int qrBitmapHeight = qrBitmap.getHeight();
        int logoBitmapWidth = logoBitmap.getWidth();
        int logoBitmapHeight = logoBitmap.getHeight();
        Bitmap blankBitmap = Bitmap.createBitmap(qrBitmapWidth, qrBitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(blankBitmap);
        canvas.drawBitmap(qrBitmap, 0, 0, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        float scaleSize = 1.0f;
        while ((logoBitmapWidth / scaleSize) > (qrBitmapWidth / 5) || (logoBitmapHeight / scaleSize) > (qrBitmapHeight / 5)) {
            scaleSize *= 2;
        }
        float sx = 1.0f / scaleSize;
        canvas.scale(sx, sx, qrBitmapWidth / 2, qrBitmapHeight / 2);
        canvas.drawBitmap(logoBitmap, (qrBitmapWidth - logoBitmapWidth) / 2, (qrBitmapHeight - logoBitmapHeight) / 2, null);
        canvas.restore();
        return blankBitmap;
    }

    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


}

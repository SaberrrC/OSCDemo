package com.saberrr.openchina.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.UserCenterBean;
import com.saberrr.openchina.bean.mymsgcenter.CommentBean;
import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.ui.view.SolarSystemView;
import com.saberrr.openchina.utils.AssimilateUtils;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.StringUtils;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.Utils;
import com.saberrr.openchina.utils.XmlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserCenterActivity extends AppCompatActivity implements FinalRecycleAdapter.OnViewAttachListener {

    @BindView(R.id.view_solar_system)
    SolarSystemView mViewSolarSystem;
    @BindView(R.id.iv_portrait)
    CircleImageView mIvPortrait;
    @BindView(R.id.iv_gender)
    ImageView mIvGender;
    @BindView(R.id.tv_nick)
    TextView mTvNick;
    @BindView(R.id.tv_summary)
    TextView mTvSummary;
    @BindView(R.id.tv_score)
    TextView mTvScore;
    @BindView(R.id.tv_count_follow)
    TextView mTvCountFollow;
    @BindView(R.id.tv_count_fans)
    TextView mTvCountFans;
    @BindView(R.id.iv_logo_portrait)
    CircleImageView mIvLogoPortrait;
    @BindView(R.id.tv_logo_nick)
    TextView mTvLogoNick;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.layout_appbar)
    AppBarLayout mLayoutAppbar;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    private String mUserid;
    private String mLocalId;

    private int[] genderRid = {0, R.mipmap.ic_male, R.mipmap.ic_female};

    private List<UserCenterBean.Active> mItemList = new ArrayList<>();
    private FinalRecycleAdapter mRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        Intent intent = getIntent();
        mUserid = intent.getStringExtra(Constant.USERID);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        mToolbar.setTitle("");
        mToolbar.setSubtitle("");
        mToolbar.setNavigationIcon(R.mipmap.btn_back_normal);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLayoutAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int mScrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mScrollRange == -1) {
                    mScrollRange = appBarLayout.getTotalScrollRange();
                }
                if (mScrollRange + verticalOffset == 0) {
                    mTvLogoNick.setVisibility(View.VISIBLE);
                    mIvLogoPortrait.setVisibility(View.VISIBLE);
                    isShow = true;
                } else if (isShow) {
                    mTvLogoNick.setVisibility(View.GONE);
                    mIvLogoPortrait.setVisibility(View.GONE);
                    isShow = false;
                }
            }
        });

        mIvPortrait.post(new Runnable() {
            @Override
            public void run() {
                int mStatusBarHeight = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Rect rectangle = new Rect();
                    Window window = getWindow();
                    window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
                    mStatusBarHeight = rectangle.top;
                }
                float x = mIvPortrait.getX();
                float y = mIvPortrait.getY();
                ViewGroup parent = (ViewGroup) mIvPortrait.getParent();
                float px = x + parent.getX() + mIvPortrait.getWidth() / 2;
                float py = y + parent.getY() + mIvPortrait.getHeight() / 2 + mStatusBarHeight;
                int radius = (int) (mViewSolarSystem.getHeight() - py + 50);

                SolarSystemView.Planet planet1 = new SolarSystemView.Planet();
                planet1.setClockwise(true);
                planet1.setAngleRate(0.015F);
                planet1.setRadius(radius / 4);

                SolarSystemView.Planet planet2 = new SolarSystemView.Planet();
                planet2.setClockwise(false);
                planet2.setAngleRate(0.02F);
                planet2.setRadius(radius / 4 * 2);

                SolarSystemView.Planet planet3 = new SolarSystemView.Planet();
                planet3.setClockwise(true);
                planet3.setAngleRate(0.01F);
                planet3.setRadius(radius / 4 * 3);

                SolarSystemView.Planet planet4 = new SolarSystemView.Planet();
                planet4.setClockwise(false);
                planet4.setAngleRate(0.02F);
                planet4.setRadius(radius);

                mViewSolarSystem.addPlanets(planet1);
                mViewSolarSystem.addPlanets(planet2);
                mViewSolarSystem.addPlanets(planet3);
                mViewSolarSystem.addPlanets(planet4);
                mViewSolarSystem.setPivotPoint(px, py);
            }
        });
        Map<Class, Integer> map = new HashMap<>();
        map.put(UserCenterBean.Active.class, R.layout.item_comment);
        mRecycleAdapter = new FinalRecycleAdapter(mItemList, map, this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mRecycleAdapter);
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(true);
            }
        });
        mSrl.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == mItemList.size() - 1 && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    initData(false);
                }
            }
        });

        initData(true);
    }


    private void initData(final boolean isRefresh) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
                try {
                    int pageIndex = 0;
                    if (!isRefresh) {
                        pageIndex =mItemList.size() / (Constant.PAGESIZE);
                    }


                    Response<ResponseBody> response = httpServiceApi.getUserCenter(pageIndex, "", "", Constant.PAGESIZE, mUserid).execute();
                    String result = response.body().string();
                    System.out.println(result);
                    final UserCenterBean userCenterBean = XmlUtils.toBean(UserCenterBean.class, result.getBytes());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //设置头像
                            if (TextUtils.isEmpty(userCenterBean.getUser().getPortrait())) {
                                Glide.with(UserCenterActivity.this).load(R.mipmap.widget_dface).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvPortrait) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable circularBitmapDrawable =
                                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                                        circularBitmapDrawable.setCircular(true);
                                        mIvPortrait.setImageDrawable(circularBitmapDrawable);
                                    }

                                });
                                Glide.with(UserCenterActivity.this).load(R.mipmap.widget_dface).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvLogoPortrait) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable circularBitmapDrawable =
                                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                                        circularBitmapDrawable.setCircular(true);
                                        mIvLogoPortrait.setImageDrawable(circularBitmapDrawable);
                                    }

                                });
                            } else {
                                Glide.with(UserCenterActivity.this).load(TextUtils.isEmpty(userCenterBean.getUser().getPortrait())).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvPortrait) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable circularBitmapDrawable =
                                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                                        circularBitmapDrawable.setCircular(true);
                                        mIvPortrait.setImageDrawable(circularBitmapDrawable);
                                    }

                                });
                                Glide.with(UserCenterActivity.this).load(TextUtils.isEmpty(userCenterBean.getUser().getPortrait())).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvLogoPortrait) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable circularBitmapDrawable =
                                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                                        circularBitmapDrawable.setCircular(true);
                                        mIvLogoPortrait.setImageDrawable(circularBitmapDrawable);
                                    }

                                });
                            }


                            //设置性别
                            if (userCenterBean.getUser().getGender().equals("男")) {
                                mIvGender.setImageResource(genderRid[1]);

                            } else if (userCenterBean.getUser().getGender().equals("女")) {
                                mIvGender.setImageResource(genderRid[2]);
                            } else {
                                mIvGender.setVisibility(View.VISIBLE);

                            }

                            //设置名字

                            mTvLogoNick.setText(userCenterBean.getUser().getName());
                            mTvNick.setText(userCenterBean.getUser().getName());

                            //设置积分
                            mTvScore.setText("积分 " + userCenterBean.getUser().getScore());
                            mTvCountFans.setText("关注 " + userCenterBean.getUser().getFollowers());
                            mTvCountFollow.setText("粉丝 " + userCenterBean.getUser().getFollowers());

                            final List<UserCenterBean.Active> activies = userCenterBean.getActives();
                            setData(isRefresh ,activies);


                        }
                    });


                } catch (Exception e) {

                }
            }
        }).start();


    }

    private void setData(boolean isRefresh, List<UserCenterBean.Active> activies) {
        if (isRefresh ) {
            mItemList.clear();
        }

        mItemList.addAll(activies);
        if (mItemList.size() == 0) {
//            mLyerror.setVisibility(View.VISIBLE);
//            mSrl.setVisibility(View.GONE);
//            mTvResult.setText("当前无数据");
            ToastUtils.showToast("当前无数据");

        } else {

//            mLyerror.setVisibility(View.GONE);
            mSrl.setVisibility(View.VISIBLE);
            mRecycleAdapter.notifyDataSetChanged();
            ToastUtils.showToast("加载数据完成");
        }
        mSrl.setRefreshing(false);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mLocalId = SpUtil.getString(this, Constant.USERID, "");
        if (mLocalId == mUserid) {
            return super.onCreateOptionsMenu(menu);
        } else {

            getMenuInflater().inflate(R.menu.menu_other_user, menu);//导入菜单布局
            if (menu instanceof MenuBuilder) {
                MenuBuilder menuBuilder = (MenuBuilder) menu;
                menuBuilder.setOptionalIconsVisible(true);  //设置菜单图标可见
            }

            return true;
        }
    }


    @OnClick({R.id.tv_count_follow, R.id.tv_count_fans})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_count_follow:


                break;
            case R.id.tv_count_fans:
                break;
        }
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, final int position, Object itemData) {
        View rootView = holder.getRootView();
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("点击了条目，跳转到动弹详情" + mItemList.get(position).getId());


            }
        });
        TextView username = (TextView) holder.getViewById(R.id.tv_username);
        username.setText(mItemList.get(position).getAuthor());


        final ImageView ivheader = (ImageView) holder.getViewById(R.id.iv_header);
        ivheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.showToast("点击了头像，跳转进账户中心" + mItemList.get(position).getAuthorid());
                Intent userCenterVeiw = new Intent(UserCenterActivity.this, UserCenterActivity.class);
                userCenterVeiw.putExtra(Constant.USERID, mItemList.get(position).getAuthorid());
                startActivity(userCenterVeiw);
            }
        });

        if (TextUtils.isEmpty(mItemList.get(position).getPortrait())) {
            Glide.with(UserCenterActivity.this).load(R.mipmap.widget_dface).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivheader) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivheader.setImageDrawable(circularBitmapDrawable);
                }

            });
        } else {
            Glide.with(UserCenterActivity.this).load(mItemList.get(position).getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivheader) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivheader.setImageDrawable(circularBitmapDrawable);
                }

            });
        }


        TextView event = (TextView) holder.getViewById(R.id.tv_event);
        event.setText(Utils.parseActiveAction(mItemList.get(position).getObjecttype(),mItemList.get(position).getObjectcatalog(),mItemList.get(position).getObjecttitle()));

        TextView content = (TextView) holder.getViewById(R.id.tv_content);
        String body = mItemList.get(position).getMessage();
        if (body == null) {
            content.setVisibility(View.GONE);
        } else {
            content.setVisibility(View.VISIBLE);
            /*Spanned span = Html.fromHtml(body.trim());
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(span);
            Spannable spannable = Utils.displayEmoji(getContext().getResources(), spannableStringBuilder);*/

           /* SpannableStringBuilder spannableStringBuilder = Utils.parseActiveReply("", body.trim());
            Spannable spannable = Utils.displayEmoji(getContext().getResources(),spannableStringBuilder);*/
            Spannable spannable = AssimilateUtils.highlightAtUser(UserCenterActivity.this, body.trim());
            Spannable spannable1 = Utils.displayEmoji(getResources(), spannable);

            content.setText(spannable1);
        }


        TextView myself = (TextView) holder.getViewById(R.id.tv_myself);


        UserCenterBean.Active.Objectreply objectreply = mItemList.get(position).getObjectreply();
        if (objectreply == null) {
            myself.setVisibility(View.GONE);
        } else {
            myself.setVisibility(View.VISIBLE);
            SpannableStringBuilder text = Utils.parseActiveReply(objectreply.getObjectname().trim(), objectreply.getObjectbody().trim());
            Spannable spannable = Utils.displayEmoji(getResources(), text);
            myself.setText(spannable);

        }


/*
        String myname = mItemList.get(position).getObjectreply().getObjectname();
        String string = myname + ":" + mItemList.get(position).getObjectreply().getObjectbody().trim();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, myname.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        myself.setText(spannableStringBuilder);*/

//        myself.setText(mItemList.get(position).getTweet().getAuthor() + ":" + mItemList.get(position).getTweet().getBody());


        TextView time = (TextView) holder.getViewById(R.id.tv_time);
        time.setText(StringUtils.friendly_time(mItemList.get(position).getPubDate()));

        TextView platform = (TextView) holder.getViewById(R.id.tv_platform);
        String plat = "";
        switch (mItemList.get(position).getAppclient()) {

            case "3":
                plat = "Android";
                break;
            case "4":
                plat = "iphone";
                break;

        }
        platform.setText(plat);



    }
}

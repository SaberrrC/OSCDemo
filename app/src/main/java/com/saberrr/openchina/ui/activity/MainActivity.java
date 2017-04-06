package com.saberrr.openchina.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.ui.fragment.ComprehensiveFragment;
import com.saberrr.openchina.ui.fragment.FindFragment;
import com.saberrr.openchina.ui.fragment.JumpFragment;
import com.saberrr.openchina.ui.fragment.LoginFragment;
import com.saberrr.openchina.ui.fragment.MoveFragment;
import com.saberrr.openchina.ui.fragment.MyFragment;
import com.saberrr.openchina.ui.fragment.TestFragment2;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.saberrr.openchina.utils.ToastUtils.showToast;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title_toolbar)
    TextView        mTvTitleToolbar;
    @BindView(R.id.iv_right_icon_toolbar)
    ImageView       mIvIconToolbar;
    @BindView(R.id.toolbar_main)
    Toolbar         mToolbarMain;
    @BindView(R.id.fl_content_main)
    FrameLayout     mFlContentMain;
    @BindView(R.id.tabcontent)
    FrameLayout     mTabcontent;
    @BindView(R.id.tabhost)
    FragmentTabHost mTabhost;
    @BindView(R.id.iv_add)
    ImageView       mIvAdd;
    private Class  mFragmentArray[] = {ComprehensiveFragment.class, MoveFragment.class, TestFragment2.class, FindFragment.class, MyFragment.class};
    private String mTextArray[]     = {"综合", "动弹", "", "发现", "我的"};
    private int    mImageArray[]    = {R.drawable.selector_all_bg, R.drawable.selector_dongtan_bg, R.drawable.selector_add_bg, R.drawable.selector_find_bg, R.drawable.selector_mine_bg};
    private long   laatTime         = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        mToolbarMain.setTitle(mTextArray[0]);
        setSupportActionBar(mToolbarMain);
        mIvIconToolbar.setVisibility(View.VISIBLE);
        mIvIconToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("搜索界面");
            }
        });
        mIvIconToolbar.setImageResource(R.mipmap.btn_search_normal);
    }

    private ImageView mImageview;
    private TextView  mTextview;

    private void initView() {
        for (int i = 0; i < 5; i++) {
            mTabhost.setup(this, getSupportFragmentManager(), R.id.fl_content_main);
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec("" + i);
            View view = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null, false);
            mImageview = (ImageView) view.findViewById(R.id.imageview);
            mTextview = (TextView) view.findViewById(R.id.textview);
            if (i == 2) {
                view.setVisibility(View.INVISIBLE);
                view.setEnabled(false);
            }
            mImageview.setImageResource(mImageArray[i]);
            mTextview.setText(mTextArray[i]);

            tabSpec.setIndicator(view);
            mTabhost.addTab(tabSpec, mFragmentArray[i], null);
        }
        mTabhost.getTabWidget().setDividerDrawable(null);
        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cookie = SpUtil.getString(MainActivity.this, Constant.COOKIE, "");
                if (TextUtils.isEmpty(cookie)) {
                    ShowActivity.startFragment(LoginFragment.class, null);
                } else {
                    ShowActivity.startFragmentWithTitle(JumpFragment.class, null, "弹一弹", ShowActivity.TITLE_SEND);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - laatTime > 2000) {
            ToastUtils.showToast("再次点击推出开源中国");
            laatTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}

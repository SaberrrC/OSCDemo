package com.saberrr.openchina.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.ui.fragment.FindFragment;
import com.saberrr.openchina.ui.fragment.InformationFragment;
import com.saberrr.openchina.ui.fragment.MoveFragment;
import com.saberrr.openchina.ui.fragment.JumpFragment;
import com.saberrr.openchina.ui.fragment.MyFragment;
import com.saberrr.openchina.ui.fragment.TestFragment2;
import com.saberrr.openchina.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title_toolbar)
    TextView        mTvTitleToolbar;
    @BindView(R.id.iv_icon_toolbar)
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
    private Class  mFragmentArray[] = {InformationFragment.class, TestFragment1.class, TestFragment2.class, FindFragment.class, MyFragment.class};
    private String mTextArray[]     = {"综合", "动弹", "", "发现", "我的"};
    private int    mImageArray[]    = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


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
                ToastUtils.showToast("搜索界面");
            }
        });
        mIvIconToolbar.setImageResource(R.mipmap.btn_search_normal);
        //mTvTitleToolbar.setVisibility(View.GONE);
    }

    private void initView() {
        mTabhost.setup(this, getSupportFragmentManager(), R.id.fl_content_main);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(mTextArray[i]).setIndicator(getTabItemView(i));

            // 将Tab按钮添加进Tab选项卡中
            mTabhost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.mipmap.ic_launcher);
            mTabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            final int finalI = i;
            mTabhost.getTabWidget().getChildTabViewAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI != 2) {
                        mToolbarMain.setTitle(mTextArray[finalI]);
                        mTabhost.setCurrentTab(finalI);
                    } else {
                        //弹一弹

                    }

                }
            });

        }
        mTabhost.getTabWidget().setDividerDrawable(null);
        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowActivity.startFragmentWithTitle(JumpFragment.class,null,"弹一弹",ShowActivity.TITLE_SEND);
            }
        });
    }


    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(final int index) {

        View view = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);
        if (index == 2) {
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            view.setEnabled(false);
        }
        return view;
    }
}

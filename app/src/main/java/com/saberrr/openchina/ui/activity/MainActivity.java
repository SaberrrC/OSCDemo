package com.saberrr.openchina.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.ui.fragment.ComprehensiveFragment;
import com.saberrr.openchina.ui.fragment.FindFragment;
import com.saberrr.openchina.ui.fragment.MyFragment;
import com.saberrr.openchina.ui.fragment.TestFragment1;
import com.saberrr.openchina.ui.fragment.TestFragment2;
import com.saberrr.openchina.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater  mLayoutInflater;

    private Class  mFragmentArray[] = {ComprehensiveFragment.class, TestFragment1.class, TestFragment2.class, FindFragment.class, MyFragment.class};
    private String mTextArray[]     = {"综合", "动弹", "", "发现", "我的"};
    private int    mImageArray[]    = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        initToolBar();
        initView();
    }

    private void findview() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mIvSearchToolbar = (ImageView) findViewById(R.id.iv_search_toolbar);
        mTvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    }

    private Toolbar   mToolbar;
    private ImageView mIvSearchToolbar;
    private TextView  mTvTitleToolbar;

    private void initToolBar() {
        mToolbar.setTitle(mTextArray[0]);
        setSupportActionBar(mToolbar);
        mIvSearchToolbar.setVisibility(View.VISIBLE);
        mIvSearchToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("搜索界面");
            }
        });
        //mTvTitleToolbar.setVisibility(View.GONE);
    }

    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);
        // 找到TabHost

        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content_main);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i]).setIndicator(getTabItemView(i));

            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.mipmap.ic_launcher);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(0);
            final int finalI = i;
            mTabHost.getTabWidget().getChildTabViewAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mToolbar.setTitle(mTextArray[finalI]);
                    mTabHost.setCurrentTab(finalI);
                }
            });
        }
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
        return view;
    }
}

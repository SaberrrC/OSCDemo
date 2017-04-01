package com.saberrr.openchina.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.ui.fragment.FindFragment;
import com.saberrr.openchina.ui.fragment.TestFragment1;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater  mLayoutInflater;

    private Class  mFragmentArray[] = {TestFragment1.class,
            TestFragment1.class,
            TestFragment1.class,
            FindFragment.class,
            TestFragment1.class};
//    private Class  mFragmentArray[] = {TestFragment1.class,
//            TestFragment1.class,
//            TestFragment1.class,
//            FindFragment.class,
//            TestFragment1.class};
    private String mTextArray[]     = {"首页", "消息", "好友", "搜索", "更多"};
    private int    mImageArray[]    = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);
        // 找到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content_main);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
//            if (i == 2) {
//                startActivity(new Intent(this,ShowActivity.class));
//            }
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i]).setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.mipmap.ic_launcher);
        }
    }

    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);
        return view;
    }
}

package com.saberrr.openchina.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.updatainfo.UpdateInfo;
import com.saberrr.openchina.manager.netmanager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.fragment.ComprehensiveFragment;
import com.saberrr.openchina.ui.fragment.FindFragment;
import com.saberrr.openchina.ui.fragment.JumpFragment;
import com.saberrr.openchina.ui.fragment.LoginFragment;
import com.saberrr.openchina.ui.fragment.MoveFragment;
import com.saberrr.openchina.ui.fragment.MyFragment;
import com.saberrr.openchina.ui.fragment.TestFragment2;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.Utils;
import com.saberrr.openchina.utils.XmlUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private Class   mFragmentArray[] = {ComprehensiveFragment.class, MoveFragment.class, TestFragment2.class, FindFragment.class, MyFragment.class};
    private String  mTextArray[]     = {"综合", "动弹", "", "发现", "我的"};
    private int     mImageArray[]    = {R.drawable.selector_all_bg, R.drawable.selector_dongtan_bg, R.drawable.selector_add_bg, R.drawable.selector_find_bg, R.drawable.selector_mine_bg};
    private long    laatTime         = 0;
    private String  currentId        = null;
    private boolean isFirstEnter     = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.showColoredBars(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //版本比对
        if (isFirstEnter) {
            isFirstEnter = false;
            ThreadUtils.runSub(new Runnable() {
                @Override
                public void run() {
                    String xml = JsonCacheManager.getInstance().getXML(Urls.MOBILEAPPVERSION);
                    final UpdateInfo updateInfo = XmlUtils.toBean(UpdateInfo.class, xml.getBytes());
                    System.out.println(updateInfo.toString());
                    String vcode = updateInfo.getUpdate().getAAndroid().getVersionCode();
                    int netCode = Integer.valueOf(vcode);
                    int locolCode = Utils.getVersionCode(getPackageName());
                    if (locolCode < netCode) {
                        ThreadUtils.runMain(new Runnable() {
                            @Override
                            public void run() {
                                // 普通
                                String updateLog = updateInfo.getUpdate().getAAndroid().getUpdateLog();
                                String replace = updateLog.replace("<br/>", "\n");
                                String log = replace.replace("<br>", "\n");
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                // 设置图标
                                // builder.setIcon(R.drawable.iv3);
                                // 设置标题
                                builder.setTitle("发现新版本");
                                // 设置消息内容
                                builder.setMessage(log);
                                // 点击旁边区域不会消失
                                builder.setCancelable(true);
                                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplication(), "您保存了您的菊花！", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                }
            });
        }
    }

    private void initToolBar() {
        mToolbarMain.setTitle(mTextArray[0]);
        setSupportActionBar(mToolbarMain);
        mIvIconToolbar.setVisibility(View.VISIBLE);
        mIvIconToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showToast("搜索界面");
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
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
        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                currentId = tabId;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentId != null) {
            mTabhost.setCurrentTab(Integer.parseInt(currentId));
        }
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

package com.saberrr.openchina.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.contact.Fiels;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.ui.fragment.BaseFragment;
import com.saberrr.openchina.utils.ToastUtils;

import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    private Toolbar   mToolbar;
    private ImageView mIvIconToolbar;
    private TextView  mTvTitleToolbar;

    private static final int TITLE_NONE   = 100;
    private static final int TITLE_SEARCH = 101;
    private static final int TITLE_BOOK   = 102;
    private static final int TITLE_CHOOSE = 103;
    private static final int TITLE_PEOPLE = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra(Fiels.DtailActivity.TITLE);
        intent.getStringExtra(Fiels.DtailActivity.TITLE);
        int title_icon = intent.getIntExtra(Fiels.DtailActivity.TOOBARICON, TITLE_NONE);
        initToolBar(title, title_icon);
        try {
            Bundle bundle = intent.getBundleExtra(Fiels.DtailActivity.BUNDLE);
            Class<BaseFragment> classname = (Class<BaseFragment>) intent.getSerializableExtra(Fiels.DtailActivity.CLASSNAME);
            BaseFragment baseFragment = classname.newInstance();
            baseFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_show, baseFragment).commitNow();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //设置toolbar
    private void initToolBar(String title, int title_icon) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mIvIconToolbar = (ImageView) findViewById(R.id.iv_icon_toolbar);
        mTvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        //返回箭头
        mIvIconToolbar.setVisibility(View.GONE);
        mTvTitleToolbar.setVisibility(View.GONE);
        switch (title_icon) {
            case TITLE_NONE:
                break;
            case TITLE_SEARCH:
                mIvIconToolbar.setVisibility(View.VISIBLE);
                mIvIconToolbar.setImageResource(R.mipmap.btn_search_normal);
                break;
            case TITLE_BOOK:
                mIvIconToolbar.setVisibility(View.VISIBLE);
                mIvIconToolbar.setImageResource(R.mipmap.btn_search_normal);
                break;
            case TITLE_CHOOSE:
                break;
            case TITLE_PEOPLE:
                break;
        }


        mIvIconToolbar.setVisibility(View.VISIBLE);
        mIvIconToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("搜索界面");
            }
        });
        //mTvTitleToolbar.setVisibility(View.GONE);
    }


    public static void startFragment(Class clss, Bundle bundle) {
        Intent intent = new Intent(AppApplication.appContext, ShowActivity.class);
        if (bundle != null) {
            intent.putExtra(Fiels.DtailActivity.BUNDLE, bundle);
        }
        intent.putExtra(Fiels.DtailActivity.CLASSNAME, clss);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppApplication.appContext.startActivity(intent);
    }

    public static void startFragmentWithTitle(Class clss, Bundle bundle, String title) {
        Intent intent = new Intent(AppApplication.appContext, ShowActivity.class);
        if (bundle != null) {
            intent.putExtra(Fiels.DtailActivity.BUNDLE, bundle);
        }
        intent.putExtra(Fiels.DtailActivity.CLASSNAME, clss);
        intent.putExtra(Fiels.DtailActivity.TITLE, title);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppApplication.appContext.startActivity(intent);
    }

    private void hideActionBar() {
        getSupportActionBar().hide();
        int flagFullscreen = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = getWindow();
        window.setFlags(flagFullscreen, flagFullscreen);
        window.getDecorView().setSystemUiVisibility(View.INVISIBLE);
    }

    private void showActionBar(String title) {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(title);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

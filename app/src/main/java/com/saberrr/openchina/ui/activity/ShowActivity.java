package com.saberrr.openchina.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        Intent intent = getIntent();
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
        String title = intent.getStringExtra(Fiels.DtailActivity.TITLE);
        if (!TextUtils.isEmpty(title)) {
            showActionBar(title);
        } else {

            hideActionBar();
        }
    }

    private Toolbar   mToolbar;
    private  ImageView mIvSearchToolbar;
    private  TextView  mTvTitleToolbar;

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mIvSearchToolbar = (ImageView) findViewById(R.id.iv_search_toolbar);
        mTvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
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


    public static void startFragment(Class clss, Bundle bundle) {
        Intent intent = new Intent(AppApplication.appContext, ShowActivity.class);
        if (bundle != null) {
            intent.putExtra(Fiels.DtailActivity.BUNDLE, bundle);
        }
        intent.putExtra(Fiels.DtailActivity.CLASSNAME, clss);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppApplication.appContext.startActivity(intent);
    }

    public static void startFragmentWithTitle(Class clss, Bundle bundle,String title) {
        Intent intent = new Intent(AppApplication.appContext, ShowActivity.class);
        if (bundle != null) {
            intent.putExtra(Fiels.DtailActivity.BUNDLE, bundle);
        }
        intent.putExtra(Fiels.DtailActivity.CLASSNAME, clss);
        intent.putExtra(Fiels.DtailActivity.TITLE,title);
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

package com.saberrr.openchina.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

public class ShowActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar   mToolbar;
    private ImageView mIvIconToolbar;
    private TextView  mTvTitleToolbar;
    /**
     * @TITLE_NONE 右边没东西
     * @TITLE_SEARCH 右边搜索图标
     * @TITLE_COMMENT 右边是评论数量
     * @TITLE_CHOOSE 右边文字 “选择”
     * @TITLE_PEOPLE “找人”专用
     */
    private static final int TITLE_NONE    = 100;//右边没东西
    private static final int TITLE_SEARCH  = 101;//右边搜索图标
    private static final int TITLE_COMMENT = 102;//右边是评论数量
    private static final int TITLE_CHOOSE  = 103;//右边文字 “选择”
    private static final int TITLE_PEOPLE  = 104;//“找人”专用
    private TextView   mTvRightToolbar;
    private SearchView mSearchView;

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
    public void initToolBar(String title, int title_icon) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mIvIconToolbar = (ImageView) findViewById(R.id.iv_icon_toolbar);
        mTvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        mTvRightToolbar = (TextView) findViewById(R.id.tv_right_toolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        //返回箭头
        mIvIconToolbar.setVisibility(View.GONE);
        mTvTitleToolbar.setVisibility(View.GONE);
        mSearchView.setVisibility(View.GONE);
        switch (title_icon) {
            case TITLE_NONE:
                break;
            case TITLE_SEARCH:
                mIvIconToolbar.setVisibility(View.VISIBLE);
                mIvIconToolbar.setImageResource(R.mipmap.btn_search_normal);
                break;
            case TITLE_COMMENT:
                mTvRightToolbar.setVisibility(View.VISIBLE);
                mTvRightToolbar.setBackgroundResource(R.drawable.ic_menu_comment);
                mTvRightToolbar.setText("数量");
                break;
            case TITLE_CHOOSE:
                mTvRightToolbar.setVisibility(View.VISIBLE);
                mTvRightToolbar.setBackgroundResource(0);
                mTvRightToolbar.setText("数量");
                break;
            case TITLE_PEOPLE:
                mSearchView.setVisibility(View.VISIBLE);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_searviewu, menu);
        MenuItem item = menu.findItem(R.id.search);
        mSearchView = (SearchView) item.getActionView();
        //设置提示文字
        mSearchView.setQueryHint("请输入关键字");
        //设置文字搜索监听
        mSearchView.setOnQueryTextListener(this);
        return true;
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

    //menu回调 两个
    @Override
    public boolean onQueryTextSubmit(String query) {
        ToastUtils.showToast("去服务器查询 " + query + " 数据");
        mSearchView.clearFocus();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

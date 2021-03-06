package com.saberrr.openchina.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.contact.Fiels;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.ui.fragment.BaseFragment;
import com.saberrr.openchina.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private int mTitle_icon = TITLE_NONE;

    /**
     * @TITLE_NONE 右边没东西
     * @TITLE_SEARCH 右边搜索图标
     * @TITLE_COMMENT 右边是评论数量
     * @TITLE_CHOOSE 右边文字 “选择”
     * @TITLE_PEOPLE “找人”专用
     */
    public static final int TITLE_NONE    = 100;//右边没东西
    public static final int TITLE_SEARCH  = 101;//右边搜索图标
    public static final int TITLE_COMMENT = 102;//右边是评论数量
    public static final int TITLE_SEND    = 103;//右边文字 “选择”
    public static final int TITLE_PEOPLE  = 104;//“找人”专用
    public static final int TITLE_MENU    = 105;//MENU布局
    public static final int TITLE_LEFT    = 105;//MENU布局
    private TextView    mTvRightToolbar;
    private SearchView  mSearchView;
    private Toolbar     mToolbar;
    private ImageView   mIvIconToolbar;
    private TextView    mTvTitleToolbar;
    private ImageView   mIvCommendBG;
    private FrameLayout mFlCommend;
    private TextView    mTvCommend;
    private int     mTitle_menu           = -1;
    private boolean touchHintKeyboard     = false;
    private boolean hintKeyboardexception = false;
    private TextView mTvLeftToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  StatusBarCompat.compat(this, Color.parseColor("#24cf5f"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/
        Utils.showColoredBars(this);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra(Fiels.DtailActivity.TITLE);
        mTitle_icon = intent.getIntExtra(Fiels.DtailActivity.TOOBARICON, TITLE_NONE);
        initToolbar();
        initToolBar(title, mTitle_icon);
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

    public interface OnBackIconClickListener {
        void onClick(View v);
    }

    private OnBackIconClickListener mOnBackIconClickListener;

    public void setOnBackIconClickListener(OnBackIconClickListener onBackIconClickListener) {
        mOnBackIconClickListener = onBackIconClickListener;
    }

    //设置toolbar
    public void initToolBar(String title, final int title_icon) {
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //自带导航图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBackIconClickListener != null) {
                    mOnBackIconClickListener.onClick(v);
                } else {
                    finish();
                }
            }
        });
        mIvIconToolbar.setVisibility(View.GONE);
        mTvTitleToolbar.setVisibility(View.GONE);
        mFlCommend.setVisibility(View.GONE);
        mIvCommendBG.setVisibility(View.GONE);
        mTvCommend.setVisibility(View.GONE);
        if (mSearchView != null) {
            mSearchView.setVisibility(View.GONE);
        }
        switch (title_icon) {
            case TITLE_NONE:
                break;
            case TITLE_SEARCH:
                mIvIconToolbar.setVisibility(View.VISIBLE);
                mIvIconToolbar.setImageResource(R.mipmap.btn_search_normal);
                break;
            case TITLE_COMMENT:
                mFlCommend.setVisibility(View.VISIBLE);
                mIvCommendBG.setVisibility(View.VISIBLE);
                mTvCommend.setVisibility(View.VISIBLE);
                break;
            case TITLE_SEND:
                mToolbar.setTitle("");
                mIvIconToolbar.setVisibility(View.GONE);
                mTvTitleToolbar.setVisibility(View.VISIBLE);
                mTvRightToolbar.setVisibility(View.VISIBLE);
                mTvRightToolbar.setBackgroundResource(0);
                mTvTitleToolbar.setText(title);
                mTvRightToolbar.setText("发送");
                break;
            case TITLE_PEOPLE:
                if (mSearchView != null) {
                    mSearchView.setVisibility(View.VISIBLE);
                }
                break;
            case TITLE_MENU:
                mTitle_menu = getIntent().getIntExtra(Fiels.DtailActivity.MENU, TITLE_MENU);
                break;
            default:
                throw new RuntimeException("请传入指定标题类型参数");
        }
    }

    public void setCommentCount(String count) {
        if (mTitle_icon == TITLE_COMMENT) {
            mTvCommend.setText(count);
        } else {
            throw new RuntimeException("传入标题类型必须是 TITLE_COMMENT ");
        }

    }

    /**
     * 获取右边toolbar上的textview
     *
     * @return
     */
    public TextView getRightTextView() {
        return mTvRightToolbar;
    }

    /**
     * 获取左边toolbar上的textview
     *
     * @return
     */
    public TextView getLeftTextView() {
        return mTvLeftToolbar;
    }

    /**
     * shezhitoolr右上角文字
     *
     * @param text
     */
    public void setvRightToolbarText(String text) {
        mTvRightToolbar.setText(text);
    }

    private OnClickListener mOnClickListener;

    @OnClick({R.id.fl_commend, R.id.tv_right_toolbar, R.id.iv_right_icon_toolbar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_commend:
            case R.id.tv_right_toolbar:
            case R.id.iv_right_icon_toolbar:
                if (mOnClickListener != null) {
                    mOnClickListener.onClick();
                }
                break;
        }
    }

    public interface OnClickListener {
        void onClick();
    }

    /**
     * toolbar 条目点击监听
     *
     * @param onClickListener 点击监听
     */
    public void setToolbarIconOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mIvIconToolbar = (ImageView) findViewById(R.id.iv_right_icon_toolbar);
        mTvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        mTvLeftToolbar = (TextView) findViewById(R.id.tv_left_toolbar);
        mTvRightToolbar = (TextView) findViewById(R.id.tv_right_toolbar);
        mFlCommend = (FrameLayout) findViewById(R.id.fl_commend);
        mIvCommendBG = (ImageView) findViewById(R.id.iv_commend_bg);
        mTvCommend = (TextView) findViewById(R.id.tv_commend);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mTitle_icon == TITLE_PEOPLE) {
            getMenuInflater().inflate(R.menu.menu_searviewu, menu);
            MenuItem item = menu.findItem(R.id.search);
            mSearchView = (SearchView) item.getActionView();
            //设置提示文字
            mSearchView.setQueryHint("请输入关键字");
            //设置文字搜索监听
            mSearchView.setOnQueryTextListener(this);
            return true;
        }
        if (mTitle_icon == TITLE_MENU) {
            getMenuInflater().inflate(mTitle_menu, menu);
            //右上角menu按钮显示
            if (menu instanceof MenuBuilder) {
                MenuBuilder menuBuilder = (MenuBuilder) menu;
                menuBuilder.setOptionalIconsVisible(true);
            }
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mTitle_icon == TITLE_MENU) {
            super.onBackPressed();
            if (mOnOptionsItemSelected != null) {
                mOnOptionsItemSelected.onOptionsMenu(item);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(mOnMyBackPressed != null) {
            mOnMyBackPressed.onBackPressed();
        }else {
            super.onBackPressed();
        }
    }

    public interface OnMyBackPressed {
        void onBackPressed();
    }
    private OnMyBackPressed mOnMyBackPressed;

    public void setOnMyBackPressed(OnMyBackPressed onMyBackPressed) {
        mOnMyBackPressed = onMyBackPressed;
    }

    public interface onOptionsItemSelected {
        void onOptionsMenu(MenuItem item);
    }

    private onOptionsItemSelected mOnOptionsItemSelected;

    public void setonOptionsItemSelected(onOptionsItemSelected onOptionsItemSelected) {
        mOnOptionsItemSelected = onOptionsItemSelected;
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
        startFragmentWithTitle(clss, bundle, title, TITLE_NONE);
    }

    public static void startFragmentWithTitle(Class clss, Bundle bundle, String title, int right) {
        Intent intent = new Intent(AppApplication.appContext, ShowActivity.class);
        if (bundle != null) {
            intent.putExtra(Fiels.DtailActivity.BUNDLE, bundle);
        }
        intent.putExtra(Fiels.DtailActivity.CLASSNAME, clss);
        intent.putExtra(Fiels.DtailActivity.TITLE, title);
        intent.putExtra(Fiels.DtailActivity.TOOBARICON, right);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppApplication.appContext.startActivity(intent);
    }

    /***
     * @param clss
     * @param bundle
     * @param title
     * @param right
     * @param menuLayout menu的布局
     */
    public static void startFragmentWithTitleMenu(Class clss, Bundle bundle, String title, int right, int menuLayout) {
        Intent intent = new Intent(AppApplication.appContext, ShowActivity.class);
        if (bundle != null) {
            intent.putExtra(Fiels.DtailActivity.BUNDLE, bundle);
        }
        intent.putExtra(Fiels.DtailActivity.CLASSNAME, clss);
        intent.putExtra(Fiels.DtailActivity.MENU, menuLayout);
        intent.putExtra(Fiels.DtailActivity.TITLE, title);
        intent.putExtra(Fiels.DtailActivity.TOOBARICON, right);
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

    public interface OnQueryTextListener {
        boolean onQueryTextSubmit();

        boolean onQueryTextChange();
    }

    private OnQueryTextListener mOnQueryTextListener;

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        mOnQueryTextListener = onQueryTextListener;
    }

    //menu回调 两个
    @Override
    public boolean onQueryTextSubmit(String query) {
        if (mOnQueryTextListener != null) {
            mOnQueryTextListener.onQueryTextSubmit();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (mOnQueryTextListener != null) {
            mOnQueryTextListener.onQueryTextChange();
        }
        return false;
    }

    public void setHintKeyboard(boolean touchHintKeyboard) {
        this.touchHintKeyboard = touchHintKeyboard;
    }

    public void setHintKeyboardexception() {
        hintKeyboardexception = true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (hintKeyboardexception) {
            hintKeyboardexception = false;
            return super.dispatchTouchEvent(ev);
        }
        if (touchHintKeyboard) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (isShouldHideInput(v, ev)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return super.dispatchTouchEvent(ev);
            }
            // 必不可少，否则所有的组件都不会有TouchEvent了
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }
            return onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}

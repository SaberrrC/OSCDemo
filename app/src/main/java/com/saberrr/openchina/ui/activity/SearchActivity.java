package com.saberrr.openchina.ui.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.FragmentInfo;
import com.saberrr.openchina.ui.adapter.KeywordAdapter;
import com.saberrr.openchina.ui.adapter.OswViewPagerAdapter;
import com.saberrr.openchina.ui.fragment.FindUserFragment;
import com.saberrr.openchina.ui.fragment.SearchBlogFragment;
import com.saberrr.openchina.ui.fragment.SearchNewsFragment;
import com.saberrr.openchina.ui.fragment.SearchPostFragment;
import com.saberrr.openchina.ui.fragment.SearchSoftwareFragment;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements TextWatcher, KeywordAdapter.OnKeywordItemClickListener {

    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tl_search_layout)
    TabLayout mTlSearchLayout;
    @BindView(R.id.vp_search_layout)
    ViewPager mVpSearchLayout;
    @BindView(R.id.ll_search_viewpager)
    LinearLayout mLlSearchViewpager;
    @BindView(R.id.lv_search_layout)
    ListView mLvSearchLayout;
    @BindView(R.id.ll_search_history)
    LinearLayout mLlSearchHistory;
    @BindView(R.id.iv_search_delete)
    ImageView mIvSearchDelete;
    private List<FragmentInfo> datas = new ArrayList<>();
    private List<String> keywords = new ArrayList<>();
    private KeywordAdapter mKeywordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 这个属性4.4算是全透明（有的机子是过渡形式的透明），5.0就是半透明了 我的模拟器、真机都是半透明，
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 5.0 全透明实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);// calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        String[] title = Utils.getStringArray(R.array.tab_names);
        datas.add(new FragmentInfo(new SearchSoftwareFragment(), title[9]));
        datas.add(new FragmentInfo(new SearchBlogFragment(), title[1]));
        datas.add(new FragmentInfo(new SearchNewsFragment(), title[0]));
        datas.add(new FragmentInfo(new SearchPostFragment(), title[2]));
        datas.add(new FragmentInfo(new FindUserFragment(), title[10]));
        OswViewPagerAdapter oswViewPagerAdapter = new OswViewPagerAdapter(getSupportFragmentManager(), datas);
        mVpSearchLayout.setAdapter(oswViewPagerAdapter);
        mTlSearchLayout.setupWithViewPager(mVpSearchLayout);
        mTlSearchLayout.setTabTextColors(Color.parseColor("#9F9F9F"), Color.parseColor("#0DB22E"));
        mTlSearchLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT);
        mEtSearch.addTextChangedListener(this);
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //处理事件

                    final String keyword = v.getText().toString().trim();
                    //ToastUtils.showToast("搜索啦" + keyword);
                    EventBus.getDefault().postSticky(keyword);

                    if (!TextUtils.isEmpty(keyword)) {
                        ThreadUtils.runMain(new Runnable() {
                            @Override
                            public void run() {
                                if (keywords == null || keywords.size() == 0) {
                                    keywords.add(keyword);
                                } else {
                                    if (keywords.contains(keyword)) {
                                        keywords.remove(keyword);
                                    }
                                    keywords.add(0, keyword);
                                }

                                mKeywordAdapter.notifyDataSetChanged();
                                mLlSearchViewpager.setVisibility(View.VISIBLE);
                                mLlSearchHistory.setVisibility(View.GONE);
                            }
                        });

                    }
                    return true;
                }
                return false;
            }
        });
        if (mKeywordAdapter == null) {
            mKeywordAdapter = new KeywordAdapter(keywords);
        } else {
            mLvSearchLayout.requestLayout();
            mKeywordAdapter.notifyDataSetChanged();
        }
        mLvSearchLayout.setAdapter(mKeywordAdapter);
        mKeywordAdapter.setOnKeywordItemClickListener(this);
        mIvSearchDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearch.setText("");
            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //ToastUtils.showToast("beforeTextChanged=" + s.toString());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //ToastUtils.showToast("onTextChanged=" + s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
        //ToastUtils.showToast("afterTextChanged=" + s.toString());
        String edit = s.toString().trim();
        if (TextUtils.isEmpty(edit)) {
            ThreadUtils.runMain(new Runnable() {
                @Override
                public void run() {
                    mIvSearchDelete.setVisibility(View.GONE);
                    mLlSearchViewpager.setVisibility(View.GONE);
                    mKeywordAdapter.notifyDataSetChanged();
                    mLlSearchHistory.setVisibility(View.VISIBLE);
                }
            });
        }else {
            mIvSearchDelete.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.bt_search_cancel, R.id.bt_search_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_search_cancel:
                finish();
                break;
            case R.id.bt_search_delete:
                final AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确认清空搜索历史记录吗？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        keywords.clear();
                        ThreadUtils.runMain(new Runnable() {
                            @Override
                            public void run() {
                                mKeywordAdapter.notifyDataSetChanged();
                                mLlSearchHistory.setVisibility(View.GONE);
                            }
                        });

                    }
                });
                builder.show();

                break;
        }
    }


    @Override
    public void onKeywordItemClick(int position, final String keyword) {
        ToastUtils.showToast(keyword);
        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mEtSearch.setText(keyword);
                mEtSearch.setSelection(keyword.length());
                mLlSearchHistory.setVisibility(View.GONE);
                mLlSearchViewpager.setVisibility(View.VISIBLE);
            }
        });

    }
}

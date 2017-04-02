package com.blink.test.bannerview;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FinalRecycleAdapter.OnViewAttachListener {

    private Toolbar mToolbar;
    private int[] imgs = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    private List<View> viewList;
    BannerView bannerView;
    private TextView           tvTitle;
    private RecyclerView       mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initBanaView();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sf_main);
        setRecyclerView();
    }

    private void setRecyclerView() {
        List<Object> datas = initData();
        HashMap<Class, Integer> map = new HashMap<>();
        map.put(String.class, R.layout.layout_1);
        map.put(Integer.class, R.layout.layout_2);
        map.put(Long.class, R.layout.layout_3);
        mRecyclerView.setAdapter(new FinalRecycleAdapter(datas, map, this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
    }

    private List<Object> initData() {
        List<Object> objects = new ArrayList<>();
        objects.add("string " + SystemClock.currentThreadTimeMillis());
        objects.add(new Long(3L));
        objects.add("string 3" + SystemClock.currentThreadTimeMillis());
        objects.add(2);
        objects.add("string 2" + SystemClock.currentThreadTimeMillis());
        objects.add(3);
        objects.add(3);
        objects.add(3);
        objects.add(new Long(2L));
        objects.add(1);
        objects.add(new Long(1L));
        return objects;
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        if (itemData instanceof String) {
            String s = (String) itemData;
            TextView tv1 = (TextView) holder.getViewById(R.id.tv1);
            tv1.setText(s);
        }
        if (itemData instanceof Integer) {
            Integer s = (Integer) itemData;
            TextView tv1 = (TextView) holder.getViewById(R.id.tv2);
            tv1.setText(s + "   Integer");
        }
        if (itemData instanceof Long) {
            Long s = (Long) itemData;
            TextView tv1 = (TextView) holder.getViewById(R.id.tv3);
            tv1.setText(s + "    Long");
        }
    }


    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) mToolbar.findViewById(R.id.tv_toolbar);
        mToolbar.setTitle("我的标题");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //自带导航图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initBanaView() {
        viewList = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageResource(imgs[i]);
            viewList.add(image);
        }
        bannerView = (BannerView) findViewById(R.id.banner);
        bannerView.setViewList(viewList);
        bannerView.startLoop(true);
        //        bannerView.setTransformAnim(true);
    }
}

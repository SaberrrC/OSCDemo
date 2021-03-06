package com.saberrr.openchina.ui.fragment;

import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Saberrr on 2017-04-01.
 */

public class TestFragment1 extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {

    private RecyclerView mRecyclerView;

    private String[]  mStrings = {"String1", "String2", "String3", "String4"};
    private Integer[] mIntt    = {1, 2, 3, 4};
    private Long[]    mObjects = {1L, 2L, 3L, 4L};
    private List<Object>        mDatas;
    private FinalRecycleAdapter mFinalRecycleAdapter;
    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_test1, null, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        setRecyclerView();
        return view;
    }

    @Override
    public Object getData() {
        return "";
    }

    private void setRecyclerView() {
        mDatas = initData();
        HashMap<Class, Integer> map = new HashMap<>();
        map.put(String.class, R.layout.layout_1);
        map.put(Integer.class, R.layout.layout_2);
        map.put(Long.class, R.layout.layout_3);
        mFinalRecycleAdapter = new FinalRecycleAdapter(mDatas, map, this);
        mFinalRecycleAdapter.setNeedLoadMore(true, R.layout.layout_4);
        mRecyclerView.setAdapter(mFinalRecycleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false));
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
        int i = position;
        Log.d("i", "onBindViewHolder: ==============" + i++);
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
        if (position == mDatas.size()) {
            TextView tv4 = (TextView) holder.getViewById(R.id.tv3);
            tv4.setText("下拉加载更多" + SystemClock.currentThreadTimeMillis() + "");
            ToastUtils.showToast(SystemClock.currentThreadTimeMillis() + "");
            mFinalRecycleAdapter.notifyDataSetChangedNew(mDatas);
            changgeData();
        }
    }

    private void changgeData() {
        mDatas.add("string 3" + SystemClock.currentThreadTimeMillis());
        mDatas.add(2);
        mDatas.add("string 2" + SystemClock.currentThreadTimeMillis());
        mDatas.add(3);
        mDatas.add(3);
        mDatas.add(3);
        mDatas.add(new Long(2L));
        mDatas.add(1);
        mFinalRecycleAdapter.notifyDataSetChanged();
    }
}

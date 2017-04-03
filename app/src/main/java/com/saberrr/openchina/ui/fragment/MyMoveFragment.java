package com.saberrr.openchina.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.MoveNewBean;
import com.saberrr.openchina.manager.netmanager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tt on 2017/4/3.
 */

public class MyMoveFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {

    private RecyclerView mRecyclerView;
    private FinalRecycleAdapter mAdapter;
    private boolean isUpdate = true;


    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = View.inflate(getContext(), R.layout.fragment_move_new, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        setRecyclerView();
        return view;
    }
    private void setRecyclerView() {


        HashMap<Class, Integer> map = new HashMap<>();
        map.put(MoveNewBean.ResultBean.ItemsBean.class, R.layout.item_move_new);

        mAdapter = new FinalRecycleAdapter(data, map, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    final List<MoveNewBean.ResultBean.ItemsBean> data = new ArrayList<>();
    @Override
    public Object getData() {
        final MoveNewBean moveNewBean = JsonCacheManager.getInstance().getDataBean(Urls.MOVE_NEW, MoveNewBean.class);
        final List<MoveNewBean.ResultBean.ItemsBean> items = moveNewBean.getResult().getItems();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isUpdate) {
                    data.clear();
                    data.addAll(0, items);
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    data.addAll(items);
                }
                isUpdate = true;
            }
        });
        data.addAll(items);

        getActivity().runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {

                        mAdapter.notifyDataSetChanged();
                    }
                }
        );
        return data;
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {

    }
}

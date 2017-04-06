package com.saberrr.openchina.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.FriendInfoBean;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.ui.view.ContactLayout;
import com.saberrr.openchina.ui.view.SlideBar;
import com.saberrr.openchina.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Saberrr on 2017-04-06.
 */

public class AtFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.et_at)
    EditText      mEtAt;
    @BindView(R.id.cl_at)
    ContactLayout mClAt;
    private List<Object> mInfos = new ArrayList<>();
    private FinalRecycleAdapter mFinalRecycleAdapter;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = creatViewFromId(R.layout.fragment_at);
        TextView rightTextView = getRightTextView();
        rightTextView.setText("确定");
        setToolbarIconOnClickListener(new ShowActivity.OnClickListener() {
            @Override
            public void onClick() {
                ToastUtils.showToast("666");
            }
        });

        Map<Class, Integer> map = FinalRecycleAdapter.getMap();
        map.put(FriendInfoBean.class, R.layout.contact_layout);
        mInfos.add(new FriendInfoBean());
        mInfos.add(new FriendInfoBean());
        mInfos.add(new FriendInfoBean());
        mFinalRecycleAdapter = new FinalRecycleAdapter(mInfos, map, this);
        mClAt.setAdapter(mFinalRecycleAdapter);

        return view;
    }

    @Override
    public Object getData() {
        return "";
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) holder.getViewById(R.id.swipeRefreshLayout);
        RecyclerView recyclerView = (RecyclerView) holder.getViewById(R.id.recyclerView);
        TextView tvFloat = (TextView) holder.getViewById(R.id.tv_float);
        SlideBar slideBar = (SlideBar) holder.getViewById(R.id.slideBar);
    }
}

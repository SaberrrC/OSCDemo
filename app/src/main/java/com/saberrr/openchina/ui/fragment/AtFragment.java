package com.saberrr.openchina.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.FriendInfoBean;
import com.saberrr.openchina.manager.netmanager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.ui.view.ContactLayout;
import com.saberrr.openchina.ui.view.SlideBar;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.XmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saberrr on 2017-04-06.
 */

public class AtFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener{
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_at, null, false);
        ButterKnife.bind(this, view);
        TextView rightTextView = getRightTextView();
        rightTextView.setText("确定");
        setToolbarIconOnClickListener(new ShowActivity.OnClickListener() {
            @Override
            public void onClick() {
                ToastUtils.showToast("666");
            }
        });

        Map<Class, Integer> map = FinalRecycleAdapter.getMap();
        map.put(FriendInfoBean.class, R.layout.list_item_contact);
        mInfos.add(new FriendInfoBean());
        mInfos.add(new FriendInfoBean());
        mInfos.add(new FriendInfoBean());
        mFinalRecycleAdapter = new FinalRecycleAdapter(mInfos, map, this);
        List<String> names = new ArrayList<>();
       /* for (Object info : mInfos) {
            FriendInfoBean friendInfoBean = (FriendInfoBean) info;
            String name = friendInfoBean.getFriend().getName();
            names.add(name);
        }*/
        for (int i = 0; i < 30; i++) {
            names.add(i + "");
        }
        mFinalRecycleAdapter.setStringList(names);
        mClAt.setAdapter(mFinalRecycleAdapter);

        return view;
    }

    @Override
    public Object getData() {
        String xml = JsonCacheManager.getInstance().getXML(Urls.ATFRIENDS + "uid=" + SpUtil.getString(getContext(), Constant.USERID, "") + "&relation=1&all=1");
        FriendInfoBean friendInfoBean = XmlUtils.toBean(FriendInfoBean.class, xml.getBytes());
        FriendInfoBean.Friends friend = friendInfoBean.getFriend();


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

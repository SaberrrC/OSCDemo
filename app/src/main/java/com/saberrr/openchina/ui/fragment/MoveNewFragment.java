package com.saberrr.openchina.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.MoveNewBean;
import com.saberrr.openchina.manager.netmanager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by tt on 2017/4/1.
 */

public class MoveNewFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {

    private static final String TAG = "MoveNewFragment";

    private RecyclerView mRecyclerView;
    List<MoveNewBean> mShowItems = new ArrayList<>();

    @Override
    protected boolean needRefresh() {
        return true;
    }

    @Override
    public View createView() {
        View view = View.inflate(getContext(), R.layout.fragment_move_new, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        List<Object> datas = initData();

        HashMap<Class, Integer> map = new HashMap<>();
        map.put(MoveNewBean.ResultBean.class, R.layout.item_move_new);

        mRecyclerView.setAdapter(new FinalRecycleAdapter(datas, map, this));

    }


    private List<Object> initData() {
        final List<Object> data = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                MoveNewBean moveNewBean = JsonCacheManager.getInstance().getDataBean(Urls.MOVE_NEW, MoveNewBean.class);
                List<MoveNewBean.ResultBean.ItemsBean> items = moveNewBean.getResult().getItems();
                Log.i(TAG, "initData: item = " + items);
                data.addAll(items);
            }
        });

        Log.i(TAG, "initData: data = " + data);
        return data;
    }

    @Override
    public Object getData() {
        return "";
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {

        if (itemData instanceof MoveNewBean.ResultBean.ItemsBean) {
            MoveNewBean.ResultBean.ItemsBean bean = (MoveNewBean.ResultBean.ItemsBean) itemData;
            ImageView iv_icon = (ImageView) holder.getViewById(R.id.item_move_iv_icon);
            TextView tv_name = (TextView) holder.getViewById(R.id.item_move_tv_name);
            TextView tv_txt = (TextView) holder.getViewById(R.id.item_move_tv_text);
            TextView tv_date = (TextView) holder.getViewById(R.id.move_tv_date);
            TextView tv_good = (TextView) holder.getViewById(R.id.move_tv_good);
            TextView tv_comment = (TextView) holder.getViewById(R.id.move_tv_comment);

            TextView tv_relay = (TextView) holder.getViewById(R.id.move_tv_relay);

            MoveNewBean.ResultBean.ItemsBean.AuthorBean author = bean.getAuthor();
            String name = author.getName();
            String portrait = author.getPortrait();
            tv_name.setText(name);
            String content = bean.getContent();
            tv_txt.setText(content);
            tv_date.setText(bean.getPubDate());
            tv_good.setText(bean.getLikeCount());


            Glide.with(getContext()).load(portrait).asBitmap().into(iv_icon);

        }
    }
}

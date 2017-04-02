package com.saberrr.openchina.ui.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
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
    private FinalRecycleAdapter mAdapter;

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
        List<MoveNewBean.ResultBean.ItemsBean> items = moveNewBean.getResult().getItems();
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

            //头像
            String portrait = author.getPortrait();

            Glide.with(getContext()).load(portrait).asBitmap().into(iv_icon);

            //名字
            String name = author.getName();
            tv_name.setText(name);


            //时间
            tv_date.setText(bean.getPubDate());

            //赞
            if (bean.getLikeCount() > 0) {
                tv_good.setText(bean.getLikeCount() + "");
            }
            //评论
            if (bean.getCommentCount() > 0) {
                tv_comment.setText(bean.getCommentCount() + "");
            }
            if (bean.getStatistics().getTransmit() > 0) {
                tv_relay.setText(bean.getStatistics().getTransmit() + "");
            }

//            bean.get

            //文本内容
            String content = bean.getContent();
//            tv_txt.setText(content);
            tv_txt.setText(Html.fromHtml(content)); //这个不好，不能点

            Spanned spanned = Html.fromHtml(content);

            SpannableString msp = new SpannableString(spanned);

            msp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            tv_txt.setText(msp);
            tv_txt.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}

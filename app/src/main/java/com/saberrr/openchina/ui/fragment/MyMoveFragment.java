package com.saberrr.openchina.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.MoveNewBean;
import com.saberrr.openchina.manager.netmanager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tt on 2017/4/3.
 */

public class MyMoveFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {

    private static final String TAG = "MyMoveFragment";

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
        final MoveNewBean moveNewBean = JsonCacheManager.getInstance().getDataBean(Urls.MOVE_MY, MoveNewBean.class);

        final List<MoveNewBean.ResultBean.ItemsBean> items = moveNewBean.getResult().getItems();


        getActivity().runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {

                        mAdapter.notifyDataSetChanged();
                    }
                }
        );
        data.addAll(items);
        return data;
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        if (itemData instanceof MoveNewBean.ResultBean.ItemsBean) {

            MoveNewBean.ResultBean.ItemsBean bean = (MoveNewBean.ResultBean.ItemsBean) itemData;

            ShowView(holder, bean);

        }
    }

    private void ShowView(FinalRecycleAdapter.ViewHolder holder, MoveNewBean.ResultBean.ItemsBean bean) {
        ImageView iv_icon = (ImageView) holder.getViewById(R.id.item_move_iv_icon);
        TextView tv_name = (TextView) holder.getViewById(R.id.item_move_tv_name);
        TextView tv_txt = (TextView) holder.getViewById(R.id.item_move_tv_text);
        TextView tv_date = (TextView) holder.getViewById(R.id.move_tv_date);
        TextView tv_good = (TextView) holder.getViewById(R.id.move_tv_good);
        TextView tv_comment = (TextView) holder.getViewById(R.id.move_tv_comment);

        TextView tv_relay = (TextView) holder.getViewById(R.id.move_tv_relay);

        GridLayout gridLayout = (GridLayout) holder.getViewById(R.id.move_new_item_gridLayout);

        MoveNewBean.ResultBean.ItemsBean.AuthorBean author = bean.getAuthor();

        String portrait = author.getPortrait();
        //头像

        Glide.with(getContext()).load(portrait).asBitmap().into(iv_icon);


        //时间
        String pubDate = bean.getPubDate();
        long parseTime = parseTime(pubDate);
        long endTime = System.currentTimeMillis();
        long time = endTime - parseTime;
        long m = time / 1000 / 60;
        Log.i(TAG, "ShowView: m" + m);
        if (m < 60) {
            tv_date.setText(m + "分钟前");
        } else {
            long h = m / 60;
            tv_date.setText(h + "小时前");
        }


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
//            tv_txt.setText(Html.fromHtml(content)); //这个不好，不能点

        Spanned spanned = Html.fromHtml(content);

        SpannableString msp = new SpannableString(spanned);

//        msp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tv_txt.setText(msp);
        tv_txt.setMovementMethod(LinkMovementMethod.getInstance());

        List<MoveNewBean.ResultBean.ItemsBean.ImagesBean> images = bean.getImages();
        gridLayout.setColumnCount(3);
        int width = gridLayout.getMeasuredWidth();
        int childWidth = width / 3 - 20;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(childWidth, childWidth);
//            layoutParams.setMargins(10, 10, 10, 10);
        gridLayout.removeAllViews();
        if (images != null && images.size() != 0) {
            //图片
            for (int i = 0; i < images.size(); i++) {
                ImageView iv = new ImageView(getContext());
                MoveNewBean.ResultBean.ItemsBean.ImagesBean imagesBean = images.get(i);
                String thumb = imagesBean.getThumb();
                Glide.with(getContext()).load(thumb).asBitmap().into(iv);
                iv.setLayoutParams(layoutParams);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                final int finalI = i;
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "图片" + finalI + "被点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                gridLayout.addView(iv);
            }
        }  //名字
        String name = author.getName();
        tv_name.setText(name);
    }

    public long parseTime(String date) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(date);
        String[] split = m.replaceAll(" ").trim().split(" ");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]));

        return calendar.getTimeInMillis();
    }

//    public String getSystemTime() {
//        Date nowTime = new Date(System.currentTimeMillis());
//        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
//        String retStrFormatNowDate = sdFormatter.format(nowTime);
//        return retStrFormatNowDate;
//    }

}

package com.saberrr.openchina.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.MoveNewBean;
import com.saberrr.openchina.manager.netmanager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.MoveDetailActivity;
import com.saberrr.openchina.ui.activity.ShowImageActivity;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.saberrr.openchina.R.id.recyclerView;


/**
 * Created by tt on 2017/4/1.
 */

public class MoveNewFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {

    private static final String TAG = "MoveNewFragment";

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
        mRecyclerView = (RecyclerView) view.findViewById(recyclerView);
        setRecyclerView();
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
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

        final MoveNewBean moveNewBean = JsonCacheManager.getInstance().getDataBean(Urls.MOVE_NEW + 1, MoveNewBean.class);
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
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, final int position, final Object itemData) {


        if (itemData instanceof MoveNewBean.ResultBean.ItemsBean) {

            final MoveNewBean.ResultBean.ItemsBean bean = (MoveNewBean.ResultBean.ItemsBean) itemData;

            holder.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2017/4/5 点击事件
                    Intent intent = new Intent(getContext(), MoveDetailActivity.class);
                    intent.putExtra("bean", bean);
                    startActivity(intent);

                }
            });

            ShowView(holder, bean, position);

        }
    }

    private void ShowView(FinalRecycleAdapter.ViewHolder holder, final MoveNewBean.ResultBean.ItemsBean bean, final int position) {


        ImageView iv_icon = (ImageView) holder.getViewById(R.id.item_move_iv_icon);
        TextView tv_name = (TextView) holder.getViewById(R.id.item_move_tv_name);
        TextView tv_txt = (TextView) holder.getViewById(R.id.item_move_tv_text);
        TextView tv_date = (TextView) holder.getViewById(R.id.move_tv_date);
        TextView phone = (TextView) holder.getViewById(R.id.phone);

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
        Log.i(TAG, "ShowView: parseTime = " + parseTime);
        long endTime = parseTime(getSystemTime());
        Log.i(TAG, "ShowView: endTime = " + endTime);
        long time = endTime - parseTime;
        int m = (int) (time / 1000 / 60);
        Log.i(TAG, "ShowView: m" + m);

        if (m < 3) {
            tv_date.setText("刚刚");
        } else if (m < 60) {
            tv_date.setText(m + "分钟前");
        } else if (m / 60 < 24) {
            long h = m / 60;
            tv_date.setText(h + "小时前");
        } else if (m / 60 / 24 < 30) {
            int d = m / 60 / 24;
            tv_date.setText(d + "天前");
        }
        switch (bean.getAppClient()) {
            case 3:
                phone.setText("android");
                break;
            case 4:
                phone.setText("iphone");
                break;
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
        Spanned spanned = Html.fromHtml(content);
        SpannableStringBuilder msp = new SpannableStringBuilder(spanned);
        Spannable spannable = Utils.displayEmoji(getContext().getResources(), msp);

        tv_txt.setText(msp);

        tv_txt.setMovementMethod(LinkMovementMethod.getInstance());


        final List<MoveNewBean.ResultBean.ItemsBean.ImagesBean> images = bean.getImages();
//        int width = gridLayout.getMeasuredWidth();
        WindowManager windowManager = (WindowManager) ((Activity) getContext()).getSystemService(Context.WINDOW_SERVICE);
        int seernWidth = windowManager.getDefaultDisplay().getWidth();
        int childWidth = seernWidth / 3 - 40;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(childWidth, childWidth);
        gridLayout.setColumnCount(3);
        layoutParams.setMargins(10, 10, 10, 10);
        gridLayout.removeAllViews();
        if (images != null && images.size() != 0) {
            //图片
            for (int i = 0; i < images.size(); i++) {
                View view = View.inflate(getContext(), R.layout.show_image, null);
                ImageView iv = new ImageView(getContext());
                final MoveNewBean.ResultBean.ItemsBean.ImagesBean imagesBean = images.get(i);
                String thumb = imagesBean.getThumb();
                Glide.with(getContext()).load(thumb).asBitmap().into(iv);
                iv.setLayoutParams(layoutParams);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                final int finalI = i;
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ShowImageActivity.class);
                        int[] arr = {position, finalI, 1};
                        intent.putExtra("item", arr);
                        startActivity(intent);
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

    public String getSystemTime() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }
}


package com.saberrr.openchina.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
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
import com.saberrr.openchina.event.LoginBeanEvent;
import com.saberrr.openchina.ui.activity.MoveDetailActivity;
import com.saberrr.openchina.ui.activity.ShowImageActivity;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.GsonTools;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tt on 2017/4/3.
 */

public class MyMoveFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {

    private static final String TAG = "MyMoveFragment";

    private RecyclerView mRecyclerView;
    private FinalRecycleAdapter mAdapter;
    private boolean isUpdate = true;


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<MoveNewBean.ResultBean.ItemsBean> items;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {

        View view = View.inflate(getContext(), R.layout.fragment_move_new, null);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        setRecyclerView();

        EventBus.getDefault().register(this);
        return view;
    }

    private void initRequest() {
        try {
            String cookie = SpUtil.getString(getContext(), Constant.COOKIE, "");
            if (TextUtils.isEmpty(cookie)) {
                // TODO: 2017/4/6 跳转登陆界面
                ToastUtils.showToast("未登陆");
            }
            String userid = SpUtil.getString(getContext(), Constant.USERID, "");

            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            Request request = new Request.Builder().url("http://www.oschina.net/action/apiv2/tweets?authorId=" + userid).addHeader("cookie", cookie).get().build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                String string = response.body().string();

                try {
                    System.out.println("string" + string);
                    MoveNewBean moveNewBean = GsonTools.parseJsonToBean(string, MoveNewBean.class);
                    if (moveNewBean != null) {
                        items = moveNewBean.getResult().getItems();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showToast("网络错误");
                    return;
                }
            } else {
                ToastUtils.showToast("网络请求失败");
            }
            System.out.println("response" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(LoginBeanEvent e) {
        // TODO: 2017/4/5 更新ui
        getData();
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
        initRequest();
        updateData();
        return data;
    }

    private void updateData() {

        ThreadUtils.runMain(
                new Runnable() {
                    @Override
                    public void run() {

                        mAdapter.notifyDataSetChanged();
                    }
                }
        );

        try {
            data.addAll(items);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast("网络错误");
        }
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
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

    private void ShowView(FinalRecycleAdapter.ViewHolder holder, MoveNewBean.ResultBean.ItemsBean bean, final int position) {

        ImageView iv_icon = (ImageView) holder.getViewById(R.id.item_move_iv_icon);
        TextView tv_name = (TextView) holder.getViewById(R.id.item_move_tv_name);
        ImageView iv_official = (ImageView) holder.getViewById(R.id.iv_official);

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

        //名字
        String name = author.getName();
        SpannableString spann = new SpannableString(name);
//        spann.setSpan(new ImageSpan(R.mipmap));
        tv_name.setText(name);


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

        //文本内容
        String content = bean.getContent();

        Spanned spanned = Html.fromHtml(content);

        SpannableString msp = new SpannableString(spanned);

//        msp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tv_txt.setText(msp);
        tv_txt.setMovementMethod(LinkMovementMethod.getInstance());


        final List<MoveNewBean.ResultBean.ItemsBean.ImagesBean> images = bean.getImages();
        gridLayout.setColumnCount(3);
//        int width = gridLayout.getMeasuredWidth();
        WindowManager windowManager = (WindowManager) ((Activity) getContext()).getSystemService(Context.WINDOW_SERVICE);
        int seernWidth = windowManager.getDefaultDisplay().getWidth();
        int childWidth = seernWidth / 3 - 40;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(childWidth, childWidth);
        layoutParams.setMargins(10, 10, 10, 10);
        gridLayout.removeAllViews();
        if (images != null && images.size() > 0) {
            //图片
            for (int i = 0; i < images.size(); i++) {
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
                        int[] arr = {position, finalI, 3};
                        intent.putExtra("item", arr);
                        startActivity(intent);
                    }
                });
                gridLayout.addView(iv);
            }
        }

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

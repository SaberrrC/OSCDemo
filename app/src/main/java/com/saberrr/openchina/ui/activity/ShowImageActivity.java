package com.saberrr.openchina.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.MoveNewBean;
import com.saberrr.openchina.manager.netmanager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.GsonTools;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.ToastUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.saberrr.openchina.R.id.viewPager;

public class ShowImageActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private int[] mInts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        init();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final List<MoveNewBean.ResultBean.ItemsBean.ImagesBean> images = (List<MoveNewBean.ResultBean.ItemsBean.ImagesBean>) msg.obj;

            if (images == null) {
                ToastUtils.showToast("网络异常");
                return;
            }

            mViewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return images.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    ImageView imageView = new ImageView(ShowImageActivity.this);
                    MoveNewBean.ResultBean.ItemsBean.ImagesBean imagesBean = images.get(position);
                    String thumb = imagesBean.getThumb();
                    Glide.with(ShowImageActivity.this).load(thumb).asBitmap().into(imageView);
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                    container.addView(imageView);//记住这一步
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
            mViewPager.setCurrentItem(mInts[1]);
        }
    };

    private void init() {
//        int index = getIntent().getIntExtra("index", 0);
        mInts = getIntent().getIntArrayExtra("item");

        mViewPager = (ViewPager) findViewById(viewPager);
        mViewPager.setBackgroundColor(Color.BLACK);
        new Thread(new Runnable() {
            @Override
            public void run() {
                MoveNewBean dataBean = null;
                if (mInts[2] == 3) {
                    String cookie = SpUtil.getString(ShowImageActivity.this, Constant.COOKIE, "");
                    String userid = SpUtil.getString(ShowImageActivity.this, Constant.USERID, "");
                    try {
                        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                        Request request = new Request.Builder().url("http://www.oschina.net/action/apiv2/tweets?authorId=" + userid).addHeader("cookie", cookie).get().build();

                        Response response = okHttpClient.newCall(request).execute();
                        if (response.code() == 200) {

                            String string = response.body().string();
                            dataBean = GsonTools.parseJsonToBean(string, MoveNewBean.class);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        ToastUtils.showToast("网络错误");
                    }
                } else {
                    dataBean = JsonCacheManager.getInstance().getDataBean(Urls.MOVE_NEW + mInts[2], MoveNewBean.class);
                }
                if (dataBean == null) {
                    ToastUtils.showToast("空了");
                }
                List<MoveNewBean.ResultBean.ItemsBean.ImagesBean> images = dataBean.getResult().getItems().get(mInts[0]).getImages();
                Message msg = new Message();
                msg.obj = images;
                mHandler.sendMessage(msg);
            }
        }).start();

    }
}

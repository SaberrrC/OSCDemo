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
import com.saberrr.openchina.utils.ToastUtils;

import java.util.List;

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
                    container.addView(imageView);//记住这一步
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
            mViewPager.setCurrentItem(mInts[1]);
//            }
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
                MoveNewBean dataBean = JsonCacheManager.getInstance().getDataBean(Urls.MOVE_NEW + mInts[2], MoveNewBean.class);
                List<MoveNewBean.ResultBean.ItemsBean.ImagesBean> images = dataBean.getResult().getItems().get(mInts[0]).getImages();
                Message msg = new Message();
                msg.obj = images;
                mHandler.sendMessage(msg);
            }
        }).start();

    }
}

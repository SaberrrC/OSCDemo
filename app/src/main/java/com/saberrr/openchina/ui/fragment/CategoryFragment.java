package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.CategoryBean;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.XmlUtils;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuqi on 2017/4/1.
 */

public class CategoryFragment extends BaseFragment {
    private String TAG = "CategoryFragment";

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_category, null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public Object getData() {
       /* //CategoryBean categoryBean = JsonCacheManager.getInstance().getDataBean(Urls.CATEGORY, CategoryBean.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(Urls.CATEGORY).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String xml = response.body().string();
                    System.out.println(xml);
                    CategoryBean categoryBean = XmlUtils.toBean(CategoryBean.class, xml.getBytes());
                    String name = categoryBean.getSoftwareTypes().getSoftwareType().name;
                    Log.d(TAG, "run: " + name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

        return "";
    }



    @OnClick(R.id.button)
    public void onClick() {
        //ToastUtils.showToast("哈哈");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(Urls.CATEGORY).build();
                try {
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            ToastUtils.showToast("shibai");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String xml = response.body().string();
                            System.out.println(xml);
                            /*CategoryBean categoryBean = XmlUtils.toBean(CategoryBean.class, xml.getBytes());
                            String name = categoryBean.getSoftwareTypes().getSoftwareType().name;
                            Log.d(TAG, "run: " + name);*/
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

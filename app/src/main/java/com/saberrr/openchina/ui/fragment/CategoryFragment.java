package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.CategoryBean;
import com.saberrr.openchina.bean.SoftwareType;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.XmlUtils;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.name;

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
        init();
        return view;
    }

    private void init() {

    }

    @Override
    public Object getData() {
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
                    List<SoftwareType> softwareTypes = categoryBean.getSoftwareTypes();
                    for (int i = 0; i < softwareTypes.size(); i++) {
                        String name = softwareTypes.get(i).name;
                        System.out.println(name);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return "";
    }



}

package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.softwaredetailbean.Software;
import com.saberrr.openchina.bean.softwaredetailbean.SoftwareDetailBean;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.XmlUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuqi on 2017/4/5.
 */

public class SoftwareDetailFragment extends BaseFragment {
    @BindView(R.id.wv_softwaredetail)
    WebView mWvSoftwaredetail;
    @BindView(R.id.tv_softwaredetail_title)
    TextView mTvSoftwaredetailTitle;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.softwaredetail_layout, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {

    }

    @Override
    public Object getData() {
        Bundle bundle = getArguments();
        ArrayList<String> list = bundle.getStringArrayList("listName");
        int position = bundle.getInt("position");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Urls.SOFTWAREDETAIL + list.get(position)).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String xml = response.body().string();
//            System.out.println(xml);
            SoftwareDetailBean softwareDetailBean = XmlUtils.toBean(SoftwareDetailBean.class, xml.getBytes());
            final Software software = softwareDetailBean.getSoftware();
            String title = software.getTitle();

            mTvSoftwaredetailTitle.setText(software.getExtensionTitle()+title);
            ThreadUtils.runMain(new Runnable() {
                @Override
                public void run() {
                    mWvSoftwaredetail.loadDataWithBaseURL("",software.getBody(),"text/html","utf_8","");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}

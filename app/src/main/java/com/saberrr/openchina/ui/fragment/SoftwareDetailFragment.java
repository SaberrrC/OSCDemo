package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
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
import butterknife.OnClick;
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
    @BindView(R.id.tv_softwaredetail_author)
    TextView mTvSoftwaredetailAuthor;
    @BindView(R.id.tv_softwaredetail_agreement)
    TextView mTvSoftwaredetailAgreement;
    @BindView(R.id.tv_softwaredetail_language)
    TextView mTvSoftwaredetailLanguage;
    @BindView(R.id.tv_softwaredetail_system)
    TextView mTvSoftwaredetailSystem;
    @BindView(R.id.tv_softwaredetail_time)
    TextView mTvSoftwaredetailTime;
    private String mDownloadUrl;
    private String mDocumentUrl;
    private String mHomepageUrl;

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
            mDownloadUrl = software.getDownload();
            mHomepageUrl = software.getHomepage();
            mDocumentUrl = software.getDocument();

            ThreadUtils.runMain(new Runnable() {
                @Override
                public void run() {
                    mTvSoftwaredetailTitle.setText(software.getExtensionTitle() + software.getTitle());
                    mWvSoftwaredetail.loadDataWithBaseURL("", software.getBody(), "text/html", "utf_8", "");
                    String name = software.getName();
                    if (!TextUtils.isEmpty(name)) {
                        mTvSoftwaredetailAuthor.setVisibility(View.VISIBLE);
                        mTvSoftwaredetailAuthor.setText("软件作者:" + name);
                    }

                    String license = software.getLicense();
                    if (!TextUtils.isEmpty(license)) {
                        mTvSoftwaredetailAgreement.setVisibility(View.VISIBLE);
                        mTvSoftwaredetailAgreement.setText("开源协议:" + license);
                    }

                    String language = software.getLanguage();
                    if (!TextUtils.isEmpty(language)) {
                        mTvSoftwaredetailLanguage.setVisibility(View.VISIBLE);
                        mTvSoftwaredetailLanguage.setText("开发语言:" + language);
                    }
                    String os = software.getOs();
                    if (!TextUtils.isEmpty(os)) {
                        mTvSoftwaredetailSystem.setVisibility(View.VISIBLE);
                        mTvSoftwaredetailSystem.setText("操作系统:" + os);
                    }

                    String recordtime = software.getRecordtime();
                    if (!TextUtils.isEmpty(recordtime)) {
                        mTvSoftwaredetailTime.setVisibility(View.VISIBLE);
                        mTvSoftwaredetailTime.setText("收录时间:" + recordtime);
                    }

                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }



    @OnClick({R.id.bt_softwareTop, R.id.bt_softwareWord, R.id.bt_softwareDown})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_softwareTop:
                WebView webView = new WebView(getContext());
                webView.loadUrl(mHomepageUrl);
                break;
            case R.id.bt_softwareWord:
                WebView webView1 = new WebView(getContext());
                webView1.loadUrl(mDocumentUrl);
                break;
            case R.id.bt_softwareDown:
                WebView webView2 = new WebView(getContext());
                webView2.loadUrl(mDownloadUrl);
                break;
        }
    }
}

package com.saberrr.openchina.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.saberrr.openchina.R;
import com.saberrr.openchina.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 丁银晨 on 2017/4/2.
 */

public class BlogDetailsFragment extends BaseFragment {
    @BindView(R.id.wb_bolgdetails)
    WebView mWbBolgdetails;
    private String mUrl;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        Bundle bundle = getArguments();
        mUrl = bundle.getString(Constant.BLOGDETAILSFRAGMENT.HREF);
        View view = View.inflate(getContext(), R.layout.fragment_blogdatails, null);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
        WebSettings settings = mWbBolgdetails.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        mWbBolgdetails.loadUrl(mUrl);
        mWbBolgdetails.setWebViewClient(new WebViewClient(){
            //网页加载前
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //网页加载后
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
//        mWbBolgdetails.setWebChromeClient(new WebChromeClient(){
//            //网页的加载进度
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//               // System.out.println("newProgress: " + newProgress);
//            }
//
//            //接收网页标题
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                //System.out.println("title: " + title);
//            }
//        });
    }

    @Override
    public Object getData() {
        return "";
    }

}

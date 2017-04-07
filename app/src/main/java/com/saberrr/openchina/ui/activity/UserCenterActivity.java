package com.saberrr.openchina.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.saberrr.openchina.R;
import com.saberrr.openchina.utils.Constant;

public class UserCenterActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(Constant.Url);

        setContentView(R.layout.activity_user_center);
        mWebView = (WebView) findViewById(R.id.wv_contnet);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(stringExtra);


    }
}

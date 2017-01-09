package com.graypn.cmmon.view.webpage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.graypn.cmmon.R;
import com.graypn.cmmon.base.ui.activity.BaseActivity;
import com.graypn.cmmon.utils.ToastUtils;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by graypn on 16/6/6.
 */
public class WebActivity extends BaseActivity {

    public static final String PAGE_TYPE = "PAGE_TYPE";
    public static final String PAGE_TITLE = "PAGE_TITLE";
    public static final String PAGE_CONTENT = "PAGE_CONTENT";
    public static final String PAGE_URL = "PAGE_URL";

    public static final int PAGE_TYPE_DATA = 0;
    public static final int PAGE_TYPE_URL = 1;

    private WebView wvWebPage;
    private ImageButton ibTopbarLeft;
    private TextView tvToptitle;

    private int pageType;
    private String pageTitle;
    private String pageContent;
    private String pageUrl;

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        pageType = getIntent().getIntExtra(PAGE_TYPE, PAGE_TYPE_URL);
        pageTitle = getIntent().getStringExtra(PAGE_TITLE);
        pageContent = getIntent().getStringExtra(PAGE_CONTENT);
        pageUrl = getIntent().getStringExtra(PAGE_URL);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_webview);
        findViewById();
        initWebView();
        ibTopbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // ButterKnife 无法在 Library 中使用
    private void findViewById() {
        wvWebPage = (WebView) findViewById(R.id.wv_web_page);
        ibTopbarLeft = (ImageButton) findViewById(R.id.ib_topbar_left);
        tvToptitle = (TextView) findViewById(R.id.tv_toptitle);
    }

    @Override
    protected void initData() {
        tvToptitle.setText(pageTitle);
        switch (pageType) {
            case PAGE_TYPE_DATA:
                if (!TextUtils.isEmpty(pageContent)) {
                    wvWebPage.loadData(pageContent, "text/html; charset=UTF-8", null);
                }
                break;
            case PAGE_TYPE_URL:
                if (!TextUtils.isEmpty(pageUrl)) {
                    wvWebPage.loadUrl(pageUrl);
                }
                break;
        }


    }

    private void initWebView() {
        WebSettings settings = wvWebPage.getSettings();
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadWithOverviewMode(true);
//        settings.setTextZoom(400);
//        wvNewsDetail.addJavascriptInterface(new JavaScriptInterface(NewsDetailActivity.this), "js");
        wvWebPage.setWebViewClient(new WebViewClient() {
            @Override
            // 防止加载网页时调起系统浏览器
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //TODO 加载中的布局
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO 加载中的布局 消失
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebViewClient.a a) {
                super.onReceivedError(webView, webResourceRequest, a);
                ToastUtils.showToast(WebActivity.this, "加载失败，请检查网络");
            }
        });

    }
}

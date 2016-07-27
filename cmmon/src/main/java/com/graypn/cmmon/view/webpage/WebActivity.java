package com.graypn.cmmon.view.webpage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.graypn.cmmon.R;
import com.graypn.cmmon.base.ui.activity.BaseActivity;
import com.graypn.cmmon.net.GlobleNetApi;
import com.graypn.cmmon.utils.ToastUtils;

import butterknife.ButterKnife;


/**
 * Created by graypn on 16/6/6.
 */
public class WebActivity extends BaseActivity {

    public static String PAGE_TITLE = "PAGE_TITLE";
    public static String PAGE_CONTENT = "PAGE_CONTENT";
    public static String PAGE_EDIT_TIME = "PAGE_EDIT_TIME";

    private WebView wvWebPage;
    private ImageButton ibTopbarLeft;
    private TextView tvToptitle;

    private String pageTitle;
    private String pageContent;
    private String pageDate;

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        pageTitle = getIntent().getStringExtra(PAGE_TITLE);
        pageContent = getIntent().getStringExtra(PAGE_CONTENT);
        pageDate = getIntent().getStringExtra(PAGE_EDIT_TIME);
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

    private void findViewById() {
        wvWebPage = (WebView) findViewById(R.id.wv_web_page);
        ibTopbarLeft = (ImageButton) findViewById(R.id.ib_topbar_left);
        tvToptitle = (TextView) findViewById(R.id.tv_toptitle);
    }

    @Override
    protected void initData() {
        tvToptitle.setText(pageTitle);
        if (!TextUtils.isEmpty(pageContent)) {
            wvWebPage.loadData(GlobleNetApi.getNewsHtml(pageTitle, pageDate, pageContent), "text/html; charset=UTF-8", null);
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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //TODO 加载中的布局
//                ToastUtils.showProgressToast(NewsDetailActivity.this, true);
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO 加载中的布局 消失
//                ToastUtils.dismissProgressToast();
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                ToastUtils.showToast(WebActivity.this, "加载失败，请检查网络");
                super.onReceivedError(view, request, error);
            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

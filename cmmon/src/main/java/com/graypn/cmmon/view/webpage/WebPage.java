package com.graypn.cmmon.view.webpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.graypn.cmmon.R;
import com.graypn.cmmon.base.ui.page.BasePage;
import com.graypn.cmmon.net.GlobleNetApi;
import com.graypn.cmmon.utils.ToastUtils;

/**
 * Created by graypn on 16/6/3.
 */
public class WebPage extends BasePage {

    public static String PAGE_TITLE = "PAGE_TITLE";
    public static String PAGE_CONTENT = "PAGE_CONTENT";
    public static String PAGE_EDIT_TIME = "PAGE_EDIT_TIME";

    private WebView wvWebPage;

    private String pageContent;

    public WebPage(Context context, Bundle bundle) {
        super(context, bundle);
    }

    @Override
    protected void initVariables(Bundle bundle) {
        pageContent = bundle.getString(PAGE_CONTENT);
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        View rootView = inflater.inflate(R.layout.layout_webview, null);
        findViewById(rootView);
        initWebView();
        return rootView;
    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(pageContent)) {
            wvWebPage.loadData(GlobleNetApi.getWebHtml(pageContent), "text/html; charset=UTF-8", null);
        }
    }

    private void findViewById(View rootView) {
        wvWebPage = (WebView) rootView.findViewById(R.id.wv_web_page);
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
                ToastUtils.showToast(getContext(), "加载失败，请检查网络");
                super.onReceivedError(view, request, error);
            }

        });

    }
}

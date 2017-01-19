package com.graypn.cmmon.view.webpage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.graypn.cmmon.R;
import com.graypn.cmmon.base.ui.activity.BaseActivity;
import com.graypn.cmmon.utils.ScreenUtils;
import com.graypn.cmmon.utils.ToastUtils;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by graypn on 16/6/6.
 */
public class WebPopupActivity extends BaseActivity {

    public static final String PAGE_TYPE = "PAGE_TYPE";
    public static final String PAGE_CONTENT = "PAGE_CONTENT";
    public static final String PAGE_URL = "PAGE_URL";

    public static final int PAGE_TYPE_DATA = 0;
    public static final int PAGE_TYPE_URL = 1;

    private RelativeLayout mRootView;
    private WebView wvWebPage;
    private ImageView mBtnBcak;

    private int pageType;
    private String pageContent;
    private String pageUrl;

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        pageType = getIntent().getIntExtra(PAGE_TYPE, PAGE_TYPE_URL);
        pageContent = getIntent().getStringExtra(PAGE_CONTENT);
        pageUrl = getIntent().getStringExtra(PAGE_URL);
    }

    @Override
    protected void initViews() {
        int centerViewWidth = (int) (ScreenUtils.getScreenWidth(this) * 0.9);
        int centerViewHeight = (int) (ScreenUtils.getScreenHeight(this) * 0.84);
        int bottomViewHeight = ScreenUtils.dp2px(this, getResources().getDimension(R.dimen.DIP_18));
        int wvWebPageHeight = centerViewHeight - bottomViewHeight;

        mRootView = new RelativeLayout(this);
        RelativeLayout.LayoutParams rootViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mRootView.setLayoutParams(rootViewParams);

        // CenterView
        LinearLayout mCenterView = new LinearLayout(this);
        mCenterView.setOrientation(LinearLayout.VERTICAL);

        RelativeLayout.LayoutParams centerViewParams = new RelativeLayout.LayoutParams(centerViewWidth,
                centerViewHeight);
        centerViewParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mCenterView.setLayoutParams(centerViewParams);

        //
        LayoutInflater inflater = LayoutInflater.from(this);
        wvWebPage = (WebView) inflater.inflate(R.layout.layout_webview, null);

        LinearLayout.LayoutParams webViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, wvWebPageHeight);
        wvWebPage.setLayoutParams(webViewParams);
        initWebViewSettings();

        View bottomView = inflater.inflate(R.layout.layout_webpopup_bottom, null);

        bottomView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                bottomViewHeight));

        mBtnBcak = (ImageView) bottomView.findViewById(R.id.btn_back);
        mBtnBcak.setEnabled(false);
        mBtnBcak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                wvWebPage.goBack();
            }
        });
        ImageView btnRefresh = (ImageView) bottomView.findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                wvWebPage.reload();
            }
        });

        ImageView btnClose = (ImageView) bottomView.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mCenterView.addView(wvWebPage);
        mCenterView.addView(bottomView);

        mRootView.addView(mCenterView);
        setContentView(mRootView);
    }

    @Override
    protected void initData() {
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

    private void initWebViewSettings() {
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
                if (wvWebPage.canGoBack()) {
                    mBtnBcak.setImageResource(R.mipmap.ic_webpopup_back);
                    mBtnBcak.setClickable(true);
                    mBtnBcak.setEnabled(true);
                } else {
                    mBtnBcak.setImageResource(R.mipmap.ic_webpopup_back_unenable);
                    mBtnBcak.setClickable(false);
                    mBtnBcak.setEnabled(false);
                }
            }

            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebViewClient.a a) {
                super.onReceivedError(webView, webResourceRequest, a);
                ToastUtils.showToast(WebPopupActivity.this, "加载失败，请检查网络");
            }
        });

    }
}

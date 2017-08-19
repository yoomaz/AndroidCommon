package com.graypn.android_common.web;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ZhuLei on 2017/1/6.
 * Email: zhuleineuq@gmail.com
 */

public class WebViewUtils {

    public static void launchWebActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WebActivity.PAGE_TITLE, title);
        intent.putExtra(WebActivity.PAGE_URL, url);
        context.startActivity(intent);
    }

    public static void launchWebPopupActivity(Context context, String url) {
        Intent intent = new Intent(context, WebPopupActivity.class);
        intent.putExtra(WebActivity.PAGE_URL, url);
        context.startActivity(intent);
    }
}

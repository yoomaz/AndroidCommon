package com.graypn.cmmon.utils;

import android.content.Context;
import android.content.Intent;

import com.graypn.cmmon.view.webpage.WebActivity;

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
}

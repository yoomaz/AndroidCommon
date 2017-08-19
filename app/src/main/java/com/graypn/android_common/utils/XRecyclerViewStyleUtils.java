package com.graypn.android_common.utils;

import com.graypn.cmmon.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Wzs on 2016/5/27.
 */
public class XRecyclerViewStyleUtils {
    public static void applyStyle(XRecyclerView xRecyclerView) {
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        xRecyclerView.setArrowImageView(R.mipmap.ic_arrow_refreash);
    }
}

package com.graypn.cmmon.cache;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 图片缓存管理：依赖 Glide 进行实现
 * Created by graypn on 16/1/7.
 */
public class ImageCacheManager {

    private static Context mContext;

    private ImageCacheManager() {
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static void loadImage(String url, ImageView imageView) {
        if (mContext != null) {
            Glide.with(mContext).load(url).into(imageView);
        }
    }

    public static void loadImage(String url, int defImg, ImageView imageView) {
        if (url == null || url.equals("")) {
            Glide.with(mContext).load(defImg).into(imageView);
        } else {
            if (mContext != null) {
                Glide.with(mContext).load(url).placeholder(defImg).into(imageView);
            }
        }

    }

    public static void loadImage(int resourceId, ImageView imageView) {
        if (mContext != null) {
            Glide.with(mContext).load(resourceId).into(imageView);
        }
    }

}

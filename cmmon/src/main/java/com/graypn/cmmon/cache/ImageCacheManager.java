package com.graypn.cmmon.cache;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by graypn on 16/1/7.
 * <p/>
 * 依赖 Picasso 进行实现
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
            Picasso.with(mContext).load(url).into(imageView);
        }
    }

    public static void loadImage(String url, int defImg, ImageView imageView) {
        if(url == null || url.equals("")){
            Picasso.with(mContext).load(defImg).into(imageView);
        }else{
            if (mContext != null) {
                Picasso.with(mContext).load(url).placeholder(defImg).into(imageView);
            }
        }

    }

    public static void loadImage(int resourceId, ImageView imageView) {
        if (mContext != null) {
            Picasso.with(mContext).load(resourceId).into(imageView);
        }
    }

}

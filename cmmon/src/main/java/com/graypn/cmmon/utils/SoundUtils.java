package com.graypn.cmmon.utils;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by ZhuLei on 2016/9/28.
 * Email: zhuleineuq@gmail.com
 */

public class SoundUtils {

    private static SoundPool mPool;

    public static void play(int voiceID) {
        if (mPool != null) {
            mPool.play(voiceID, 1, 1, 0, 0, 1);
        }
    }

    public static int load(Context context, int resId) {
        if (mPool == null) {
            mPool = initSoundPool();
        }
        //load的返回值是一个int类的值：音频的id，在SoundPool的play()方法中加入这个id就能播放这个音频
        return mPool.load(context, resId, 1);
    }

    private static SoundPool initSoundPool() {
        /**
         * 21版本后，SoundPool的创建发生很大改变
         */
        //判断系统sdk版本，如果版本超过21，调用第一种
        if (Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams(2);//传入音频数量
            //AudioAttributes是一个封装音频各种属性的方法
            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);//设置音频流的合适的属性
            builder.setAudioAttributes(attrBuilder.build());//加载一个AudioAttributes
            mPool = builder.build();
        } else {
            mPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }
        return mPool;
    }
}

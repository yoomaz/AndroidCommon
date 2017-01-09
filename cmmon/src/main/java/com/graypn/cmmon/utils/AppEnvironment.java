package com.graypn.cmmon.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 用来获取 App 运行环境各种参数，可用发送数据给服务端等
 * 需要在首个开启的 Activity 中进行初始化操作
 * <p>
 * Created by ZhuLei on 2016/11/28.
 * Email: zhuleineuq@gmail.com
 */

public class AppEnvironment {

    public static final String OSID = "android";
    public static final String APPID = "100IME";

    private Context mContext;

    private String mOSID;
    private String mPackagePath;
    private String mVersionName;
    private int mVersionCode;
    private String mIMEI;
    private String mIMSI;
    private String mMacAddress;

    public AppEnvironment(Context context) {
        if (context == null) {
            throw new NullPointerException("CONTEXT SHOULD NOT BE NULL");
        }
        mContext = context;

        mOSID = AppEnvironment.OSID;
        try {
            String pkgName = context.getPackageName();
            PackageInfo appInfo = context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_META_DATA);
            mPackagePath = appInfo.applicationInfo.sourceDir;
            mVersionCode = appInfo.versionCode;
            mVersionName = appInfo.versionName;
        } catch (Exception e) {
            mVersionCode = 0;
            mVersionName = "1.1.0";
        }
    }

    /**
     * 获取当前 App 名称
     */
    public String getAppId() {
        return AppEnvironment.APPID;
    }

    /**
     * 获取当前操作系统名称
     */
    public String getOSID() {
        return mOSID;
    }

    /**
     * 获取当前APP版本名称
     */
    public String getVersionName() {
        return mVersionName;
    }

    /**
     * 获取当前APP版本号
     */
    public int getVersionCode() {
        return mVersionCode;
    }

    /**
     * 获取当前APP包路径
     */
    public String getPackagePath() {
        return mPackagePath;
    }

    /**
     * 获取手机 IMEI
     * 在使用时读取，防止初始化时读不到，不过多次获取貌似也有信号崩溃或其他定制反馈的问题，后续可考虑增加重试次数
     */
    public String getIMEI() {
        if (!TextUtils.isEmpty(mIMEI)) {
            return mIMEI;
        }
        mIMEI = getTelephonyManager().getDeviceId();
        return mIMEI;
    }

    /**
     * 获取手机 IMSI
     * 在使用时读取，防止初始化时读不到，不过多次获取貌似也有信号崩溃或其他定制反馈的问题，后续可考虑增加重试次数
     */
    public String getIMSI() {
        if (TextUtils.isEmpty(mIMSI)) {
            mIMSI = getTelephonyManager().getSubscriberId();
        }
        return mIMSI;
    }

    /**
     * 获取手机 Mac 地址
     */
    public String getLocalMacAddress() {
        if (TextUtils.isEmpty(mMacAddress)) {
            try {
                WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
                if (info != null) {
                    mMacAddress = info.getMacAddress();
                }
            } catch (Throwable t) {
            }
        }
        return mMacAddress;
    }

    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    }

}

package com.graypn.cmmon.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * 网络检测工具类
 * <p/>
 * need  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 * <p/>
 * update by graypn on 17/8/21
 */
public class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    public enum NetType {
        None(1),
        Mobile(2),
        Wifi(4),
        Other(8);

        NetType(int value) {
            this.value = value;
        }

        public int value;
    }

    public enum MobileNetType {
        UnKnown(-1),
        Net2G(2),
        Net3G(3),
        Net4G(4);

        MobileNetType(int value) {
            this.value = value;
        }

        public int value;
    }

    /**
     * 判断网络连接是否有效（此时可传输数据）。
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = getConnectivityManager(context);
        if (connectivityManager != null) {
            NetworkInfo net = connectivityManager.getActiveNetworkInfo();
            if (net != null && net.isConnected()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是WIFI连接
     */
    public static boolean isWifiConnected(Context context) {
        return getConnectedType(context) == NetType.Wifi;
    }

    /**
     * 是否是移动连接
     */
    public static boolean isMobileConnected(Context context) {
        return getConnectedType(context) == NetType.Mobile;
    }

    /**
     * 获取网络的类型
     */
    private static NetType getConnectedType(Context context) {
        ConnectivityManager connectivityManager = getConnectivityManager(context);
        if (connectivityManager != null) {
            NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
            if (net != null) {
                switch (net.getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        return NetType.Wifi;
                    case ConnectivityManager.TYPE_MOBILE:
                        return NetType.Mobile;
                    default:
                        return NetType.Other;
                }
            }
        }
        return NetType.None;
    }

    /**
     * 检测网络是否为可用状态
     */
    public static boolean isNetAvailable(Context context) {
        return isWifiAvailable(context) || (isMobileAvailable(context) && isMobileEnabled(context));
    }

    /**
     * 判断是否有可用状态的Wifi，以下情况返回false：
     * 1. 设备wifi开关关掉;
     * 2. 已经打开飞行模式；
     * 3. 设备所在区域没有信号覆盖；
     * 4. 设备在漫游区域，且关闭了网络漫游。
     *
     * @return boolean wifi为可用状态（不一定成功连接，即Connected）即返回ture
     */
    public static boolean isWifiAvailable(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.getType() == ConnectivityManager.TYPE_WIFI) {
                    return net.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 判断有无可用状态的移动网络，注意关掉设备移动网络直接不影响此函数。
     * 也就是即使关掉移动网络，那么移动网络也可能是可用的(彩信等服务)，即返回true。
     * 以下情况它是不可用的，将返回false：
     * 1. 设备打开飞行模式；
     * 2. 设备所在区域没有信号覆盖；
     * 3. 设备在漫游区域，且关闭了网络漫游。
     *
     * @return boolean
     */
    public static boolean isMobileAvailable(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return net.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 设备是否打开移动网络开关
     *
     * @return boolean 打开移动网络返回true，反之false
     */
    public static boolean isMobileEnabled(Context context) {
        try {
            Method getMobileDataEnabledMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled");
            getMobileDataEnabledMethod.setAccessible(true);
            return (Boolean) getMobileDataEnabledMethod.invoke(getConnectivityManager(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 反射失败，默认开启
        return true;
    }

    /**
     * 获取当前移动网络类型
     * <p>
     * <p>
     * GPRS    2G(2.5) General Packet Radia Service 114kbps
     * EDGE    2G(2.75G) Enhanced Data Rate for GSM Evolution 384kbps
     * UMTS    3G WCDMA 联通3G Universal Mobile Telecommunication System 完整的3G移动通信技术标准
     * CDMA    2G 电信 Code Division Multiple Access 码分多址
     * EVDO_0  3G (EVDO 全程 CDMA2000 1xEV-DO) Evolution - Data Only (Data Optimized) 153.6kps - 2.4mbps 属于3G
     * EVDO_A  3G 1.8mbps - 3.1mbps 属于3G过渡，3.5G
     * 1xRTT   2G CDMA2000 1xRTT (RTT - 无线电传输技术) 144kbps 2G的过渡,
     * HSDPA   3.5G 高速下行分组接入 3.5G WCDMA High Speed Downlink Packet Access 14.4mbps
     * HSUPA   3.5G High Speed Uplink Packet Access 高速上行链路分组接入 1.4 - 5.8 mbps
     * HSPA    3G (分HSDPA,HSUPA) High Speed Packet Access
     * IDEN    2G Integrated Dispatch Enhanced Networks 集成数字增强型网络 （属于2G，来自维基百科）
     * EVDO_B  3G EV-DO Rev.B 14.7Mbps 下行 3.5G
     * LTE     4G Long Term Evolution FDD-LTE 和 TDD-LTE , 3G过渡，升级版 LTE Advanced 才是4G
     * EHRPD   3G CDMA2000向LTE 4G的中间产物 Evolved High Rate Packet Data HRPD的升级
     * HSPAP   3G HSPAP 比 HSDPA 快些
     */
    public static MobileNetType getNetworkType(Context context) {
        if (isMobileConnected(context)) {
            TelephonyManager telephonyManager = getTelephonyManager(context);
            if (telephonyManager != null) {
                int telType = getTelephonyManager(context).getNetworkType();
                switch (telType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        return MobileNetType.Net2G;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        return MobileNetType.Net3G;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return MobileNetType.Net4G;
                }
            }
        }
        return MobileNetType.UnKnown;
    }

    /**
     * 获取ConnectivityManager
     * <p>
     * 在部分手机上，获取系统服务可能会出异常
     */
    private static ConnectivityManager getConnectivityManager(Context context) {
        ConnectivityManager connectivityManager = null;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connectivityManager;
    }

    /**
     * 获取TelephonyManager
     * <p>
     * 在部分手机上，获取系统服务可能会出异常
     */
    private static TelephonyManager getTelephonyManager(Context context) {
        TelephonyManager telephonyManager = null;
        try {
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return telephonyManager;
    }

    public static void testCurrentNetInfo(Context context) {
        Log.i(TAG, "当前网络是否连接: " + isConnected(context));

        Log.i(TAG, "是否是 WIFI: " + isWifiConnected(context));

        Log.i(TAG, "是否是 移动网络: " + isMobileConnected(context));

        Log.i(TAG, "当前移动网络类型: " + getNetworkType(context));
    }

}

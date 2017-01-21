package com.graypn.cmmon.permission;

/**
 * Created by ZhuLei on 2017/1/20.
 * Email: zhuleineuq@gmail.com
 */

public interface OnPermissionListener {

    void onPermissionGranted();

    void onPermissionDenied();

    void onError();
}

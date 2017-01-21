package com.graypn.cmmon.permission;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.graypn.cmmon.base.ui.activity.BaseActivity;
import com.graypn.cmmon.view.dialog.CommonDialog;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

/**
 * Created by ZhuLei on 2017/1/20.
 * Email: zhuleineuq@gmail.com
 */

public class PermissionHelper {

    public static void requestPermission(final BaseActivity activity, final OnPermissionListener permissionListener, final String permission, final String dialogTitle, final String dialogMessage) {
        Dexter.withActivity(activity)
                .withPermission(permission)
                .withListener(new CompositePermissionListener(new CommonPermissionListener(activity, permissionListener, dialogTitle, dialogMessage)))
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        permissionListener.onError();
                    }
                })
                .check();
    }

    @TargetApi(23)
    public static boolean checkPermission(Context context, String permission) {
        boolean status = false;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            status = true;
        } else {
            try {
                status = context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return status;
    }
}

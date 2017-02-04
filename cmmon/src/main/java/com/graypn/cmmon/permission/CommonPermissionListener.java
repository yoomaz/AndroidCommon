package com.graypn.cmmon.permission;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;

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

public class CommonPermissionListener implements PermissionListener {

    private final BaseActivity activity;
    private OnPermissionListener permissionListener;
    private String dialogTitle;
    private String dialogMessage;

    public CommonPermissionListener(BaseActivity activity, OnPermissionListener listener, String title, String message) {
        this.activity = activity;
        permissionListener = listener;
        dialogTitle = title;
        dialogMessage = message;
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {
        permissionListener.onPermissionGranted();
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {
        permissionListener.onPermissionDenied();
    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
                                                   PermissionToken token) {
        showPermissionRationale(token);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showPermissionRationale(final PermissionToken token) {
        new CommonDialog.AlertBuilder(activity)
                .setTitle(dialogTitle)
                .setMessage(dialogMessage)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        token.continuePermissionRequest();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        token.cancelPermissionRequest();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                    }
                })
                .build()
                .show();
    }
}

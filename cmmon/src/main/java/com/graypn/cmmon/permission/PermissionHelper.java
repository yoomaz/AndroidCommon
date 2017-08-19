package com.graypn.cmmon.permission;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import com.graypn.cmmon.base.ui.activity.BaseActivity;
import com.graypn.cmmon.view.dialog.CommonDialogHelper;
import com.graypn.permissionmaster.PermissionMaster;
import com.graypn.permissionmaster.listener.PermissionListener;

/**
 * Created by ZhuLei on 2017/1/20.
 * Email: zhuleineuq@gmail.com
 */

public class PermissionHelper {

    public static void requestPermission(final BaseActivity activity, final PermissionListener permissionListener, final String permission, final String dialogTitle, final String dialogMessage) {
        CommonDialogHelper.getCommonAlertDialog(activity, dialogTitle, dialogMessage, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PermissionMaster.withActivity(activity)
                        .withPermission(permission)
                        .withListener(permissionListener)
                        .check();
            }
        }, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

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

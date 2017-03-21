package com.graypn.cmmon.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by ZhuLei on 2017/3/17.
 * Email: zhuleineuq@gmail.com
 */

public class CommonDialogHelper {

    public static Dialog getCommonAlertDialog(Context context, String title, String content,
                                              String positiveBtnText, DialogInterface.OnClickListener positiveBtnListener,
                                              String negativeBtnText, DialogInterface.OnClickListener negativeBtnListener) {
        return new CommonDialog.AlertBuilder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(positiveBtnText, positiveBtnListener)
                .setNegativeButton(negativeBtnText, negativeBtnListener)
                .build();
    }
}

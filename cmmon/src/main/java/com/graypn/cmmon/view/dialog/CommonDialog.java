package com.graypn.cmmon.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.graypn.cmmon.R;

/**
 * Created by graypn on 15/12/21.
 */
public class CommonDialog extends Dialog {

    private static final int TYPE_ALERT = 0;
    private static final int TYPE_PROGRESS = 1;

    public CommonDialog(Context context) {
        this(context, 0);
    }

    public CommonDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class AlertBuilder extends Builder {

        private String mPositiveButtonText;
        private String mNegativeButtonText;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private OnDismissListener onDismissListener;

        public AlertBuilder(Context context, String title, String message) {
            super(context, title, message);
        }

        public AlertBuilder setPositiveButton(String text, OnClickListener positiveButtonClickListener) {
            this.mPositiveButtonText = text;
            this.positiveButtonClickListener = positiveButtonClickListener;
            return this;
        }

        public AlertBuilder setNegativeButtonClickListener(String text, OnClickListener negativeButtonClickListener) {
            this.mNegativeButtonText = text;
            this.negativeButtonClickListener = negativeButtonClickListener;
            return this;
        }

        public AlertBuilder setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        @Override
        public CommonDialog build() {
            final CommonDialog dialog = new CommonDialog(mContext, R.style.dialogStyle);
            dialog.setCanceledOnTouchOutside(false);
            View dialogView = inflater.inflate(R.layout.dialog_alert, null);
            if (!TextUtils.isEmpty(mTitle)) {
                ((TextView) dialogView.findViewById(R.id.dialog_title)).setText(mTitle);
            }
            if (!TextUtils.isEmpty(mMessage)) {
                ((TextView) dialogView.findViewById(R.id.dialog_msg)).setText(mMessage);
            }
            if (!TextUtils.isEmpty(mPositiveButtonText)) {
                ((Button) dialogView.findViewById(R.id.btn_confirm)).setText(mPositiveButtonText);
            }
            if (!TextUtils.isEmpty(mNegativeButtonText)) {
                ((Button) dialogView.findViewById(R.id.btn_cancel)).setText(mNegativeButtonText);
            }
            if (positiveButtonClickListener != null) {
                dialogView.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        dialog.dismiss();
                    }
                });
            } else {
                dialogView.findViewById(R.id.btn_confirm).setVisibility(View.GONE);
            }
            if (negativeButtonClickListener != null) {
                dialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        dialog.dismiss();
                    }
                });
            } else {
                dialogView.findViewById(R.id.btn_cancel).setVisibility(View.GONE);
            }
            if (onDismissListener != null) {
                dialog.setOnDismissListener(onDismissListener);
            }
            dialog.addContentView(dialogView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return dialog;
        }
    }

    public static class ProgressBuilder extends Builder {

        public ProgressBuilder(Context context, String title, String message) {
            super(context, title, message);
        }

        @Override
        public CommonDialog build() {
            final CommonDialog dialog = new CommonDialog(mContext, R.style.dialogStyle);
            dialog.setCanceledOnTouchOutside(false);
            View dialogView = inflater.inflate(R.layout.dialog_progress, null);
            if (!TextUtils.isEmpty(mTitle)) {
                ((TextView) dialogView.findViewById(R.id.dialog_title)).setText(mTitle);
            }
            if (!TextUtils.isEmpty(mMessage)) {
                ((TextView) dialogView.findViewById(R.id.dialog_msg)).setText(mMessage);
            }
            dialog.addContentView(dialogView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return dialog;
        }
    }

    public static abstract class Builder {
        protected Context mContext;
        protected String mTitle;
        protected String mMessage;
        protected LayoutInflater inflater;

        public Builder(Context context, String title, String message) {
            this.mContext = context;
            this.mTitle = title;
            this.mMessage = message;
            inflater = LayoutInflater.from(mContext);
        }

        public abstract CommonDialog build();
    }
}

package com.graypn.cmmon.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.graypn.cmmon.R;
import com.graypn.cmmon.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by graypn on 15/12/21.
 */
public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        this(context, 0);
    }

    public CommonDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class AlertBuilder extends Builder {
        // 要显示的Dialog
        private Dialog mDialog;
        // 顶部标题
        private String mTitle;
        // 中间信息
        private String mMessage;
        private String mPositiveButtonText;
        private String mNegativeButtonText;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private OnDismissListener onDismissListener;

        public AlertBuilder(Context context) {
            super(context);
        }

        public AlertBuilder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public AlertBuilder setMessage(String message) {
            mMessage = message;
            return this;
        }

        public AlertBuilder setPositiveButton(String text, OnClickListener positiveButtonClickListener) {
            this.mPositiveButtonText = text;
            this.positiveButtonClickListener = positiveButtonClickListener;
            return this;
        }

        public AlertBuilder setNegativeButton(String text, OnClickListener negativeButtonClickListener) {
            this.mNegativeButtonText = text;
            this.negativeButtonClickListener = negativeButtonClickListener;
            return this;
        }

        public AlertBuilder setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        @Override
        public Dialog build() {
            mDialog = new Dialog(mContext, R.style.dialogStyle);
            mDialog.setCanceledOnTouchOutside(false);
            Window mDialogWindow = mDialog.getWindow();
            mDialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            mDialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE);

            View dialogView = inflater.inflate(R.layout.dialog_alert, null);
            if (!TextUtils.isEmpty(mTitle)) {
                ((TextView) dialogView.findViewById(R.id.dialog_title)).setText(mTitle);
            }
            if (!TextUtils.isEmpty(mMessage)) {
                ((TextView) dialogView.findViewById(R.id.dialog_msg)).setText(mMessage);
            }
            if (!TextUtils.isEmpty(mPositiveButtonText)) {
                ((Button) dialogView.findViewById(R.id.btn_confirm)).setText(mPositiveButtonText);
                dialogView.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        positiveButtonClickListener.onClick(mDialog, DialogInterface.BUTTON_POSITIVE);
                        mDialog.dismiss();
                    }
                });
            }
            if (!TextUtils.isEmpty(mNegativeButtonText)) {
                ((Button) dialogView.findViewById(R.id.btn_cancel)).setText(mNegativeButtonText);
                dialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeButtonClickListener.onClick(mDialog, DialogInterface.BUTTON_NEGATIVE);
                        mDialog.dismiss();
                    }
                });
            } else {
                dialogView.findViewById(R.id.v_line).setVisibility(View.GONE);
                dialogView.findViewById(R.id.btn_cancel).setVisibility(View.GONE);
            }
            if (onDismissListener != null) {
                mDialog.setOnDismissListener(onDismissListener);
            }
            mDialog.addContentView(dialogView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return mDialog;
        }
    }

    public static class ListBuilder extends Builder {
        // 要显示的Dialog
        private Dialog mDialog;

        private String mTitle;
        private List<String> mItems;
        private DialogInterface.OnClickListener mOnClickListener;

        public ListBuilder(Context context) {
            super(context);
        }

        public ListBuilder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public ListBuilder setContentList(List<String> items, DialogInterface.OnClickListener onClickListener) {
            mItems = items;
            mOnClickListener = onClickListener;
            return this;
        }

        @Override
        public Dialog build() {
            mDialog = new Dialog(mContext, R.style.dialogStyle);
            mDialog.setCanceledOnTouchOutside(false);
            View dialogView = inflater.inflate(R.layout.dialog_list, null);
            if (!TextUtils.isEmpty(mTitle)) {
                ((TextView) dialogView.findViewById(R.id.dialog_title)).setText(mTitle);
            }
            if (mItems != null) {
                ListView listView = (ListView) dialogView.findViewById(R.id.lv_dialog_list);
                CommonDialogListAdapter adapter = new CommonDialogListAdapter(mContext, mItems);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (mOnClickListener != null) {
                            mOnClickListener.onClick(mDialog, position);
                            mDialog.dismiss();
                        }
                    }
                });
            }
            dialogView.findViewById(R.id.btn_cancel_list).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
            mDialog.addContentView(dialogView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return mDialog;
        }
    }

    public static class ProgressBuilder extends Builder {
        // 要显示的Dialog
        private Dialog mDialog;

        private String mTitle;
        private String mMessage;

        public ProgressBuilder(Context context) {
            super(context);
        }

        public ProgressBuilder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public ProgressBuilder setMessage(String message) {
            mMessage = message;
            return this;
        }

        @Override
        public Dialog build() {
            mDialog = new Dialog(mContext, R.style.dialogStyle);
            mDialog.setCanceledOnTouchOutside(false);
            View dialogView = inflater.inflate(R.layout.dialog_progress, null);
            if (!TextUtils.isEmpty(mTitle)) {
                ((TextView) dialogView.findViewById(R.id.dialog_title)).setText(mTitle);
            }
            if (!TextUtils.isEmpty(mMessage)) {
                ((TextView) dialogView.findViewById(R.id.dialog_msg)).setText(mMessage);
            }
            mDialog.addContentView(dialogView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return mDialog;
        }
    }

    private static abstract class Builder {
        protected Context mContext;
        protected LayoutInflater inflater;

        public Builder(Context context) {
            this.mContext = context;
            inflater = LayoutInflater.from(mContext);
        }

        public abstract Dialog build();
    }
}

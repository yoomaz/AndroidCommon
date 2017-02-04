package com.graypn.cmmon.view.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.graypn.cmmon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhuLei on 2017/2/4.
 * Email: zhuleineuq@gmail.com
 */

public class CommonDialogListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mItems;

    public CommonDialogListAdapter(Context context, List<String> mItems) {
        this.mContext = context;
        this.mItems = mItems;
    }

    @Override
    public int getCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mContext == null || mItems == null) return null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_list_item, null);
        TextView item = (TextView) view.findViewById(R.id.tv_dialog_list);
        String text = mItems.get(position);
        if (!TextUtils.isEmpty(text)) {
            item.setText(text);
        }
        return view;
    }
}

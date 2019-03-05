package com.twiceyuan.dropdownmenu.widget;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.twiceyuan.dropdownmenu.R;

import java.util.List;

public class DropListAdapter extends BaseDropListAdapter<String, DropListAdapter.ViewHolder> {

    DropListAdapter(List<String> strings) {
        super(strings);
    }

    @Override
    protected ViewHolder createHolder(View rootView) {
        return new ViewHolder(rootView);
    }

    @Override
    protected int itemLayoutId() {
        return R.layout.ddm_item_drop_list;
    }

    @Override
    protected void onBindSelected(String text, ViewHolder holder) {
        holder.mTextView.setTextColor(0xFF000000);
        holder.mTextView.setText(text);
        holder.mTextView.setTypeface(null, Typeface.BOLD);
    }

    @Override
    protected void onBindNormal(String s, ViewHolder holder) {
        holder.mTextView.setTextColor(0xFF444444);
        holder.mTextView.setText(s);
        holder.mTextView.setTypeface(null, Typeface.NORMAL);
    }

    static class ViewHolder {
        TextView mTextView;

        ViewHolder(View rootView) {
            mTextView = rootView.findViewById(android.R.id.text1);
        }
    }
}

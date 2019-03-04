package com.twiceyuan.dropdownmenu.widget;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twiceyuan.dropdownmenu.R;

import java.util.List;

public class DropListAdapter extends BaseAdapter {

    private final List<String> mStrings;

    private int selected = -1;

    public DropListAdapter(List<String> strings) {
        mStrings = strings;
    }

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }

    @Override
    public String getItem(int position) {
        return mStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mStrings.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.ddm_item_drop_list, null);
        }
        TextView text = convertView.findViewById(android.R.id.text1);
        text.setText(getItem(position));
        if (selected == position) {
            text.setTextColor(0xFF000000);
            text.setTypeface(null, Typeface.BOLD);
        } else {
            text.setTextColor(0xFF444444);
            text.setTypeface(null, Typeface.NORMAL);
        }

        return convertView;
    }
}

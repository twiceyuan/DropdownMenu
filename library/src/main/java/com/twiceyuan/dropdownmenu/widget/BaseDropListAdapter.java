package com.twiceyuan.dropdownmenu.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class BaseDropListAdapter<T, VH> extends BaseAdapter {

    private final List<T> mData;

    private int selected = -1;

    public BaseDropListAdapter(List<T> strings) {
        mData = strings;
    }

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    protected abstract VH createHolder(View rootView);

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).hashCode();
    }

    protected abstract int itemLayoutId();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), itemLayoutId(), null);
            holder = createHolder(convertView);
            convertView.setTag(holder);
        } else {
            //noinspection unchecked
            holder = (VH) convertView.getTag();
        }

        if (selected == position) {
            onBindSelected(getItem(position), holder);
        } else {
            onBindNormal(getItem(position), holder);
        }

        return convertView;
    }

    protected abstract void onBindSelected(T t, VH holder);

    protected abstract void onBindNormal(T t, VH holder);
}

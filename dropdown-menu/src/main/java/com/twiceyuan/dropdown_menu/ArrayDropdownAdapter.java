package com.twiceyuan.dropdown_menu;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by twiceYuan on 10/22/15.
 *
 * 默认 String 的适配器
 */
public class ArrayDropdownAdapter extends ArrayAdapter<String> implements DropdownAdapter {

    public ArrayDropdownAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ArrayDropdownAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ArrayDropdownAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    public ArrayDropdownAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ArrayDropdownAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    public ArrayDropdownAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public String getTitleString(int position) {
        return getItem(position);
    }
}

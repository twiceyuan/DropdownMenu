package com.twiceyuan.dropdown_menu;

import android.widget.ListAdapter;

/**
 * Created by twiceYuan on 10/22/15.
 *
 * 下拉菜单项适配器，要求实现选择后的文字映射
 */
public interface DropdownAdapter extends ListAdapter {

    String getTitleString(int position);
}

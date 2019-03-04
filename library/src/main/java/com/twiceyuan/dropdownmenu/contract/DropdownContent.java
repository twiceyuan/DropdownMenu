package com.twiceyuan.dropdownmenu.contract;

import android.view.View;

import com.twiceyuan.dropdownmenu.listener.OnChooseListener;

public interface DropdownContent<T> {
    View onCreateDropdownView(OnChooseListener<T> listener);
}

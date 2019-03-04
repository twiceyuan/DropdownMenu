package com.twiceyuan.dropdownmenu.contract;

import com.twiceyuan.dropdownmenu.listener.OnChooseListener;
import com.twiceyuan.dropdownmenu.listener.OnShowListener;
import com.twiceyuan.dropdownmenu.listener.OnStateChangeListener;

public interface DropdownHeader<T> extends OnChooseListener<T>, OnStateChangeListener {

    void setupShowListener(OnShowListener onShowListener);
}

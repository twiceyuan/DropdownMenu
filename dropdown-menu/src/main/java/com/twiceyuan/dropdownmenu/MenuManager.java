package com.twiceyuan.dropdownmenu;

import android.view.View;

import java.util.Collections;
import java.util.HashSet;

/**
 * Created by twiceYuan on 10/23/15.
 *
 * 弹出管理器，用于完成一些特定的操作，比如只允许单个 DropdownMenu 弹出
 */
public class MenuManager {

    /**
     * 同时只允许一个 DropdownMenu 弹出
     */
    public static void group(DropdownMenu... menus) {

        for (DropdownMenu menu : menus) {
            final HashSet<DropdownMenu> menuList = new HashSet<>();
            Collections.addAll(menuList, menus);
            menuList.remove(menu);
            menu.addOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (DropdownMenu m : menuList) m.collapse();
                }
            });
        }
    }
}

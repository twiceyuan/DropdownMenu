package com.twiceyuan.dropdownmenu;

import android.content.res.Resources;

public class Utils {

    public static int dp2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
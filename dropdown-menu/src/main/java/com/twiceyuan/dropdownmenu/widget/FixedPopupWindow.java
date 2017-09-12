package com.twiceyuan.dropdownmenu.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import java.lang.reflect.Method;

/**
 * Created by twiceYuan on 2017/9/12.
 * <p>
 * 解决 PopupWindow 在 7.0 以上的 bug
 */
public class FixedPopupWindow extends PopupWindow {

    public FixedPopupWindow(Context context) {
        super(context);
    }

    public FixedPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FixedPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FixedPopupWindow() {
    }

    public FixedPopupWindow(View contentView) {
        super(contentView);
    }

    public FixedPopupWindow(int width, int height) {
        super(width, height);
    }

    public FixedPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public FixedPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    @Override
    public void showAsDropDown(View anchor) {
        Class<?> popupwindow = PopupWindow.class;
        try {
            Method m2 = popupwindow.getDeclaredMethod("computeGravity");
            m2.setAccessible(true);
            int invoke = (int) m2.invoke(this);
            int gravity = Gravity.START | Gravity.TOP;
            if (invoke == gravity) {
                Rect rect = new Rect();
                anchor.getGlobalVisibleRect(rect);
                int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
                setHeight(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.showAsDropDown(anchor);
    }
}

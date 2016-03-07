package com.twiceyuan.dropdownmenu.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 字体图标
 */
public class FontIcon extends TextView {

    public FontIcon(Context context) {
        super(context);
        init();
    }

    public FontIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontIcon(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "icon.ttf");
        setTypeface(typeFace);
    }

    @SuppressWarnings("unused") public void setFontIconColor(int textColor) {
        this.setTextColor(textColor);
    }

    @SuppressWarnings("unused") public void setFontIconColor(ColorStateList colors) {
        this.setTextColor(colors);
    }
}

package com.twiceyuan.dropdownmenu.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.twiceyuan.dropdownmenu.R;
import com.twiceyuan.dropdownmenu.contract.DropdownHeader;
import com.twiceyuan.dropdownmenu.listener.OnShowListener;

public class TextViewHeader implements DropdownHeader<String> {

    private final TextView mTextView;

    public TextViewHeader(TextView textView) {
        mTextView = textView;
    }

    @Override
    public void onChoose(String text) {
        mTextView.setText(text);
    }

    @Override
    public void onChange(boolean isExpand) {
        Context context = mTextView.getContext();

        Resources resources = context.getResources();
        Drawable upIcon = resources.getDrawable(R.drawable.ddm_ic_arrow_up);

        Drawable downIcon = resources.getDrawable(R.drawable.ddm_ic_arrow_down);

        Drawable indicator = isExpand ? upIcon : downIcon;
        mTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, indicator, null);
    }

    @Override
    public void setupShowListener(OnShowListener onShowListener) {
        mTextView.setOnClickListener(onShowListener::onShow);
    }
}

package com.twiceyuan.dropdownmenu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.twiceyuan.dropdownmenu.contract.DropdownContent;
import com.twiceyuan.dropdownmenu.contract.DropdownHeader;
import com.twiceyuan.dropdownmenu.listener.OnChooseListener;

public class DropdownMenu<T> {

    private PopupWindow mPopupWindow;

    private OnChooseListener<T> mOnChooseListener;

    public static class Builder<T> {
        private DropdownHeader<T>  header;
        private DropdownContent<T> content;

        public Builder<T> header(DropdownHeader<T> header) {
            this.header = header;
            return this;
        }

        public Builder<T> content(DropdownContent<T> content) {
            this.content = content;
            return this;
        }

        public DropdownMenu<T> build() {
            return new DropdownMenu<T>(header, content);
        }
    }

    private DropdownMenu(DropdownHeader<T> header, DropdownContent<T> content) {

        OnChooseListener<T> onChooseListener = (result) -> {
            header.onChoose(result);
            mPopupWindow.dismiss();
            mOnChooseListener.onChoose(result);
        };

        View dropdownContent = content.onCreateDropdownView(onChooseListener);
        LinearLayout container = wrapShadowContainer(dropdownContent);

        mPopupWindow = new PopupWindow(
                container,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );

        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(() -> header.onChange(false));

        header.setupShowListener(v -> mPopupWindow.showAsDropDown(v));
    }

    public void setOnChooseListener(OnChooseListener<T> onChooseListener) {
        mOnChooseListener = onChooseListener;
    }

    private LinearLayout wrapShadowContainer(View dropdownContent) {
        LinearLayout container = new LinearLayout(dropdownContent.getContext());
        container.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        container.setOrientation(LinearLayout.VERTICAL);
        container.addView(dropdownContent);

        View shadowView = new View(dropdownContent.getContext());
        shadowView.setBackgroundResource(R.drawable.ddm_shadow);
        shadowView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                Utils.dp2px(16)
        ));

        container.addView(shadowView);
        return container;
    }
}

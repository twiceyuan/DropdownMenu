package com.twiceyuan.ddmsample.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twiceyuan.ddmsample.R;
import com.twiceyuan.dropdownmenu.contract.DropdownHeader;
import com.twiceyuan.dropdownmenu.listener.OnShowListener;

/**
 * 带动画指示器的头部
 */
public class AnimatedHeader implements DropdownHeader<String> {

    private final ViewGroup layout;
    private final TextView  title;
    private final ImageView indicator;

    public AnimatedHeader(ViewGroup layout) {
        this.layout = layout;
        title = (TextView) layout.getChildAt(0);
        indicator = (ImageView) layout.getChildAt(1);
    }

    @Override
    public void setupShowListener(OnShowListener onShowListener) {
        layout.setOnClickListener(v -> onShowListener.onShow(layout));
    }

    @Override
    public void onChoose(String result) {
        title.setText(result);
    }

    @Override
    public void onChange(boolean isExpand) {
        Resources resources = layout.getContext().getResources();

        Drawable upIcon = resources.getDrawable(R.drawable.ddm_ic_arrow_up);
        Drawable downIcon = resources.getDrawable(R.drawable.ddm_ic_arrow_down);

        Drawable indicatorIcon = isExpand ? upIcon : downIcon;

        ObjectAnimator animator = ObjectAnimator.ofFloat(
                indicator, "rotation", 0f, 180f
        );

        animator.setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                indicator.setRotation(0f);
                indicator.setImageDrawable(indicatorIcon);
            }
        });

        animator.start();
    }
}

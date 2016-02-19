package com.twiceyuan.dropdownmenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by twiceYuan on 10/21/15.
 *
 * 可以设置最大高度的 ListView
 */
public class FixedHeightListView extends ListView {

    private int mMaxHeight = 300;

    public FixedHeightListView(Context context) {
        super(context);
        init(context, null);
    }

    public FixedHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FixedHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FixedHeightListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void setMaxHeight(int maxHeight) {
        this.mMaxHeight = maxHeight;
    }

    private void init(@SuppressWarnings("UnusedParameters") Context context, AttributeSet attrs) {
        // 初始化属性
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.FixedHeightListView);
        mMaxHeight = attributes.getDimensionPixelOffset(R.styleable.FixedHeightListView_maxHeight, 300);
        attributes.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

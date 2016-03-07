package com.twiceyuan.dropdownmenu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.twiceyuan.dropdownmenu.FixedHeightListView;

/**
 * Created by twiceYuan on 2/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class CascadeView extends LinearLayout {

    private FixedHeightListView mListView1;
    private FixedHeightListView mListView2;

    public CascadeView(Context context) {
        super(context);
        init(context);
    }

    public CascadeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CascadeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setWeightSum(2);

        mListView1 = new FixedHeightListView(context);
        mListView2 = new FixedHeightListView(context);

        mListView1.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        mListView2.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        mListView2.setDivider(null);

        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);

        mListView1.setLayoutParams(params);
        mListView2.setLayoutParams(params);
        mListView1.setVerticalScrollBarEnabled(false);
        mListView2.setVerticalScrollBarEnabled(false);

        addView(mListView1);
        addView(mListView2);
    }

    /**
     * 设置左右比例
     *
     * @param left  左边占权重
     * @param right 右边占权重
     */
    public void setScale(int left, int right) {
        setWeightSum(left + right);
        LayoutParams leftParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, left);
        mListView1.setLayoutParams(leftParams);
        LayoutParams rightParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, right);
        mListView2.setLayoutParams(rightParams);
    }

    /**
     * 设置左侧适配器
     *
     * @param adapter 左侧适配器
     */
    public void setLeftAdapter(ListAdapter adapter) {
        mListView1.setAdapter(adapter);
    }

    /**
     * 设置右侧适配器
     *
     * @param adapter 右侧适配器
     */
    public void setRightAdapter(ListAdapter adapter) {
        mListView2.setAdapter(adapter);
    }

    /**
     * 设置左边选择事件回调
     *
     * @param listener 回调接口
     */
    public void setLeftOnSelected(AdapterView.OnItemClickListener listener) {
        mListView1.setOnItemClickListener(listener);
    }

    /**
     * 设置右边选择事件回调
     *
     * @param listener 回调接口
     */
    @SuppressWarnings("unused") public void setRightOnSelected(AdapterView.OnItemClickListener listener) {
        mListView2.setOnItemClickListener(listener);
    }

    /**
     * 获得最终选择的 listView
     *
     * @return listView
     */
    public ListView getFinalListView() {
        return mListView2;
    }
}

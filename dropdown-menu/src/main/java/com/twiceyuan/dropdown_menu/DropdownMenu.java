package com.twiceyuan.dropdown_menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by twiceYuan on 10/21/15.
 * <p/>
 * 下拉菜单，通过 PopupWindow 实现
 */
public class DropdownMenu extends LinearLayout {

    @SuppressWarnings("FieldCanBeLocal")
    private Context mContext;
    private PopupWindow mPopupWindow;
    private ListView mListView;
    @SuppressWarnings("FieldCanBeLocal")
    private RelativeLayout mShadowLayout;
    private OnDropdownItemClickListener mItemClickListener;
    private TextView textView;
    private ImageView iconView;
    private DropdownAdapter mDropdownAdapter;
    private OnClickListener mSecondClickListener;

    public DropdownMenu(Context context) {
        super(context);
        init(context, null);
    }

    public DropdownMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DropdownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DropdownMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        // 初始化属性
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.DropdownMenu);
        String titleText = attributes.getString(
                R.styleable.DropdownMenu_titleText);
        float textSize = attributes.getFloat(
                R.styleable.DropdownMenu_titleTextSize, 15f);
        //noinspection deprecation
        int textColor = attributes.getColor(
                R.styleable.DropdownMenu_titleColor,
                context.getResources().getColor(android.R.color.background_dark));
        //noinspection deprecation
        int titleBgColor = attributes.getColor(
                R.styleable.DropdownMenu_titleBgColor,
                mContext.getResources().getColor(R.color.grey));
        int collapseIcon = attributes.getResourceId(
                R.styleable.DropdownMenu_iconCollapse,
                R.drawable.ic_expand_less_black_24dp);
        int expandedIcon = attributes.getResourceId(
                R.styleable.DropdownMenu_iconExpanded,
                R.drawable.ic_expand_more_black_24dp);
        //noinspection deprecation
        int listBgColor = attributes.getColor(
                R.styleable.DropdownMenu_listBgColor,
                mContext.getResources().getColor(android.R.color.white));
        final int iconStyle = attributes.getInt(
                R.styleable.DropdownMenu_iconColor, 1);
        if (iconStyle == 0) {
            collapseIcon = R.drawable.ic_expand_more_white_24dp;
            expandedIcon = R.drawable.ic_expand_less_white_24dp;
        }
        if (iconStyle == 1) {
            collapseIcon = R.drawable.ic_expand_more_black_24dp;
            expandedIcon = R.drawable.ic_expand_less_black_24dp;
        }
        final int iconExpanded = expandedIcon;
        final int iconCollapse = collapseIcon;

        attributes.recycle();

        View popWindows = LayoutInflater.from(mContext)
                .inflate(R.layout.dropdown_menu_popupwindow, (ViewGroup) getParent(), false);
        mPopupWindow = new PopupWindow(popWindows, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(false);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(HORIZONTAL);
        setBackgroundColor(titleBgColor);
        setGravity(Gravity.CENTER);
        mListView = (ListView) popWindows.findViewById(R.id.lv_menu);
        mListView.setBackgroundColor(listBgColor);
        mListView.setAdapter(mDropdownAdapter = new ArrayDropdownAdapter(
                mContext,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"请配置 DropdownMenu 的 Adapter"}));
        mShadowLayout = (RelativeLayout) popWindows.findViewById(R.id.rl_menu_shadow);
        mShadowLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mItemClickListener != null) {
                    mItemClickListener.onClick(position);
                }
                textView.setText(mDropdownAdapter.getTitleString(position));
                mPopupWindow.dismiss();
            }
        });

        textView = new TextView(mContext);
        textView.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(TextUtils.isEmpty(titleText) ? "[未配置]" : titleText);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        textView.setTextColor(textColor);
        textView.setBackgroundColor(titleBgColor);
        textView.setPadding(20, 20, 20, 20);
        textView.setGravity(Gravity.CENTER);
        iconView = new ImageView(context);
        iconView.setPadding(20, 20, 20, 20);
        iconView.setImageResource(collapseIcon);

        addView(textView);
        addView(iconView);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                iconView.setImageResource(iconCollapse);
            }
        });

        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    iconView.setImageResource(iconCollapse);
                } else {
                    mPopupWindow.showAsDropDown(DropdownMenu.this);
                    iconView.setImageResource(iconExpanded);
                }
                if (mSecondClickListener != null) {
                    mSecondClickListener.onClick(DropdownMenu.this);
                }
            }
        });
    }

    public void setAdapter(DropdownAdapter adapter) {
        mListView.setAdapter(mDropdownAdapter = adapter);
    }

    public void setOnItemClickListener(OnDropdownItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    /**
     * 设置默认标题文字
     *
     * @param title 内容
     */
    public void setTitle(String title) {
        textView.setText(title);
    }

    /**
     * 下拉菜单是否在显示
     */
    public boolean isDropdown() {
        return mPopupWindow != null && mPopupWindow.isShowing();
    }

    /**
     * 如果存在，展开
     */
    public void expand() {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(this);
        }
    }

    /**
     * 如果存在，收起
     */
    public void collapse() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    public void addOnClickListener(OnClickListener l) {
        mSecondClickListener = l;
    }
}

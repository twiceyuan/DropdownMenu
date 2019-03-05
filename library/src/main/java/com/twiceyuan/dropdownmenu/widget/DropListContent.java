package com.twiceyuan.dropdownmenu.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.twiceyuan.dropdownmenu.R;
import com.twiceyuan.dropdownmenu.Utils;
import com.twiceyuan.dropdownmenu.contract.DropdownContent;
import com.twiceyuan.dropdownmenu.listener.OnChooseListener;

import java.util.List;

public class DropListContent implements DropdownContent<String> {

    private final Context      mContext;
    private final List<String> mSelections;

    public DropListContent(Context context, List<String> selections) {
        mContext = context;
        mSelections = selections;
    }

    @Override
    public View onCreateDropdownView(OnChooseListener<String> controller) {
        ListView listView = new ListView(mContext);
        DropListAdapter adapter = new DropListAdapter(mSelections);

        listView.setDivider(mContext.getResources().getDrawable(R.drawable.ddm_list_divider));
        listView.setDividerHeight(Utils.dp2px(1));
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            adapter.setSelected(position);
            controller.onChoose(mSelections.get(position));
        });

        FrameLayout frameLayout = new FrameLayout(mContext);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                Utils.dp2px(300)
        ));

        frameLayout.addView(listView);
        return frameLayout;
    }
}

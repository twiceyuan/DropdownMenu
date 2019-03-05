package com.twiceyuan.ddmsample.custom;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.twiceyuan.ddmsample.R;
import com.twiceyuan.dropdownmenu.contract.DropdownContent;
import com.twiceyuan.dropdownmenu.listener.OnChooseListener;
import com.twiceyuan.dropdownmenu.widget.BaseDropListAdapter;

import java.util.List;

/**
 * 自定义的选择弹出内容
 */
public class CustomListContent implements DropdownContent<String> {

    private Context      mContext;
    private List<String> mSelections;

    public CustomListContent(Context context, List<String> selections) {
        mContext = context;
        mSelections = selections;
    }

    @Override
    public View onCreateDropdownView(OnChooseListener<String> listener) {
        ListView listView = new ListView(mContext);
        BorderAdapter adapter = new BorderAdapter(mSelections);

        listView.setDivider(null);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            adapter.setSelected(position);
            listener.onChoose(mSelections.get(position));
        });

        return listView;
    }

    private static class BorderAdapter extends BaseDropListAdapter<String, ViewHolder> {

        BorderAdapter(List<String> strings) {
            super(strings);
        }

        @Override
        protected ViewHolder createHolder(View rootView) {
            return new ViewHolder(rootView);
        }

        @Override
        protected int itemLayoutId() {
            return R.layout.item_border_selection;
        }

        @Override
        protected void onBindSelected(String s, ViewHolder holder) {
            holder.text.setText(s);
            holder.text.setTextColor(Color.WHITE);
            holder.text.setBackgroundResource(R.drawable.shape_selection_border);
        }

        @Override
        protected void onBindNormal(String text, ViewHolder holder) {
            holder.text.setText(text);
            holder.text.setTextColor(0xFF222222);
            holder.text.setBackground(null);
        }
    }

    static class ViewHolder {

        TextView text;

        ViewHolder(View rootView) {
            this.text = rootView.findViewById(R.id.tv_item);
        }
    }
}

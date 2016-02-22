package com.twiceyuan.ddmsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.twiceyuan.dropdownmenu.ArrayDropdownAdapter;
import com.twiceyuan.dropdownmenu.DropdownMenu;
import com.twiceyuan.dropdownmenu.MenuManager;
import com.twiceyuan.dropdownmenu.OnDropdownItemClickListener;

/**
 * Created by twiceYuan on 2/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class DropdownListFragment extends Fragment {

    final String[] HEROES = new String[]{"Iron Man", "Ant Man", "American Captain", "Hulk", "Thor", "Black Widow"};
    final String[] COLORS = new String[]{"Red", "Yellow", "Blue", "White"};

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dropdown_list, container, false);

        final DropdownMenu menu1 = (DropdownMenu) view.findViewById(R.id.dm_dropdown);
        final DropdownMenu menu2 = (DropdownMenu) view.findViewById(R.id.dm_dropdown2);

        final TextView textContent = (TextView) view.findViewById(R.id.textContent);

        menu1.setAdapter(new ArrayDropdownAdapter(getContext(), R.layout.dropdown_light_item_1line, HEROES));

        // 设定下拉内容为单选
        menu1.getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        // 设置 ListView 的分隔线和分隔线高度
        menu1.getListView().setDivider(ContextCompat.getDrawable(getContext(), R.drawable.inset_divider));
        menu1.getListView().setDividerHeight(1);

        // 配置选择监听器
        menu1.setOnItemClickListener(new OnDropdownItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textContent.setText(String.format("你选择了英雄：%s", HEROES[position]));
            }
        });

        menu2.setAdapter(new ArrayDropdownAdapter(getActivity(), R.layout.dropdown_dark_item_1line, COLORS));

        menu2.setOnItemClickListener(new OnDropdownItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textContent.setText(String.format("你选择了颜色：%s", COLORS[position]));
            }
        });

        // 将多个 DropdownMenu 成组，在其中一个弹出的时候隐藏另外的
        MenuManager.group(menu1, menu2);
        return view;
    }
}
